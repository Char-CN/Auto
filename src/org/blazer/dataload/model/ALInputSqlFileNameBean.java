package org.blazer.dataload.model;

public class ALInputSqlFileNameBean {

	private Integer recordId;

	private Integer fileId;

	private String fieldPointName;

	private String fileNameRegExp;

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

	public String getFieldPointName() {
		return fieldPointName;
	}

	public void setFieldPointName(String fieldPointName) {
		this.fieldPointName = fieldPointName;
	}

	public String getFileNameRegExp() {
		return fileNameRegExp;
	}

	public void setFileNameRegExp(String fileNameRegExp) {
		this.fileNameRegExp = fileNameRegExp;
	}

	@Override
	public String toString() {
		return "ALInputFileSqlFileNameBean [recordId=" + recordId + ", fileId=" + fileId + ", fieldPointName=" + fieldPointName + ", fileNameRegExp="
				+ fileNameRegExp + "]";
	}

}
