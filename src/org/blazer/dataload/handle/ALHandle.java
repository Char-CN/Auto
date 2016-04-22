package org.blazer.dataload.handle;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.CharUtils;
import org.blazer.common.util.ApplicationUtil;
import org.blazer.common.util.SourceUtil;
import org.blazer.common.util.SysConfig;
import org.blazer.dataload.exception.DirectoryNotFoundException;
import org.blazer.dataload.exception.FileEmptyException;
import org.blazer.dataload.exception.UnknowDataSourceException;
import org.blazer.dataload.exception.UnknowTargetSourceException;
import org.blazer.dataload.model.ALInputFileBean;
import org.blazer.dataload.model.ALInputFileBeforeBean;
import org.blazer.dataload.model.ALInputFileConstantBean;
import org.blazer.dataload.model.ALInputFileFieldBean;
import org.blazer.dataload.service.ALDataSourceService;
import org.blazer.dataload.service.ALLogService;
import org.blazer.dataload.service.ALService;
import org.blazer.dataload.util.CsvUtil;
import org.blazer.dataload.util.FieldUtil;
import org.blazer.dataload.util.FileUtil;
import org.blazer.dataload.util.LockUtil;
import org.blazer.dataload.util.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "ALHandle")
public class ALHandle {

	private static final Logger logger = LoggerFactory.getLogger(ALHandle.class);

	public static void main(String[] args) {
		// String[] testArgs = new String[] { "*", "144,146" };
		String[] testArgs = new String[] { "1", "*" };
		ApplicationUtil.init(SourceUtil.resource + "logback_autoload.xml");
		ALHandle handle = (ALHandle) ApplicationUtil.getBean("ALHandle");
		// testArgs = args;
		if (args.length == 0) {
			args = new String[] { "*", "*" };
		} else if (args.length == 1) {
			args = new String[] { args[0], "*" };
		}
		handle.handle(args, testArgs, SysConfig.test);
	}

	@Autowired
	private ALService alService;

	@Autowired
	private ALDataSourceService alDataSourceService;

	@Autowired
	private ALLogService alLogService;

	/**
	 * 配置中以#A#,#B#来占位,暂时只支持到#Z#,以后可更换配置规则可自动读取列数,以{0},{1}来替换,Dim通常表示对维度的配置
	 * 
	 * 其中如果#A#中的列含有#B#会存在潜在问题,该问题遗留日后修复
	 * 
	 * null|#NULL#|"N 的统一转换成小写的null值,只有为#NULL_STR# 时才转换成是null的字符串
	 * 
	 * step1.1 : 开始,已经加载过spring应用,注册过扑捉kill和ctrl+c和hup命令,并记录日志
	 * 
	 * step1.2 : 加载自定义数据源
	 * 
	 * step2 : 将所有AL_InputFile(包含AL_InputFileField,如果是Dim表则先不将数据读入内存)查询出来放进List
	 * 
	 * step3 : 循环所有AL_InputFile处理每一个配置信息
	 * 
	 * step4 : 查询该配置所有匹配到的文件名称
	 * 
	 * step5 : 将AL_InputFile的Dim表将数据读入内存
	 * 
	 * step6 : 循环所有匹配的文件名称(排序asc)处理每一个文件
	 * 
	 * step7 : 设置转换当前选择文件的变量
	 * 
	 * step8 : 读取文件内容并存入List<String[]> 集合
	 * 
	 * step9 : 将List<String[]>转换成List<HashMap<String, String>>
	 * 其中HashMap的Key为#A#,#B#,#C#...的格式
	 * 
	 * step10 : 如果List<HashMap<String, String>>为0行数据表示文件为空,抛出FileEmptyException
	 * 
	 * step11 : 将所有非Dim的字段进行转换,目的是为了Dim字段能用到非Dim字段
	 *
	 * step11.5 : 两种模式：1.事务模式，2.容错模式
	 * 
	 * step12 : 转换插入维度的语句并存入DimBean里,在该方法中判断DimFlag是否为1,如果为1则表示是维度需要插入
	 * 
	 * step13 : 循环插入转换的维度的语句,并更新step6的Dim内存数据
	 * 
	 * step14 : 将所有的Dim的字段进行转换,目的是为了接下来替换InputSql中语句的占位符
	 * 
	 * step15 : 判断目标是插入到Mysql,Hbase或者抛出UnknowTargetSourceException
	 * 
	 * step16.1 : 转换BeforeSql并且存入BeforeBean中
	 * 
	 * step16.2 : 循环插入转换的维度的BeforeSql
	 * 
	 * step16.3 : 将配置的InputSql用;号分解成获得一个List<String>集合
	 * 
	 * step17 : 转换插入的语句并存入AL_InputFileBean里
	 * 
	 * step18 : 循环插入转换的语句
	 * 
	 * step19 : 根据flag判断是否执行成功,并移动文件到success或fail目录
	 * 
	 * step20 : 已经将文件移动到success或fail目录
	 * 
	 * step21 : 判断时间超过多少分钟给个通知日志
	 * 
	 * step100 : 所有处理结束
	 * 
	 * @param args
	 *            "NotIn1,2,3,4,5,6,7,20", "*"
	 */
	public void handle(String[] args, String[] testArgs, boolean isTest) {
		logger.info("====================AutoLoad Begin====================");
		double step = 1.1;
		if (isTest)
			args = testArgs;
		long totalTimeBegin = System.currentTimeMillis();
		step = 1.2;
		// 确保lock目录存在
		String lockDir = SourceUtil.root + "lock/";
		File folder = new File(lockDir);
		if (folder.isFile()) {
			folder.delete();
		}
		if (!folder.isDirectory()) {
			folder.mkdir();
		}
		step = 1.3;
		// 加载自定义数据源
		alDataSourceService.initDataSource();
		int totalFileSuccess = 0;
		int totalFileFail = 0;
		for (int i = 0; i < args.length; i++) {
			logger.info("arg[{}] : {}", i + 1, args[i]);
		}
		step = 1.4;
		// 加载系统占位符
		FieldUtil.init();
		step = 2;
		List<ALInputFileBean> aifList = new ArrayList<ALInputFileBean>();
		try {
			// 根据配置表，读取配置的需要导入的文件规则
			aifList = alService.findByGroupAndFile(args[0], args[1]);
		} catch (Exception e) {
			logger.error("findByTypeAndFile function error. please check table ALInputFile");
			logger.error(e.getMessage(), e);
		}
		step = 3;
		for (ALInputFileBean aif : aifList) {
			logger.info("");
			logger.info("======Config processing begin[{}]===========================================]", aif.getRecordId());
			logger.info("ALInputFile RecordID        : {}", aif.getRecordId());
			logger.info("ALInputFile GroupID         : {}", aif.getGroupId());
			logger.info("ALInputFile FilePath        : {}", aif.getFilePath());
			logger.info("ALInputFile FileRegExp      : {}", aif.getFileRegExp());
			logger.info("ALInputFile FileName        : {}", aif.getFileName());
			try {
				step = 4;
				String[] names = FileUtil.findFileNameRegexp(aif.getFilePath(), aif.getFileRegExp());
				if (names == null || names.length == 0) {
					logger.info("no match to the file, this ALInputFile is continue");
					logger.info("======Config processing end[{}]===============================================]", aif.getRecordId());
					continue;
				}
				logger.info("ALInputFile InputSql        : {}", aif.getInputSql());
				logger.info("ALInputFile dims count      : {}", aif.getAlInputFileFieldBeans().size());
				for (ALInputFileFieldBean aisfBean : aif.getAlInputFileFieldBeans()) {
					logger.info(aisfBean.toString());
				}
				logger.info("ALInputFile befores count   : {}", aif.getAlInputFileBeforeBeans().size());
				for (ALInputFileBeforeBean beforeBean : aif.getAlInputFileBeforeBeans()) {
					logger.info(beforeBean.toString());
				}
				logger.info("ALInputFile constants count : {}", aif.getAlInputFileConstantBeans().size());
				for (ALInputFileConstantBean constantBean : aif.getAlInputFileConstantBeans()) {
					logger.info(constantBean.toString());
				}
				logger.info("match files count               : {}", names.length);
				step = 5;
				// 将Dim表将数据读入内存
				alService.resetExtInfo(aif);
				// alService.resetExtDimKeyVal(aif, true);
				step = 6;
				// 找到匹配文件
				int fileCount = 1;
				for (String name : names) {
					long oneTimeBegin = System.currentTimeMillis();
					logger.info("======File processing begin[{}][{}]====================", name, fileCount);
					String lockName = lockDir + aif.getGroupId() + "_" + aif.getRecordId() + "_" + name + ".lock";
					logger.info("lockName " + lockName);
					boolean fileProcessingResult = false;
					Exception error = null;
					try {
						step = 7.1;
						// 设置转换当前选择文件的变量
						alService.setConvertInputConstantAndCurrentFile(aif, name);
						step = 7.9;
						// 不存在进程则杀掉锁并重新上锁
						// 存在进程则
						// // 给该配置上一个锁,如果重复则锁住
						// // 给该文件上锁，上锁失败表示有进程正在占用，然后直接跳过
						if (!LockUtil.lock(lockName)) {
							long oneTimeEnd = System.currentTimeMillis();
							long time = oneTimeEnd - oneTimeBegin;
							logger.info("=== file is lock， continue to this file");
							logger.info("=== one file cost time : {}", time);
							logger.info("======File processing end[{}]======================", name);
							continue;
						}
						step = 8;
						// 读取CSV文件
						List<String[]> list = CsvUtil.readCsv(aif.getFilePath() + name, CharUtils.toString(aif.getFileSeparator()));
						step = 9;
						// 转换成List<HashMap<String, String>>
						List<HashMap<String, String>> rowList = alService.getConvertCsvColumn2FieldPoint(list);
						step = 10;
						logger.info("=== total rows : [{}]", list.size());
						if (rowList.size() == 0) {
							throw new FileEmptyException("the file is empty");
						} else {
							logger.info("=== row 1 length: [{}]", list.get(0).length);
							if (list.get(0).length > FieldUtil.getDefaultSqlPlaceholderSize()) {
								logger.warn("=== Notice : row 1 length > System Default Sql Placeholder Size [" + FieldUtil.getDefaultSqlPlaceholderSize() + "], may be a problem.");
							}
							// 排序
							List<HashMap.Entry<String, String>> sorts = MapUtil.sorts(rowList.get(0).entrySet());
							for (int i = 0; i < sorts.size(); i++) {
							    logger.info("=== row 1 Point[{}] : {}", sorts.get(i).getKey(), sorts.get(i).getValue());
							}
						}
						step = 11;
						// 先转换占位非Dim字段
						alService.convertFileField2FieldPointByRowAndIsNotDim(rowList, aif.getAlInputFileFieldNotDimBeans());
						// 两种模式：1.事务模式，2.容错模式
						// 插入维度前先检测维度配置是否有多重维度依赖, 有则单个循环处理(多消耗), 没有则批量处理(高效)
						if (alService.checkMultiDimDepend(aif.getAlInputFileFieldDimBeans())) {
							logger.info("=== have multi dim depend");
							// 先单个获得插入维度SQL
							for (ALInputFileFieldBean dimBean : aif.getAlInputFileFieldDimBeans()) {
								step = 12;
								// 获得Dim插入维度SQL
								alService.setConvertDimSqls(rowList, dimBean);
								step = 13;
								// 插入维度
								alService.insertDim(dimBean);
								step = 14;
								// 后转换占位Dim字段
								alService.convertFileField2FieldPointByRowAndDim(rowList, dimBean);
							}
						} else {
							logger.info("=== not have multi dim depend");
							step = 12;
							// 先批量获得插入维度SQL
							alService.setConvertDimsSqls(rowList, aif.getAlInputFileFieldDimBeans());
							step = 13;
							// 循环插入维度
							for (ALInputFileFieldBean aisfBean : aif.getAlInputFileFieldDimBeans()) {
								alService.insertDim(aisfBean);
							}
							step = 14;
							// 后转换占位Dim字段
							alService.convertFileField2FieldPointByRowAndDims(rowList, aif.getAlInputFileFieldDimBeans());
						}
						step = 15;
						logger.info("================== insert data start ==================");
						if (aif.getDataSourceBean().getTargetSourceDBName() == null) {
							throw new UnknowTargetSourceException("the target source is not found");
						} else if (aif.getDataSourceBean().getTargetSourceDBName().equalsIgnoreCase("csv")) {
							step = 16;
							alService.convertInputSqlsByMysql(aif);
							step = 17;
							alService.placeholderReplaceByCsv(rowList, aif);
							step = 18;
							// 导出成后缀名为.al的文件
							alService.outputToFile(aif);
						} else if (aif.getDataSourceBean().getTargetSourceDBName().equalsIgnoreCase("mysql")) {
							step = 16.1;
							alService.convertSetInputBeforesSqls(aif);
							step = 16.2;
							// 执行在导入数据之前需要执行的sql语句
							for (ALInputFileBeforeBean beforeBean : aif.getAlInputFileBeforeBeans()) {
								alService.insertInputBefore(beforeBean);
							}
							step = 16.3;
							alService.convertInputSqlsByMysql(aif);
							step = 17;
							alService.placeholderReplaceByMysql(rowList, aif);
							step = 18;
							// 执行sql语句导入数据到mysql
							alService.insertInputFile(aif);
						} else {
							throw new UnknowTargetSourceException("the target source is not found, please check you TargetSourceDBName is correct!");
						}
						fileProcessingResult = true;
					} catch (UnknowDataSourceException e) {
						logger.error(e.getMessage(), e);
						error = e;
					} catch (FileEmptyException e) {
						logger.error(e.getMessage(), e);
						error = e;
					} catch (UnknowTargetSourceException e) {
						logger.error(e.getMessage(), e);
						error = e;
					} catch (RuntimeException e) {
						logger.error(e.getMessage(), e);
						error = e;
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						error = e;
					} finally {
						// 每处理完成一个文件后得清空insert sql和dim insert sql和befor sql
						alService.clearByOneFile(aif);
					}
					step = 19;
					String destinationPath = null;
					if (fileProcessingResult) {
						logger.info("=== insert data success");
						destinationPath = aif.getExtFilePathSuccess();
						totalFileSuccess++;
					} else {
						logger.info("=== insert data error");
						destinationPath = aif.getExtFilePathFail();
						totalFileFail++;
					}
					// 移动文件
					if (!isTest && FileUtil.moveFile(aif.getFilePath() + name, destinationPath)) {
						logger.info("=== move file [{}]", aif.getFilePath() + name);
						logger.info("===        to [{}]", destinationPath);
						logger.info("===    result [success]");
					} else {
						logger.info("=== move file [{}]", aif.getFilePath() + name);
						logger.info("===        to [{}]", destinationPath);
						logger.info("===    result [fail]");
					}
					step = 20;
					long oneTimeEnd = System.currentTimeMillis();
					long time = oneTimeEnd - oneTimeBegin;
					logger.info("=== one file cost time : {}", time);
					step = 21;
					if (time / 1000 / 60 > SysConfig.noticeFileProcessingMinute) {
						logger.warn("=== Notice : File processing time than expected");
					}
					// 解锁
					LockUtil.unLock(lockName);
					if (fileProcessingResult) {
						alLogService.successLog(aif);
					} else {
						alLogService.failLog(aif, error);
					}
					step = 100;
					logger.info("======File processing end[{}][{}]======================", name, fileCount);
					fileCount ++;
				}
			} catch (UnknowDataSourceException e) {
				alLogService.failLog(aif, e);
				logger.error(e.getMessage(), e);
			} catch (DirectoryNotFoundException e) {
				alLogService.failLog(aif, e);
				logger.error(e.getMessage(), e);
			} catch (Exception e) {
				alLogService.failLog(aif, e);
				logger.error(e.getMessage(), e);
			} finally {
				logger.info("======Config processing end[{}]===============================================]", aif.getRecordId());
				// 每处理完成一个InputFile配置规则后得所有查询到内存中的Dim,因为包含的KeyValMap数据
				alService.clearByOneConfig(aif);
			}
		}
		long totalTimeEnd = System.currentTimeMillis();
		logger.info("end the step             : {}", step);
		logger.info("total file config        : {}", aifList.size());
		logger.info("total file success count : {}", totalFileSuccess);
		logger.info("total file fail count    : {}", totalFileFail);
		logger.info("total file all count     : {}", totalFileSuccess + totalFileFail);
		logger.info("total cost time          : {}", totalTimeEnd - totalTimeBegin);
		logger.info("====================AutoLoad End====================");
	}

}
