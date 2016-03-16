package org.blazer.dataload.model;

public class ALInputFileConstantBean implements ALConvert {

	private Integer recordId;

	private Integer fileId;

	private ALInputFileBean inputFileBean;

	private String fieldPointName;

	private String defaultValue;

	private String convertMethod;

	private String limitLength;

	private String oldFormat;

	private String newFormat;

	private Integer sort;

	private String resultValue;

	public String getResultValue() {
		return resultValue;
	}

	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public ALInputFileBean getInputFileBean() {
		return inputFileBean;
	}

	public void setInputFileBean(ALInputFileBean inputFileBean) {
		this.inputFileBean = inputFileBean;
	}

	public String getFieldPointName() {
		return fieldPointName;
	}

	public void setFieldPointName(String fieldPointName) {
		this.fieldPointName = fieldPointName;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getConvertMethod() {
		return convertMethod;
	}

	public void setConvertMethod(String convertMethod) {
		this.convertMethod = convertMethod;
	}

	public String getLimitLength() {
		return limitLength;
	}

	public void setLimitLength(String limitLength) {
		this.limitLength = limitLength;
	}

	public String getOldFormat() {
		return oldFormat;
	}

	public void setOldFormat(String oldFormat) {
		this.oldFormat = oldFormat;
	}

	public String getNewFormat() {
		return newFormat;
	}

	public void setNewFormat(String newFormat) {
		this.newFormat = newFormat;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "ALInputFileConstant [recordId=" + recordId + ", fileId=" + fileId + ", fieldPointName=" + fieldPointName + ", defaultValue="
				+ defaultValue + ", convertMethod=" + convertMethod + ", limitLength=" + limitLength + ", oldFormat=" + oldFormat + ", newFormat="
				+ newFormat + ", sort=" + sort + "]";
	}

}
