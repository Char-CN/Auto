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

	void batchUpdateTransaction(String[] sqls) throws RuntimeException;

	void batchUpdateTransaction(String[] sqls, Integer interval) throws RuntimeException;

	void batchUpdateTransaction(String[] sqls, Count count) throws RuntimeException;

	void batchUpdateFaultTolerant(String[] sqls, Count count) throws RuntimeException;

	void batchUpdateTransaction(List<String> sqls) throws RuntimeException;

	void batchUpdateTransaction(List<String> sqls, Integer interval) throws RuntimeException;

	void batchUpdateTransaction(List<String> sqls, Count count) throws RuntimeException;

	void batchUpdateFaultTolerant(List<String> sqls, Count count) throws RuntimeException;

	void update(String updateSql, Object... args) throws RuntimeException;

	void update(String updateSql) throws RuntimeException;

}
