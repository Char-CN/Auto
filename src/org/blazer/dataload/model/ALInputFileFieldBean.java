package org.blazer.dataload.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ALInputFileFieldBean implements ALConvert {

	private Integer recordId;

	private Integer fileId;

	private ALInputFileBean inputFileBean;

	private ALDataSourceBean dataSourceBean;

	private Integer dimFlag;

	private String fieldPointName;

	private String convertMethod;

	private String defaultValue;

	private String limitLength;

	private String oldFormat;

	private String newFormat;

	private String dimKey;

	private static final String DIMKEY_GROUP_DELIMITER = ",";

	private static final String DIMKEY_KEY_VAL_DELIMITER = ":";

	private String dimValue;

	private String dimTableName;

	private Integer sort;

	private Date cTime;

	private Date mTime;

	private HashMap<String, String> extDimKeyValMap;

	private HashMap<KeyCombine, String> extDimKeyCombineValMap;

	public HashMap<KeyCombine, String> getExtDimKeyCombineValMap() {
		return extDimKeyCombineValMap;
	}

	public void setExtDimKeyCombineValMap(HashMap<KeyCombine, String> extDimKeyCombineValMap) {
		this.extDimKeyCombineValMap = extDimKeyCombineValMap;
	}

	private String extDimKeyStr; // A1,A2,A3

	private String extDimKeyPointStr; // #A#,#B#,#C#

	private List<String> extDimKeyPointList; // #A#,#B#,#C#

	private String extInputSQL; // insert into aaa(...)

	private List<String> extInputSQLList; // insert aaa(...) ,insert aaa(...)

	private boolean isDim;

	public ALDataSourceBean getDataSourceBean() {
		return dataSourceBean;
	}

	public void setDataSourceBean(ALDataSourceBean dataSourceBean) {
		this.dataSourceBean = dataSourceBean;
	}

	public String getExtInputSQL() {
		return extInputSQL;
	}

	public void setExtInputSQL(String extInputSQL) {
		this.extInputSQL = extInputSQL;
	}

	public List<String> getExtInputSQLList() {
		return extInputSQLList;
	}

	public void setExtInputSQLList(List<String> extInputSQLList) {
		this.extInputSQLList = extInputSQLList;
	}

	public List<String> getExtDimKeyPointList() {
		return extDimKeyPointList;
	}

	public void setExtDimKeyPointList(List<String> extDimKeyPointList) {
		this.extDimKeyPointList = extDimKeyPointList;
	}

	public String getExtDimKeyStr() {
		return extDimKeyStr;
	}

	public void setExtDimKeyStr(String extDimKeyStr) {
		this.extDimKeyStr = extDimKeyStr;
	}

	public String getGroupDelimiter() {
		return DIMKEY_GROUP_DELIMITER;
	}

	public String getKeyValDelimiter() {
		return DIMKEY_KEY_VAL_DELIMITER;
	}

	public ALInputFileBean getInputFileBean() {
		return inputFileBean;
	}

	public void setInputFileBean(ALInputFileBean inputFileBean) {
		this.inputFileBean = inputFileBean;
	}

	public boolean isDim() {
		return isDim;
	}

	public boolean isNotDim() {
		return !isDim;
	}

	public void setDim(boolean isDim) {
		this.isDim = isDim;
	}

	public HashMap<String, String> getExtDimKeyValMap() {
		return extDimKeyValMap;
	}

	public void setExtDimKeyValMap(HashMap<String, String> extDimKeyValMap) {
		this.extDimKeyValMap = extDimKeyValMap;
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

	public Integer getDimFlag() {
		return dimFlag;
	}

	public void setDimFlag(Integer dimFlag) {
		this.dimFlag = dimFlag;
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

	public String getLimitLength() {
		return limitLength;
	}

	public void setLimitLength(String limitLength) {
		this.limitLength = limitLength;
	}

	public String getOldFormat() {
		return oldFormat;
	}

	public String getConvertMethod() {
		return convertMethod;
	}

	public void setConvertMethod(String convertMethod) {
		this.convertMethod = convertMethod;
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

	public String getDimKey() {
		return dimKey;
	}

	public void setDimKey(String dimKey) {
		this.dimKey = dimKey;
	}

	public String getDimValue() {
		return dimValue;
	}

	public void setDimValue(String dimValue) {
		this.dimValue = dimValue;
	}

	public String getDimTableName() {
		return dimTableName;
	}

	public void setDimTableName(String dimTableName) {
		this.dimTableName = dimTableName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public Date getmTime() {
		return mTime;
	}

	public void setmTime(Date mTime) {
		this.mTime = mTime;
	}

	public String getExtDimKeyPointStr() {
		return extDimKeyPointStr;
	}

	public void setExtDimKeyPointStr(String extDimKeyPointStr) {
		this.extDimKeyPointStr = extDimKeyPointStr;
	}

	@Override
	public String toString() {
		return "ALInputFileFieldBean [recordId=" + recordId + ", dimFlag=" + dimFlag + ", fieldPointName=" + fieldPointName + ", defaultValue="
				+ defaultValue + ", limitLength=" + limitLength + ", oldFormat=" + oldFormat + ", newFormat=" + newFormat + ", dimKey=" + dimKey
				+ ", dimValue=" + dimValue + ", dimTableName=" + dimTableName + ", sort=" + sort + ", isDim=" + isDim + "]";
	}

}
