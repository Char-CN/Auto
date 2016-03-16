package org.blazer.dataload.model;

/**
 * 该接口为统一转换之用
 * 
 * @author heyunyang
 * 
 */
public interface ALConvert {

	public ALInputFileBean getInputFileBean();

	public String getFieldPointName();

	public String getConvertMethod();

	public String getDefaultValue();

	public String getLimitLength();

	public String getOldFormat();

	public String getNewFormat();

}
