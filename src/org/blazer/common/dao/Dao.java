package org.blazer.common.dao;

import java.util.List;
import java.util.Map;

import org.blazer.common.util.Count;

/**
 * Dao接口规范
 * 
 * @author heyunyang
 * 
 */
public interface Dao {

	List<Map<String, Object>> find(String findSql);

	List<Map<String, Object>> find(String findSql, Object... args);

	Map<String, Object> findByUnique(String findSql);

	void batchUpdateTranstaion(String[] sqls) throws RuntimeException;

	void batchUpdateTranstaion(String[] sqls, Integer interval) throws RuntimeException;

	void batchUpdateTranstaion(String[] sqls, Count count) throws RuntimeException;

	void batchUpdateFaultTolerant(String[] sqls, Count count) throws RuntimeException;

	void batchUpdateTranstaion(List<String> sqls) throws RuntimeException;

	void batchUpdateTranstaion(List<String> sqls, Integer interval) throws RuntimeException;

	void batchUpdateTranstaion(List<String> sqls, Count count) throws RuntimeException;

	void batchUpdateFaultTolerant(List<String> sqls, Count count) throws RuntimeException;

	void update(String updateSql, Object... args) throws RuntimeException;

	void update(String updateSql) throws RuntimeException;

}
