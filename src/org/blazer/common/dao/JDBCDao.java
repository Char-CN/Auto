package org.blazer.common.dao;

import java.util.List;
import java.util.Map;

import org.blazer.common.util.Count;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 基础的JDBC Dao
 * 
 * @author heyunyang
 * 
 */
@Repository(value = "JDBCDao")
@Transactional
public class JDBCDao {

	private static final Logger logger = LoggerFactory.getLogger(JDBCDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Map<String, Object>> find(String findSql) {
		logger.debug("=====find===== SQL : {}", findSql);
		return jdbcTemplate.queryForList(findSql);
	}

	public List<Map<String, Object>> find(String findSql, Object... args) {
		logger.debug("=====find===== SQL : {}", findSql);
		for (int i = 0; i < args.length; i++) {
			logger.debug("=====param {} : {}", i, args[i]);
		}
		return jdbcTemplate.queryForList(findSql, args);
	}

	public Map<String, Object> findByUnique(String findSql) {
		logger.debug("=====findByUnique===== SQL : {}", findSql);
		return jdbcTemplate.queryForList(findSql).get(0);
	}

	public void batchUpdate(String[] sqls) throws RuntimeException {
		logger.debug("=====batchUpdate===== Length : {}", sqls.length);
		try {
			for (String sql : sqls) {
				update(sql);
			}
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public void batchUpdate(List<String> sqls, Count count) throws RuntimeException {
		logger.debug("=====batchUpdate===== Size : {}", sqls.size());
		try {
			for (String sql : sqls) {
				update(sql);
				count.add(1);
			}
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public void update(String updateSql, Object... args) throws RuntimeException {
		logger.debug("=====update===== SQL : {}", updateSql);
		for (int i = 0; i < args.length; i++) {
			logger.debug("=====param {} : {}", i, args[i]);
		}
		try {
			jdbcTemplate.update(updateSql, args);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public void update(String updateSql) throws RuntimeException {
		logger.debug("=====update===== SQL : {}", updateSql);
		try {
			jdbcTemplate.update(updateSql);
		} catch (RuntimeException e) {
			throw e;
		}
	}

}
