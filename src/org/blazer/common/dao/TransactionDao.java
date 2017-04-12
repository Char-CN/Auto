package org.blazer.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.blazer.common.util.Count;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 事务Dao，使用该Dao可以将批量导入并且事务控制，支持多线程
 * 
 * @author heyunyang
 * 
 */
public class TransactionDao implements Dao {

	private static final Logger logger = LoggerFactory.getLogger(TransactionDao.class);

	private JdbcTemplate jdbcTemplate;
	private PlatformTransactionManager platformTransactionManager;
	private DefaultTransactionDefinition transactionDefinition;
	private ThreadLocal<TransactionStatus> transcationStatus = new ThreadLocal<TransactionStatus>();

	public TransactionDao(javax.sql.DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		transactionDefinition = new DefaultTransactionDefinition();
		transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
		transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		platformTransactionManager = new DataSourceTransactionManager(this.jdbcTemplate.getDataSource());
	}

	private void beginTranstaion() {
		TransactionStatus tmp = platformTransactionManager.getTransaction(transactionDefinition);
		transcationStatus.set(tmp);
	}

	private void commit() throws RuntimeException {
		TransactionStatus tmp = transcationStatus.get();
		if (tmp == null) {
			throw new RuntimeException("no transcation");
		}
		platformTransactionManager.commit(tmp);
		transcationStatus.remove();
	}

	private void rollback() throws RuntimeException {
		TransactionStatus tmp = transcationStatus.get();
		if (tmp == null) {
			throw new RuntimeException("no transcation");
		}
		platformTransactionManager.rollback(tmp);
		transcationStatus.remove();
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

	public void batchUpdateTranstaion(String[] sqls) throws RuntimeException {
		batchUpdateTranstaion(sqls, 5000);
	}

	public void batchUpdateTranstaion(String[] sqls, Integer interval) throws RuntimeException {
		Count count = new Count(0, interval);
		batchUpdateTranstaion(sqls, count);
	}

	public void batchUpdateTranstaion(String[] sqls, Count count) throws RuntimeException {
		logger.debug("=====batchUpdateTranstaion===== Length : {}", sqls.length);
		try {
			logger.info("batchUpdateTranstaion begin");
			beginTranstaion();
			for (String sql : sqls) {
				count.add(1);
				jdbcTemplate.update(sql);
				if (count.modZero())
					logger.info("imported " + count.getCount());
			}
			if (count.getCount() == 0 || !count.modZero())
				logger.info("imported " + count.getCount());
			commit();
			logger.info("batchUpdateTranstaion commit");
		} catch (RuntimeException e) {
			rollback();
			logger.error("batchUpdateTranstaion rollback");
			throw e;
		}
	}

	public void batchUpdateFaultTolerant(String[] sqls, Count count) throws RuntimeException {
		logger.debug("=====batchUpdateFaultTolerant===== Length : {}", sqls.length);
		try {
			logger.info("batchUpdateFaultTolerant begin");
			for (String sql : sqls) {
				count.add(1);
				try {
					jdbcTemplate.update(sql);
				} catch (Exception e) {
					count.add(-1);
				}
				if (count.modZero())
					logger.info("imported " + count.getCount());
			}
			if (count.getCount() == 0 || !count.modZero())
				logger.info("imported " + count.getCount());
			logger.info("batchUpdateFaultTolerant commit");
		} catch (RuntimeException e) {
			logger.error("batchUpdateFaultTolerant rollback");
			throw e;
		}
	}

	public void batchUpdateTranstaion(List<String> sqls) throws RuntimeException {
		batchUpdateTranstaion(sqls, 5000);
	}

	public void batchUpdateTranstaion(List<String> sqls, Integer interval) throws RuntimeException {
		Count count = new Count(0, interval);
		batchUpdateTranstaion(sqls, count);
	}

	public void batchUpdateTranstaion(List<String> sqls, Count count) throws RuntimeException {
		logger.debug("=====batchUpdateTranstaion===== Length : {}", sqls.size());
		try {
			logger.info("batchUpdateTranstaion begin");
			beginTranstaion();
			for (String sql : sqls) {
				count.add(1);
				jdbcTemplate.update(sql);
				if (count.modZero())
					logger.info("imported " + count.getCount());
			}
			if (count.getCount() == 0 || !count.modZero())
				logger.info("imported " + count.getCount());
			commit();
			logger.info("batchUpdateTranstaion commit");
		} catch (RuntimeException e) {
			rollback();
			logger.error("batchUpdateTranstaion rollback");
			throw e;
		}
	}

	public void batchUpdateFaultTolerant(List<String> sqls, Count count) throws RuntimeException {
		logger.debug("=====batchUpdateFaultTolerant===== Length : {}", sqls.size());
		try {
			logger.info("batchUpdateFaultTolerant begin");
			for (String sql : sqls) {
				count.add(1);
				try {
					jdbcTemplate.update(sql);
				} catch (Exception e) {
					count.add(-1);
				}
				if (count.modZero())
					logger.info("imported " + count.getCount());
			}
			if (count.getCount() == 0 || !count.modZero())
				logger.info("imported " + count.getCount());
			logger.info("batchUpdateFaultTolerant commit");
		} catch (RuntimeException e) {
			logger.error("batchUpdateFaultTolerant rollback");
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

	public static void main(String[] args) {
		BasicDataSource bds = new BasicDataSource();
		bds.setUrl("jdbc:mysql://127.0.0.1:3306/DW_MetaData");
		bds.setUsername("root");
		bds.setPassword("root");
		TransactionDao dao = new TransactionDao(bds);
		String sql1 = "insert into test(name,lastperiod,age,remark) values('哈哈','102','17','{.}');";
		String sql2 = "insert into test(name,lastperiod,age,remark) values('哈hyyhyy哈test2','111',null,'{.}');";
		String sql3 = "insert into test(name,lastperiod,age,remark) values(null,'702',null,'{.}');";
		String sql4 = "insert into test(name,lastperiod,age,remark) values('null','702',null,'{.}');";
		String sql5 = "insert into test(name,lastperiod,age,remark) values('null','702',null,'{.}');";
		String sql6 = "insert into test(asd) values(asd);";
		String[] sqls = new String[] { sql1, sql2, sql6, sql3, sql4, sql5 };
		dao.batchUpdateTranstaion(sqls);
		dao.update(sql1);
	}

}
