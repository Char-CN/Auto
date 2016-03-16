package org.blazer.dataload.service;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;
import org.blazer.common.dao.Dao;
import org.blazer.common.dao.JDBCDao;
import org.blazer.common.util.Count;
import org.blazer.common.util.IntegerUtil;
import org.blazer.dataload.datasource.CustomJdbcDao;
import org.blazer.dataload.exception.UnknowDataSourceException;
import org.blazer.dataload.model.ALConvert;
import org.blazer.dataload.model.ALDataSourceBean;
import org.blazer.dataload.model.ALInputFileBean;
import org.blazer.dataload.model.ALInputFileBeforeBean;
import org.blazer.dataload.model.ALInputFileConstantBean;
import org.blazer.dataload.model.ALInputFileFieldBean;
import org.blazer.dataload.model.FileVariable;
import org.blazer.dataload.model.InputMode;
import org.blazer.dataload.model.KeyCombine;
import org.blazer.dataload.util.FieldUtil;
import org.blazer.dataload.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "ALService")
public class ALService {

	private static final Logger logger = LoggerFactory.getLogger(ALService.class);

	@Autowired
	private JDBCDao jdbcDao;

	public void convertSetInputBeforesSqls(final ALInputFileBean inputFileBean) throws Exception {
		for (ALInputFileBeforeBean beforeBean : inputFileBean.getAlInputFileBeforeBeans()) {
			for (String sql : StringUtils.split(beforeBean.getBeforeSql(), ";")) {
				// 先转换当前文件变量
				for (ALInputFileConstantBean constantBean : beforeBean.getInputFileBean().getAlInputFileConstantBeans()) {
					sql = sql.replace(constantBean.getFieldPointName(), convertStr(constantBean.getResultValue()));
				}
				beforeBean.getExtInputSQLList().add(sql);
			}
		}
	}

	/**
	 * 将配置的sql转换成一组sql,因为;会将其截断
	 * 
	 * @param aif
	 * @return
	 * @throws Exception
	 */
	public List<String> convertInputSqlsByMysql(final ALInputFileBean aif) throws Exception {
		long l1 = System.currentTimeMillis();
		String inputSql = aif.getInputSql();
		logger.info("----Begin----------------------------------------");
		logger.info("-- param inputSql     : {}", inputSql);
		List<String> sqlList = new ArrayList<String>();
		if (StringUtils.isBlank(inputSql) || inputSql.trim().equals(";")) {
			return sqlList;
		}
		try {
			// INSERT INTO
			// DW_Warehouse.DW_VISITOR_INTERACTION_FACTS(#PK#=VISITOR_TYPE_KEY,#PK#=APP_DEVICE_KEY,APP_VERSION_KEY,#PK#=PUBLISH_CHANNEL_KEY,ACTION_KEY,#PK#=PERIOD_KEY,#PK#=DAY_KEY,#UP#=ACTION_COUNT_INCREMENT,#UP#=USER_COUNT_INCREMENT)
			// VALUES (0,0,3,0,7,102,#A#,#B#,#C#);
			String onDuplicateKeyUpdate = StringUtils.EMPTY;
			for (String sql : StringUtils.split(inputSql, ";")) {
				// 过滤空白字符
				if (StringUtils.isBlank(sql)) {
					continue;
				}
				// 先转换当前文件变量
				for (ALInputFileConstantBean constantBean : aif.getAlInputFileConstantBeans()) {
					sql = sql.replace(constantBean.getFieldPointName(), convertStr(constantBean.getResultValue()));
				}
				String upperSql = sql.toUpperCase();
				if (upperSql.indexOf("INSERT") == -1 || upperSql.indexOf("ON DUPLICATE KEY UPDATE") != -1) {
//					sqlList.add(sql + ";");
					sqlList.add(sql);
					continue;
				}
				if (upperSql.indexOf("#UP#") == -1) {
//					sqlList.add(sql + ";");
					sqlList.add(sql);
					continue;
				}
				onDuplicateKeyUpdate = " ON DUPLICATE KEY UPDATE ";
				int braketLeft = sql.indexOf("(") + 1;
				int braketRight = sql.indexOf(")");
				String strField = sql.substring(braketLeft, braketRight);
				// 格式#PK#=KEY1,#PK#=KEY2,VAL1,VAL2
				String[] fields = StringUtils.splitByWholeSeparator(strField, ",");
				for (int i = 0, j = 0; i < fields.length; i++) {
					// 格式#PK#=VISITOR_TYPE_KEY
					String[] field = StringUtils.splitByWholeSeparator(fields[i], "=");
					if (field[0].trim().equalsIgnoreCase("#UP#")) {
						if (j != 0) {
							onDuplicateKeyUpdate += ",";
						}
						onDuplicateKeyUpdate += field[1] + "=VALUES(" + field[1] + ")";
						j++;
					}
				}
				sql = sql.replace("#PK#=", "");
				sql = sql.replace("#UP#=", "");
				// retSql += sql + onDuplicateKeyUpdate + ";";
//				sqlList.add(sql + onDuplicateKeyUpdate + ";");
				sqlList.add(sql + onDuplicateKeyUpdate);
			}
		} catch (Exception e) {
			// logger.error(e.getMessage(), e);
			throw e;
		} finally {
			long l2 = System.currentTimeMillis();
			if (sqlList.size() == 1) {
				logger.info("-- param sqlList size : {}", sqlList.size());
			} else {
				logger.info("-- param sqlList size : {} > 1, sql config may be mulit ';'", sqlList.size());
			}
			logger.info("-- method cost time   : {}", l2 - l1);
			logger.info("----End------------------------------------------");
		}
		return sqlList;
	}

	/**
	 * 检查是否包含多重维度，意思就是维度B里面包含维度A，可支持多重维度，在此判断为之后的操作奠定了性能的基础
	 * 
	 * @param dimBeans
	 * @return
	 */
	public boolean checkMultiDimDepend(final List<ALInputFileFieldBean> dimBeans) {
		for (int i = 1; i < dimBeans.size(); i++)
			for (int j = 0; j < i; j++)
				for (String FieldPointName : dimBeans.get(i).getExtDimKeyPointList())
					if (dimBeans.get(j).getFieldPointName().equals(FieldPointName))
						return true;
		return false;
	}

	/**
	 * 转换单个Dim的Sqls
	 * 
	 * @param rowList
	 * @param dimBean
	 */
	public void setConvertDimSqls(final List<HashMap<String, String>> rowList, ALInputFileFieldBean dimBean) {
		// 该情况多见,为了提高效率
		if (dimBean.getDimFlag() == 0) {
			return;
		}
		String inputSql = dimBean.getExtInputSQL();
		// 先转换当前文件变量
		for (ALInputFileConstantBean constantBean : dimBean.getInputFileBean().getAlInputFileConstantBeans()) {
			inputSql = inputSql.replace(constantBean.getFieldPointName(), convertStr(constantBean.getResultValue()));
		}
		for (HashMap<String, String> columnMap : rowList) {
			// 创建一个KeyCombine对象取值
			KeyCombine kc = new KeyCombine();
			List<String> keyList = new ArrayList<String>();
			kc.setKeyPointList(dimBean.getExtDimKeyPointList());
			for (String keyPoint : dimBean.getExtDimKeyPointList()) {
				if (columnMap.containsKey(keyPoint)) {
					keyList.add(columnMap.get(keyPoint));
				}
			}
			kc.setKeyList(keyList);
			if (dimBean.getExtDimKeyCombineValMap().containsKey(kc)) {
				continue;
			}
			String dimInputSql = inputSql;
			// 替换sql中的占位
			for (int i = 0; i < kc.getKeyList().size(); i++) {
				dimInputSql = dimInputSql.replace(kc.getKeyPointList().get(i), convertStr(kc.getKeyList().get(i)));
			}
			dimBean.getExtInputSQLList().add(dimInputSql);
			dimBean.getExtDimKeyCombineValMap().put(kc, null);
		}
	}

	/**
	 * 转换多个Dim的Sqls
	 * 
	 * @param rowList
	 * @param dimBeans
	 */
	public void setConvertDimsSqls(final List<HashMap<String, String>> rowList, List<ALInputFileFieldBean> dimBeans) {
		// 该情况多见,为了提高效率
		if (dimBeans.size() == 0) {
			return;
		}
		for (HashMap<String, String> columnMap : rowList) {
			for (ALInputFileFieldBean dimBean : dimBeans) {
				if (dimBean.getDimFlag() == 0) {
					continue;
				}
				// 创建一个KeyCombine对象进行map的比较
				KeyCombine kc = new KeyCombine();
				List<String> keyList = new ArrayList<String>();
				kc.setKeyPointList(dimBean.getExtDimKeyPointList());
				for (String keyPoint : kc.getKeyPointList()) {
					if (columnMap.containsKey(keyPoint)) {
						keyList.add(columnMap.get(keyPoint));
					}
				}
				kc.setKeyList(keyList);
				// 优化步骤，如果存在相同的，则不加入插入
				if (dimBean.getExtDimKeyCombineValMap().containsKey(kc)) {
					continue;
				}
				String inputSql = dimBean.getExtInputSQL();
				// 先转换当前文件变量
				for (ALInputFileConstantBean constantBean : dimBean.getInputFileBean().getAlInputFileConstantBeans()) {
					inputSql = inputSql.replace(constantBean.getFieldPointName(), convertStr(constantBean.getResultValue()));
				}
				// 替换sql中的占位
				for (int i = 0; i < kc.getKeyList().size(); i++) {
					inputSql = inputSql.replace(kc.getKeyPointList().get(i), convertStr(kc.getKeyList().get(i)));
				}
				dimBean.getExtInputSQLList().add(inputSql);
				dimBean.getExtDimKeyCombineValMap().put(kc, null);
			}
		}
	}

	/**
	 * 转换InputSql的Sqls
	 * 
	 * @param rowList
	 * @param sqlList
	 * @param aifBean
	 */
	public void setConvertSqls(final List<HashMap<String, String>> rowList, final List<String> sqlList, final ALInputFileBean aifBean) {
		logger.info("----Begin----------------------------------------");
		logger.info("-- param rowList size : {}", rowList.size());
		long l1 = System.currentTimeMillis();
		if (sqlList.size() == 0) {
			List<String> retList = new ArrayList<String>();
			aifBean.setExtInputSQLList(retList);
			return;
		}
		Count count = new Count(0, 5000);
		for (HashMap<String, String> columnMap : rowList) {
			count.add(1);
			for (String sql : sqlList) {
				String replaceSql = sql;
				for (String key : columnMap.keySet()) {
					// 直接replace会导致:前一个中含有本次需要replace的key的值,将前一个中的参数也替换成本参数
					replaceSql = replaceSql.replace(key, convertStr(columnMap.get(key)));
				}
				aifBean.getExtInputSQLList().add(replaceSql);
			}
			if (count.modZero()) {
				logger.info("converted " + count.getCount());
			}
		}
		if (count.getCount() == 0 || !count.modZero()) {
			logger.info("converted " + count.getCount());
		}
		long l2 = System.currentTimeMillis();
		logger.info("-- method cost time   : {}", l2 - l1);
		logger.info("----End------------------------------------------");
		
	}

	/**
	 * 转换InputSql的Sqls
	 * 
	 * @param rowList
	 * @param sqlList
	 * @param aifBean
	 */
	public void setConvertSqlsByCsv(final List<HashMap<String, String>> rowList, final List<String> sqlList, final ALInputFileBean aifBean) {
		logger.info("----Begin----------------------------------------");
		logger.info("-- param rowList size : {}", rowList.size());
		long l1 = System.currentTimeMillis();
		if (sqlList.size() == 0) {
			List<String> retList = new ArrayList<String>();
			aifBean.setExtInputSQLList(retList);
			return;
		}
		Count count = new Count(0, 5000);
		for (HashMap<String, String> columnMap : rowList) {
			count.add(1);
			for (String sql : sqlList) {
				String replaceSql = sql;
				for (String key : columnMap.keySet()) {
					// 直接replace会导致:前一个中含有本次需要replace的key的值,将前一个中的参数也替换成本参数
					replaceSql = replaceSql.replace(key, "" + columnMap.get(key));
				}
				aifBean.getExtInputSQLList().add(replaceSql);
			}
			if (count.modZero()) {
				logger.info("converted " + count.getCount());
			}
		}
		if (count.getCount() == 0 || !count.modZero()) {
			logger.info("converted " + count.getCount());
		}
		long l2 = System.currentTimeMillis();
		logger.info("-- method cost time   : {}", l2 - l1);
		logger.info("----End------------------------------------------");
	}

	/**
	 * 将csv的列转换成自定义HashMap,key:#A#,value:columns[i]
	 * 
	 * 将每一列文本值为 null|#NULL#|"N 的统一转换成小写的null值
	 * 
	 * 只有为#NULL_STR# 时才转换成是null的字符串
	 * 
	 * @param list
	 * @return
	 */
	public List<HashMap<String, String>> getConvertCsvColumn2FieldPoint(List<String[]> list) {
		List<HashMap<String, String>> rowList = new ArrayList<HashMap<String, String>>();
		if (list.size() == 0) {
			return rowList;
		}
		for (String[] columns : list) {
			HashMap<String, String> columnMap = new HashMap<String, String>();
			for (int i = 0; i < columns.length; i++) {
				if (columns[i] == null || columns[i].equalsIgnoreCase("null") || columns[i].equals("#NULL#") || columns[i].equals("\"N")) {
					columnMap.put(FieldUtil.LINE.get((i + 1)), null);
				} else if (columns[i].equals("#NULL_STR#")) {
					columnMap.put(FieldUtil.LINE.get((i + 1)), "null");
				} else {
					columnMap.put(FieldUtil.LINE.get((i + 1)), columns[i]);
				}
			}
			rowList.add(columnMap);
		}
		return rowList;
	}

	/**
	 * 后转换占位的Dim字段
	 * 
	 * @param rowList
	 * @param dimBean
	 */
	public void convertFileField2FieldPointByRowAndDim(List<HashMap<String, String>> rowList, final ALInputFileFieldBean dimBean) {
		for (HashMap<String, String> columnMap : rowList) {
			// 创建一个KeyCombine对象进行map的比较
			KeyCombine kc = new KeyCombine();
			List<String> keyList = new ArrayList<String>();
			kc.setKeyPointList(dimBean.getExtDimKeyPointList());
			for (String keyPoint : kc.getKeyPointList()) {
				if (columnMap.containsKey(keyPoint)) {
					keyList.add(columnMap.get(keyPoint));
				}
			}
			kc.setKeyList(keyList);
			if (!dimBean.getExtDimKeyCombineValMap().containsKey(kc)) {
				logger.info("Notice: Dim may be a problem , is not found value, point values {}", keyList);
			} else {
				String val = dimBean.getExtDimKeyCombineValMap().get(kc);
				columnMap.put(dimBean.getFieldPointName(), val);
			}
		}
	}

	/**
	 * 后转换占位的Dim字段
	 * 
	 * @param rowList
	 * @param dimBeans
	 */
	public void convertFileField2FieldPointByRowAndDims(List<HashMap<String, String>> rowList, final List<ALInputFileFieldBean> dimBeans) {
		// 该情况多见,为了提高效率
		if (dimBeans.size() == 0) {
			return;
		}
		for (HashMap<String, String> columnMap : rowList) {
			for (ALInputFileFieldBean dimBean : dimBeans) {
				// 创建一个KeyCombine对象进行map的比较
				KeyCombine kc = new KeyCombine();
				List<String> keyList = new ArrayList<String>();
				kc.setKeyPointList(dimBean.getExtDimKeyPointList());
				for (String keyPoint : kc.getKeyPointList()) {
					if (columnMap.containsKey(keyPoint)) {
						keyList.add(columnMap.get(keyPoint));
					}
				}
				kc.setKeyList(keyList);
				if (!dimBean.getExtDimKeyCombineValMap().containsKey(kc)) {
					logger.info("Notice: Dim may be a problem , is not found value, point values {}", keyList);
				} else {
					String val = dimBean.getExtDimKeyCombineValMap().get(kc);
					columnMap.put(dimBean.getFieldPointName(), val);
				}
			}
		}
	}

	/**
	 * 设置转换当前选择文件的变量
	 * 
	 * @param alInputFileBean
	 * @param fileName
	 */
	public void setConvertInputConstantAndCurrentFile(final ALInputFileBean alInputFileBean, final String fileName) {
		HashMap<String, String> fileVarMap = new HashMap<String, String>();
		fileVarMap.put(FileVariable.FILENAME, fileName);
		fileVarMap.put(FileVariable.FILEPATH, alInputFileBean.getFilePath());
		alInputFileBean.setCurrentFileVar(fileVarMap);
		for (ALInputFileConstantBean constantBean : alInputFileBean.getAlInputFileConstantBeans()) {
			// 转换1: 默认值
			String value = null;
			value = getDefault(value, constantBean);
			// 转换2: 扩展方法转换,主要针对对字符串的处理
			value = getConvertMethodValue(value, constantBean);
			// 转换3: 正则转换
			value = getReplaceByReg(value, constantBean);
			// 转换5: 截取长度
			value = getLimitLength(value, constantBean);
			constantBean.setResultValue(value);
		}
	}

	/**
	 * 先转换占位的Dim字段
	 * 
	 * @param rowList
	 * @param notDimBeans
	 */
	public void convertFileField2FieldPointByRowAndIsNotDim(final List<HashMap<String, String>> rowList, final List<ALInputFileFieldBean> notDimBeans) {
		// 该情况多见,为了提高效率
		if (notDimBeans.size() == 0) {
			return;
		}
		for (HashMap<String, String> columnMap : rowList) {
			for (ALInputFileFieldBean notDimBean : notDimBeans) {
				String value = columnMap.get(notDimBean.getFieldPointName());
				// 转换1: 默认值
				value = getDefault(value, notDimBean);
				// 转换2: 扩展方法转换,主要针对对字符串的处理
				value = getConvertMethodValue(value, notDimBean);
				// 转换3: 正则转换
				value = getReplaceByReg(value, notDimBean);
				// 转换4: 占位替换(一般DefaultValue或OldFormat中含有#A#的字段进行替换)
				value = getReplaceByColumnPoint(value, columnMap);
				// 转换5: 截取长度
				value = getLimitLength(value, notDimBean);
				columnMap.put(notDimBean.getFieldPointName(), value);
			}
		}
	}

	/**
	 * 占位替换(一般DefaultValue或OldFormat中含有#A#的字段进行替换)
	 */
	private static String getReplaceByColumnPoint(String value, final HashMap<String, String> columnMap) {
		if (StringUtils.isBlank(value)) {
			return value;
		}
		for (String key : columnMap.keySet()) {
			if (value.indexOf(key) != -1) {
				value = value.replace(key, columnMap.get(key));
			}
		}
		return value;
	}

	/**
	 * 正则转换
	 */
	private static String getReplaceByReg(final String value, final ALConvert convert) {
		if (StringUtils.isBlank(value)) {
			return value;
		}
		return StringUtil.replaceAllByReg(value, convert.getOldFormat(), convert.getNewFormat());
	}

	/**
	 * 转换字符串
	 */
	private static String convertStr(String str) {
		if (str == null) {
			return "null";
		}
		// 转义斜杠
		if (str.indexOf("\\") > -1) {
			str = str.replace("\\", "\\\\");
		}
		// 转义引号
		if (str.indexOf("'") > -1) {
			str = str.replace("'", "\\'");
		}
		return "'" + str + "'";
	}

	/**
	 * 转换value值, 根据aiffb.getConvertMethod()
	 */
	private static String getConvertMethodValue(final String value, final ALConvert convert) {
		if (StringUtils.isBlank(value) || StringUtils.isBlank(convert.getConvertMethod())) {
			return value;
		}
		if (convert.getConvertMethod().equalsIgnoreCase("lower")) {
			return value.toLowerCase();
		} else if (convert.getConvertMethod().equalsIgnoreCase("upper")) {
			return value.toUpperCase();
		} else {
			logger.info("convertMethod may be a problem not found the convertMethod[{}]", convert.getConvertMethod());
		}
		return value;
	}

	/**
	 * 根据value获得默认值 当value不等于""(空字符串)的时候
	 */
	private static String getDefault(final String value, final ALConvert convert) {
		// 如果不是空字符串 或 null 则返回该value
		if (StringUtils.isNotBlank(value)) {
			return value;
		}
		// 如果默认值为空字符串
		if (StringUtils.isBlank(convert.getDefaultValue()) || convert.getDefaultValue().equalsIgnoreCase("#EMPTY#")) {
			return StringUtils.EMPTY;
		}
		// 如果默认值为null
		if (convert.getDefaultValue().equalsIgnoreCase("#NULL#")) {
			return null;
		}
		// 如果默认值为NULL_STR
		if (convert.getDefaultValue().equalsIgnoreCase("#NULL_STR#")) {
			return "null";
		}
		// 如果默认值为FILENAME
		if (convert.getDefaultValue().equalsIgnoreCase(FileVariable.FILENAME)) {
			return convert.getInputFileBean().getCurrentFileVar().get(FileVariable.FILENAME);
		}
		// 如果默认值为FILEPATH
		if (convert.getDefaultValue().equalsIgnoreCase(FileVariable.FILEPATH)) {
			return convert.getInputFileBean().getCurrentFileVar().get(FileVariable.FILEPATH);
		}
		return convert.getDefaultValue();
	}

	/**
	 * 根据value截取字符串 limitLength 格式:(5,10);(5,);(,10); 从第几个截取到第几个
	 */
	private static String getLimitLength(final String value, final ALConvert convert) {
		String limitLength = convert.getLimitLength();
		if (StringUtils.isBlank(value)) {
			return value;
		}
		if (StringUtils.isBlank(limitLength) || limitLength.indexOf(',') == -1 || limitLength.equals(",")) {
			return value;
		}
		limitLength = org.blazer.common.util.StringUtil.trimAll(limitLength);
		String[] limit = limitLength.split(",");
		int valueLength = value.length();
		int beginIndex = 0;
		int endIndex = 0;
		if (limitLength.matches("\\d+,\\d+")) {
			beginIndex = Integer.parseInt(limit[0]) - 1;
			endIndex = Integer.parseInt(limit[1]);
			endIndex = endIndex > valueLength ? valueLength : endIndex;
		} else if (limitLength.matches(",\\d+")) {
			beginIndex = valueLength - Integer.parseInt(limit[1]);
			beginIndex = beginIndex < 0 ? 0 : beginIndex;
			endIndex = valueLength;
		} else if (limitLength.matches("\\d+,")) {
			beginIndex = Integer.parseInt(limit[0]) - 1;
			beginIndex = beginIndex > valueLength ? valueLength : beginIndex;
			endIndex = valueLength;
		}
		// 容错处理(-111,-111)||(999999,999)
		beginIndex = beginIndex < 0 ? 0 : beginIndex;
		beginIndex = beginIndex > valueLength ? valueLength : beginIndex;
		endIndex = endIndex < 0 ? 0 : endIndex;
		endIndex = endIndex > valueLength ? valueLength : endIndex;
		return value.substring(beginIndex, endIndex);
	}

	/**
	 * 优化内存操作
	 */
	public void clearByOneFile(ALInputFileBean inputFileBean) {
		// 清空InputFile的SQL
		inputFileBean.getExtInputSQLList().clear();
		// 该步骤可省略, 因为执行下一个文件时会重复给该变量赋值, 为逻辑清晰暂此保留
		for (ALInputFileConstantBean constantBean : inputFileBean.getAlInputFileConstantBeans()) {
			constantBean.setResultValue(null);
		}
		// 清空before
		for (ALInputFileBeforeBean beforeBean : inputFileBean.getAlInputFileBeforeBeans()) {
			beforeBean.getExtInputSQLList().clear();
		}
		// 清空维度转换的SQL
		for (ALInputFileFieldBean fileFieldBean : inputFileBean.getAlInputFileFieldDimBeans()) {
			fileFieldBean.getExtInputSQLList().clear();
		}
	}

	/**
	 * 优化内存操作
	 */
	public void clearByOneConfig(ALInputFileBean aifBean) {
		aifBean.getAlInputFileFieldBeans().clear();
		aifBean.getAlInputFileFieldDimBeans().clear();
		aifBean.getAlInputFileFieldNotDimBeans().clear();
		aifBean.getAlInputFileBeforeBeans().clear();
		aifBean.getAlInputFileConstantBeans().clear();
		aifBean.getCurrentFileVar().clear();
		aifBean.getExtInputSQLList().clear();
	}

	/**
	 * 将Dim表将数据读入内存
	 */
	public void resetExtInfo(ALInputFileBean aifBean) throws UnknowDataSourceException {
		for (ALInputFileFieldBean aiffBean : aifBean.getAlInputFileFieldDimBeans()) {
			setExtInfo(aiffBean);
		}
	}

	/**
	 * 设置扩展信息
	 */
	private void setExtInfo(ALInputFileFieldBean aiffBean) throws UnknowDataSourceException {
		Dao executeDao = CustomJdbcDao.getDao(aiffBean.getDataSourceBean().getRecordId());
		// 先将所有存在的key,value查询出来
		HashMap<KeyCombine, String> kcMap = new HashMap<KeyCombine, String>();
		String dimSelectKeyStr = StringUtils.EMPTY;
		String dimKeyStr = StringUtils.EMPTY;
		String dimKeyPointStr = StringUtils.EMPTY;
		List<String> keyPointList = new ArrayList<String>();

		// A1:#A#,A2:#C#,A3:#D#
		String[] keys = StringUtils.splitByWholeSeparator(aiffBean.getDimKey(), aiffBean.getGroupDelimiter());
		if (keys.length > 1) {
			for (String key : keys) {
				if (key.indexOf(aiffBean.getKeyValDelimiter()) != -1) {
					if (dimSelectKeyStr.length() != 0) {
						dimSelectKeyStr += aiffBean.getGroupDelimiter();
						dimKeyStr += aiffBean.getGroupDelimiter();
						dimKeyPointStr += aiffBean.getGroupDelimiter();
					}
					String[] keyVal = StringUtils.splitByWholeSeparator(key, aiffBean.getKeyValDelimiter());
					dimSelectKeyStr += keyVal[0] + " as '" + keyVal[1] + "'";
					dimKeyStr += keyVal[0];
					dimKeyPointStr += keyVal[1];
					keyPointList.add(keyVal[1]);
				} else {
					logger.info("the dimKey may be a problem : [{}] , bean : [{}]", aiffBean.getDimKey(), aiffBean);
				}
			}
		} else {
			if (keys[0].indexOf(aiffBean.getKeyValDelimiter()) != -1) {
				String[] keyVal = StringUtils.splitByWholeSeparator(keys[0], aiffBean.getKeyValDelimiter());
				dimSelectKeyStr = keyVal[0] + " as '" + keyVal[1] + "'";
				dimKeyStr = keyVal[0];
				dimKeyPointStr = keyVal[1];
				keyPointList.add(keyVal[1]);
			} else {
				dimSelectKeyStr = keys[0] + " as '" + aiffBean.getFieldPointName() + "'";
				dimKeyStr = keys[0];
				dimKeyPointStr = aiffBean.getFieldPointName();
				keyPointList.add(aiffBean.getFieldPointName());
			}
		}
		String inputSql = "INSERT INTO " + aiffBean.getDimTableName() + "(" + dimKeyStr + ") VALUES(" + dimKeyPointStr + ")";
		String sql = "SELECT " + aiffBean.getDimValue() + " as dimValue," + dimSelectKeyStr + " FROM " + aiffBean.getDimTableName();

		List<Map<String, Object>> list = executeDao.find(sql);
		for (Map<String, Object> map : list) {
			KeyCombine kc = new KeyCombine();
			List<String> keyList = new ArrayList<String>();
			for (String keyPoint : keyPointList) {
				keyList.add(StringUtil.getString(map.get(keyPoint)));
			}
			String dimValue = StringUtil.getString(map.get("dimValue"));
			kc.setKeyList(keyList);
			kc.setKeyPointList(keyPointList);
			kcMap.put(kc, dimValue);
		}
		aiffBean.setExtInputSQL(inputSql);
		aiffBean.setExtDimKeyPointList(keyPointList);
		aiffBean.setExtDimKeyCombineValMap(kcMap);
	}

	/**
	 * 执行sql语句导入数据
	 * 
	 * @param inputFile
	 * @throws UnknowDataSourceException
	 */
	public void insertInputFile(ALInputFileBean inputFile) throws UnknowDataSourceException {
		long l1 = System.currentTimeMillis();
		logger.info("----Begin----------------------------------------");
		logger.info("-- param sqls length : {}", inputFile.getExtInputSQLList().size());
		Dao executeDao = CustomJdbcDao.getDao(inputFile.getDataSourceBean().getRecordId());
		Count count = new Count(0, 5000);
		try {
			int i = 1;
			for (String record : inputFile.getExtInputSQLList()) {
				if (i <= 10) {
					logger.info("-- param record {} : {}", i, record);
					i++;
				} else {
					break;
				}
			}
			executeDao.batchUpdate(inputFile.getExtInputSQLList(), count);
		} catch (RuntimeException e) {
			logger.error("error row : [{}]", count.getCount());
			logger.error("error sql : [{}]", inputFile.getExtInputSQLList().get(count.getCount() - 1));
			throw e;
		} finally {
			long l2 = System.currentTimeMillis();
			logger.info("-- method cost time : {}", l2 - l1);
			logger.info("----End------------------------------------------");
		}
	}

	/**
	 * 导出成后缀名为.al的文件
	 * 
	 * @param inputFile
	 * @throws Exception
	 */
	public void outputToFile(ALInputFileBean inputFile) throws Exception {
		String output = inputFile.getCurrentFileVar().get(FileVariable.FILEPATH);
		output += inputFile.getCurrentFileVar().get(FileVariable.FILENAME);
		output += ".al";
		FileWriter fw = new FileWriter(output);
		for (String row : inputFile.getExtInputSQLList()) {
			fw.write(row.replace("\\t", "\t") + "\n");
		}
		fw.flush();
		fw.close();
	}

	/**
	 * 插入维度数据
	 * 
	 * @param fileField
	 * @throws UnknowDataSourceException
	 */
	public void insertDim(ALInputFileFieldBean fileField) throws UnknowDataSourceException {
		long l1 = System.currentTimeMillis();
		logger.info("----Begin----------------------------------------");
		logger.info("-- param fileField   : {}", fileField);
		logger.info("-- param sqls length : {}", fileField.getExtInputSQLList().size());
		Dao executeDao = CustomJdbcDao.getDao(fileField.getDataSourceBean().getRecordId());
		Count count = new Count(0, 5000);
		try {
			int i = 1;
			for (String record : fileField.getExtInputSQLList()) {
				if (i <= 10) {
					logger.info("-- param record {} : {}", i, record);
					i++;
				} else {
					break;
				}
			}
			executeDao.batchUpdate(fileField.getExtInputSQLList(), count);
			// 重新加载KeyVal
			setExtInfo(fileField);
		} catch (RuntimeException e) {
			logger.error("error row : [{}]", count.getCount());
			throw e;
		} finally {
			long l2 = System.currentTimeMillis();
			logger.info("-- method cost time : {}", l2 - l1);
			logger.info("----End------------------------------------------");
		}
	}

	/**
	 * 执行在导入数据之前需要执行的sql语句
	 * 
	 * @param beforeBean
	 * @throws UnknowDataSourceException
	 */
	public void insertInputBefore(ALInputFileBeforeBean beforeBean) throws UnknowDataSourceException {
		long l1 = System.currentTimeMillis();
		logger.info("----Begin----------------------------------------");
		logger.info("-- param beforeBean : {}", beforeBean);
		logger.info("-- param sqls length : {}", beforeBean.getExtInputSQLList().size());
		Dao executeDao = CustomJdbcDao.getDao(beforeBean.getDataSourceBean().getRecordId());
		Count count = new Count(0, 5000);
		try {
			int i = 1;
			for (String record : beforeBean.getExtInputSQLList()) {
				if (i <= 10) {
					logger.info("-- param record {} : {}", i, record);
					i++;
				} else {
					break;
				}
			}
			executeDao.batchUpdate(beforeBean.getExtInputSQLList(), count);
		} catch (RuntimeException e) {
			logger.error("error row : [{}]", count.getCount());
			throw e;
		} finally {
			long l2 = System.currentTimeMillis();
			logger.info("-- method cost time : {}", l2 - l1);
			logger.info("----End------------------------------------------");
		}
	}

	/**
	 * 根据配置表，读取配置的需要导入的文件规则， 表：AL_InputGroup &
	 * AL_InputFile & AL_DataSource
	 * 
	 * @param groupIDs
	 * @param fileIds
	 * @return
	 */
	public List<ALInputFileBean> findByGroupAndFile(String groupIDs, String fileIds) {
		long l1 = System.currentTimeMillis();
		logger.info("----Begin----------------------------------------");
		logger.info("-- param groupIDs : {}", groupIDs);
		logger.info("-- param fileIds : {}", fileIds);
		// 取AL
		StringBuilder sbSql = new StringBuilder();
		sbSql.append("SELECT aif.*, ifnull(ads2.RecordID, ads1.RecordID) DSID");
		sbSql.append(" FROM AL_InputGroup aig");
		sbSql.append(" INNER JOIN AL_InputFile aif ON aif.GroupID = aig.RecordID");
		sbSql.append(" LEFT JOIN (SELECT * FROM AL_DataSource WHERE Enable = 1) ads1 ON ads1.RecordID = aig.DataSourceID");
		sbSql.append(" LEFT JOIN (SELECT * FROM AL_DataSource WHERE Enable = 1) ads2 ON ads2.RecordID = aif.DataSourceID");
		sbSql.append(" WHERE aig.`Enable` > 0");
		sbSql.append(" AND aif.`Enable` > 0");
		// sbSql.append(" AND (aig.RecordID IN (").append(groupIDs).append(") OR aif.RecordID IN (").append(fileIds).append("))");
		if (!groupIDs.equalsIgnoreCase("*")) {
			String selectType = "";
			if (groupIDs.toLowerCase().startsWith("notin")) {
				selectType = "NOT IN";
				groupIDs = groupIDs.substring(5);
			} else if (groupIDs.toLowerCase().startsWith("in")) {
				selectType = "IN";
				groupIDs = groupIDs.substring(2);
			} else {
				selectType = "IN";
			}
			sbSql.append(" AND aig.RecordID ").append(selectType).append("(").append(groupIDs).append(")");
		}
		if (!fileIds.equalsIgnoreCase("*")) {
			String selectType = "";
			if (fileIds.toLowerCase().startsWith("notin")) {
				selectType = "NOT IN";
				fileIds = fileIds.substring(5);
			} else if (fileIds.toLowerCase().startsWith("in")) {
				selectType = "IN";
				fileIds = fileIds.substring(2);
			} else {
				selectType = "IN";
			}
			sbSql.append(" AND aif.RecordID ").append(selectType).append("(").append(fileIds).append(")");
		}
		sbSql.append(" ORDER BY aif.GroupID ASC, aif.Sort ASC, aif.RecordID ASC");
		logger.debug("-- query sql     : {}", sbSql);
		List<Map<String, Object>> resultList = jdbcDao.find(sbSql.toString());
		logger.debug("-- query size    : {}", resultList.size());
		List<ALInputFileBean> list = new ArrayList<ALInputFileBean>();
		for (Map<String, Object> map : resultList) {
			ALInputFileBean aifb = new ALInputFileBean();
			aifb.setRecordId(IntegerUtil.getInteger(map.get("RecordID")));
			aifb.setGroupId(IntegerUtil.getInteger(map.get("GroupId")));
			aifb.setFileName(StringUtil.getString(map.get("FileName")));
			aifb.setFilePath(StringUtil.getString(map.get("FilePath")));
			// 目录路径最后的separator
			if (aifb.getFilePath() != null && !aifb.getFilePath().endsWith("/")) {
				aifb.setFilePath(aifb.getFilePath() + "/");
			}
			if (aifb.getFilePath() != null) {
				aifb.setExtFilePathSuccess(aifb.getFilePath() + "success");
				aifb.setExtFilePathFail(aifb.getFilePath() + "fail");
			}
			aifb.setFileRegExp(StringUtil.getString(map.get("FileRegExp")));
			aifb.setInputSql(StringUtil.getString(map.get("InputSql")));
			// 事务模式 或 容错模式
			if (map.get("InputMode") == null || "1".equals(StringUtil.getString(map.get("InputMode")))) {
				aifb.setInputMode(InputMode.Trunsaction);
			} else {
				aifb.setInputMode(InputMode.FaultTolerant);
			}
			aifb.setSort(IntegerUtil.getInteger(map.get("Sort")));
			aifb.setEnable(IntegerUtil.getInteger(map.get("Enable")));
			String fileSeparator = StringUtil.getString(map.get("FileSeparator"));
			// 分隔符 Spring 查出来转义成了\\t 默认\t
			if (StringUtils.isEmpty(fileSeparator)) {
				aifb.setFileSeparator('\t');
			} else if (fileSeparator.equals("\\t")) {
				aifb.setFileSeparator('\t');
			} else if (fileSeparator.equals("\\n")) {
				aifb.setFileSeparator('\n');
			} else if (fileSeparator.equals("\\b")) {
				aifb.setFileSeparator('\b');
			} else if (fileSeparator.equals("\\f")) {
				aifb.setFileSeparator('\f');
			} else if (fileSeparator.equals("\\r")) {
				aifb.setFileSeparator('\r');
			} else {
				aifb.setFileSeparator(CharUtils.toChar(fileSeparator));
			}
			// 目标数据源
			ALDataSourceBean dataSourceBean = null;
			try {
				dataSourceBean = CustomJdbcDao.getDataSourceBean(IntegerUtil.getInteger(map.get("DSID")));
			} catch (UnknowDataSourceException e) {
				dataSourceBean = new ALDataSourceBean();
				dataSourceBean.setRecordId(null);
				logger.info("UnknowDataSourceException id[{}]", IntegerUtil.getInteger(map.get("DSID")));
			}
			aifb.setDataSourceBean(dataSourceBean);
			// 所有FileField
			aifb.setAlInputFileFieldBeans(this.findFileFieldByFileID(aifb));
			// 所有FileConstant
			aifb.setAlInputFileConstantBeans(this.findFileConstantByFileID(aifb));
			// 所有FileBefore
			aifb.setAlInputFileBeforeBeans(this.findFileBeforeByFileID(aifb));
			// 所有DimFileField
			List<ALInputFileFieldBean> dimBeans = new ArrayList<ALInputFileFieldBean>();
			// 所有NotDimFileField
			List<ALInputFileFieldBean> notDimBeans = new ArrayList<ALInputFileFieldBean>();
			for (ALInputFileFieldBean bean : aifb.getAlInputFileFieldBeans()) {
				if (bean.isDim()) {
					dimBeans.add(bean);
				} else {
					notDimBeans.add(bean);
				}
			}
			aifb.setAlInputFileFieldDimBeans(dimBeans);
			aifb.setAlInputFileFieldNotDimBeans(notDimBeans);
			aifb.setExtInputSQLList(new ArrayList<String>());
			aifb.setCurrentFileVar(new HashMap<String, String>());
			list.add(aifb);
		}
		long l2 = System.currentTimeMillis();
		logger.info("-- method cost time : {}", l2 - l1);
		logger.info("----End------------------------------------------");
		return list;
	}

	public List<ALInputFileFieldBean> findFileFieldByFileID(ALInputFileBean aifb) {
		String sql = "SELECT aiff.* FROM AL_InputFileField aiff";
		sql += " WHERE aiff.FileID = ?";
		sql += " ORDER BY aiff.FileID ASC, aiff.Sort ASC, aiff.RecordID ASC";
		List<Map<String, Object>> resultList = jdbcDao.find(sql, aifb.getRecordId());
		List<ALInputFileFieldBean> list = new ArrayList<ALInputFileFieldBean>();
		for (Map<String, Object> map : resultList) {
			ALInputFileFieldBean aiffb = new ALInputFileFieldBean();
			aiffb.setInputFileBean(aifb);
			aiffb.setRecordId(IntegerUtil.getInteger(map.get("RecordID")));
			aiffb.setFileId(IntegerUtil.getInteger(map.get("FileId")));
			aiffb.setDimFlag(IntegerUtil.getInteger(map.get("DimFlag")));
			aiffb.setFieldPointName(StringUtil.getString(map.get("FieldPointName")));
			if (!FieldUtil.POINT.containsKey(aiffb.getFieldPointName())) {
				logger.debug("Notice : [{}] is not valid in system defined", aiffb.getFieldPointName());
			}
			aiffb.setConvertMethod(StringUtil.getString(map.get("ConvertMethod")));
			aiffb.setDefaultValue(StringUtil.getString(map.get("DefaultValue")));
			aiffb.setLimitLength(StringUtil.getString(map.get("LimitLength")));
			aiffb.setOldFormat(StringUtil.getString(map.get("OldFormat")));
			aiffb.setNewFormat(StringUtil.getString(map.get("NewFormat")));
			aiffb.setDimKey(StringUtil.getString(map.get("DimKey")));
			aiffb.setDimValue(StringUtil.getString(map.get("DimValue")));
			aiffb.setDimTableName(StringUtil.getString(map.get("DimTableName")));
			if (StringUtils.isNotBlank(aiffb.getDimKey()) && StringUtils.isNotBlank(aiffb.getDimValue()) && StringUtils.isNotBlank(aiffb.getDimTableName())) {
				aiffb.setDim(true);
				aiffb.setExtInputSQLList(new ArrayList<String>());
				// 目标数据源
				if (IntegerUtil.getInteger(map.get("DataSourceID")) == null) {
					aiffb.setDataSourceBean(aifb.getDataSourceBean());
				} else {
					ALDataSourceBean dataSourceBean = null;
					try {
						dataSourceBean = CustomJdbcDao.getDataSourceBean(IntegerUtil.getInteger(map.get("DataSourceID")));
					} catch (UnknowDataSourceException e) {
						dataSourceBean = new ALDataSourceBean();
						dataSourceBean.setRecordId(null);
						logger.info("UnknowDataSourceException id[{}]", IntegerUtil.getInteger(map.get("DataSourceID")));
					}
					aiffb.setDataSourceBean(dataSourceBean);
				}
			} else {
				aiffb.setDim(false);
			}
			list.add(aiffb);
		}
		return list;
	}

	public List<ALInputFileBeforeBean> findFileBeforeByFileID(ALInputFileBean aifb) {
		String sql = "SELECT * FROM AL_InputFileBefore WHERE FileID=? ORDER BY FileID ASC, Sort ASC, RecordID ASC";
		List<Map<String, Object>> resultList = jdbcDao.find(sql, aifb.getRecordId());
		List<ALInputFileBeforeBean> list = new ArrayList<ALInputFileBeforeBean>();
		for (Map<String, Object> map : resultList) {
			ALInputFileBeforeBean beforeBean = new ALInputFileBeforeBean();
			beforeBean.setInputFileBean(aifb);
			beforeBean.setRecordId(IntegerUtil.getInteger(map.get("RecordID")));
			beforeBean.setFileId(IntegerUtil.getInteger(map.get("FileId")));
			beforeBean.setBeforeSql(StringUtil.getString(map.get("BeforeSql")));
			// 目标数据源
			if (IntegerUtil.getInteger(map.get("DataSourceID")) == null) {
				beforeBean.setDataSourceBean(aifb.getDataSourceBean());
			} else {
				ALDataSourceBean dataSourceBean = null;
				try {
					dataSourceBean = CustomJdbcDao.getDataSourceBean(IntegerUtil.getInteger(map.get("DataSourceID")));
				} catch (UnknowDataSourceException e) {
					dataSourceBean = new ALDataSourceBean();
					dataSourceBean.setRecordId(null);
					logger.info("UnknowDataSourceException id[{}]", IntegerUtil.getInteger(map.get("DataSourceID")));
				}
				beforeBean.setDataSourceBean(dataSourceBean);
			}
			beforeBean.setExtInputSQLList(new ArrayList<String>());
			list.add(beforeBean);
		}
		return list;
	}

	public List<ALInputFileConstantBean> findFileConstantByFileID(ALInputFileBean aifb) {
		String sql = "SELECT * FROM AL_InputFileConstant WHERE FileID=? ORDER BY FileID ASC, Sort ASC, RecordID ASC";
		List<Map<String, Object>> resultList = jdbcDao.find(sql, aifb.getRecordId());
		List<ALInputFileConstantBean> list = new ArrayList<ALInputFileConstantBean>();
		for (Map<String, Object> map : resultList) {
			ALInputFileConstantBean constantBean = new ALInputFileConstantBean();
			constantBean.setInputFileBean(aifb);
			constantBean.setRecordId(IntegerUtil.getInteger(map.get("RecordID")));
			constantBean.setFileId(IntegerUtil.getInteger(map.get("FileId")));
			constantBean.setFieldPointName(StringUtil.getString(map.get("FieldPointName")));
			if (!FieldUtil.POINT.containsKey(constantBean.getFieldPointName())) {
				logger.debug("Notice : [{}] is not valid in system defined", constantBean.getFieldPointName());
			}
			constantBean.setConvertMethod(StringUtil.getString(map.get("ConvertMethod")));
			constantBean.setDefaultValue(StringUtil.getString(map.get("DefaultValue")));
			constantBean.setLimitLength(StringUtil.getString(map.get("LimitLength")));
			constantBean.setOldFormat(StringUtil.getString(map.get("OldFormat")));
			constantBean.setNewFormat(StringUtil.getString(map.get("NewFormat")));
			list.add(constantBean);
		}
		return list;
	}

}
