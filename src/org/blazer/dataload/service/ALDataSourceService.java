package org.blazer.dataload.service;

import java.util.List;
import java.util.Map;

import org.blazer.common.dao.JDBCDao;
import org.blazer.common.util.IntegerUtil;
import org.blazer.dataload.datasource.CustomJdbcDao;
import org.blazer.dataload.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 数据源服务类
 * 
 * @author heyunyang
 * 
 */
@Repository(value = "ALDataSourceService")
public class ALDataSourceService {

	private static final Logger logger = LoggerFactory.getLogger(ALDataSourceService.class);

	@Autowired
	private JDBCDao jdbcDao;

	public void initDataSource() {
		// String sql =
		// "select * from DW_MetaData.AL_DataSource where Enable = 1 and TargetSourceDBName = 'mysql'";
		String sql = "select * from AL_DataSource where Enable = 1";
		List<Map<String, Object>> resultList = jdbcDao.find(sql);
		if (resultList.size() == 0) {
			logger.info("not found datasource");
		}
		for (Map<String, Object> map : resultList) {
			Integer recordID = IntegerUtil.getInteger(map.get("RecordID"));
			String targetSourceDBName = StringUtil.getString(map.get("TargetSourceDBName"));
			String name = StringUtil.getString(map.get("Name"));
			String url = StringUtil.getString(map.get("Url"));
			String userName = StringUtil.getString(map.get("UserName"));
			String password = StringUtil.getString(map.get("Password"));
			try {
				CustomJdbcDao.addDataSource(recordID, targetSourceDBName, name, url, userName, password);
			} catch (Exception e) {
				logger.info("continue init datasource exception {}", e);
			}
		}
		logger.info("init datasource finish");
	}

}
