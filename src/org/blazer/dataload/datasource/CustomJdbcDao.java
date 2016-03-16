package org.blazer.dataload.datasource;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.dbcp.BasicDataSource;
import org.blazer.common.dao.Dao;
import org.blazer.common.dao.TransactionDao;
import org.blazer.dataload.exception.UnknowDataSourceException;
import org.blazer.dataload.model.ALDataSourceBean;
import org.springframework.stereotype.Repository;

@Repository(value = "CustomJdbcDao")
public class CustomJdbcDao {

	private final static Map<Integer, ALDataSourceBean> dataSourceMap = new HashMap<Integer, ALDataSourceBean>();

	private CustomJdbcDao() {
	}

	public static void addDataSource(Integer id, String targetSourceDBName, String name, String url, String userName, String password) {
		if (dataSourceMap.containsKey(id)) {
			return;
		}
		ALDataSourceBean dataSourceBean = new ALDataSourceBean();
		dataSourceBean.setTargetSourceDBName(targetSourceDBName);
		dataSourceBean.setName(name);
		dataSourceBean.setUrl(url);
		dataSourceBean.setUserName(userName);
		dataSourceBean.setPassword(password);
		dataSourceBean.setRecordId(id);
		if ("csv".equalsIgnoreCase(targetSourceDBName)) {
			// do nothing
		} else {
			BasicDataSource bds = new BasicDataSource();
			bds.setUsername(userName);
			bds.setUrl(url);
			bds.setPassword(password);
			// 增加事务管理
			// DataSourceTransactionManager txManager = new DataSourceTransactionManager(bds);
			// JdbcTemplate jt = new JdbcTemplate(bds);
			Dao dao = new TransactionDao(bds);
			//dao.setJdbcTemplate(jt);
			dataSourceBean.setDao(dao);
		}
		dataSourceMap.put(id, dataSourceBean);
	}

	public static Dao getDao(Integer id) throws UnknowDataSourceException {
		if (!dataSourceMap.containsKey(id)) {
			throw new UnknowDataSourceException("not found the datasource id[" + id + "]");
		}
		return dataSourceMap.get(id).getDao();
	}

	public static ALDataSourceBean getDataSourceBean(Integer id) throws UnknowDataSourceException {
		if (!dataSourceMap.containsKey(id)) {
			throw new UnknowDataSourceException("not found the datasource id[" + id + "]");
		}
		return dataSourceMap.get(id);
	}

	public static Set<Integer> getKeySet() {
		return dataSourceMap.keySet();
	}

}
