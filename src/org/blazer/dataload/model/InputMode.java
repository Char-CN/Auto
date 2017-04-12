package org.blazer.dataload.model;

/**
 * 导入模式：分两种
 * 
 * 1.事务模式：导入csv文件数据中有一条失败，即导入失败。
 * 
 * 2.容错模式：导入csv文件数据中有失败的条数不终止，继续循环下一条的数据导入。
 * 
 * @author hyy
 *
 */
public enum InputMode {

	Transaction, FaultTolerant

}
