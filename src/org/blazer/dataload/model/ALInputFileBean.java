package org.blazer.dataload.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ALInputFileBean {

	private Integer recordId;

	private Integer groupId;

	private ALDataSourceBean dataSourceBean;

	private String fileName;

	private String filePath;

	private String fileRegExp;

	private String inputSql;

	private InputMode inputMode;

	private char fileSeparator;

	private Integer sort;

	private Integer enable;

	private Date cTime;

	private Date mTime;

	private List<ALInputFileFieldBean> alInputFileFieldBeans;

	private List<ALInputFileFieldBean> alInputFileFieldDimBeans;

	private List<ALInputFileFieldBean> alInputFileFieldNotDimBeans;

	private List<ALInputFileBeforeBean> alInputFileBeforeBeans;

	private List<ALInputFileAfterBean> alInputFileAfterBeans;

	private List<ALInputFileConstantBean> alInputFileConstantBeans;

	private String extFilePathSuccess;

	private String extFilePathFail;

	// private TargetSource extTargetSource;

	private List<String> extOldInputSQLList;

	private List<String> extInputSQLList;

	private HashMap<String, String> currentFileVar;

	public List<String> getExtOldInputSQLList() {
		return extOldInputSQLList;
	}

	public void setExtOldInputSQLList(List<String> extOldInputSQLList) {
		this.extOldInputSQLList = extOldInputSQLList;
	}

	public HashMap<String, String> getCurrentFileVar() {
		return currentFileVar;
	}

	public void setCurrentFileVar(HashMap<String, String> currentFileVar) {
		this.currentFileVar = currentFileVar;
	}

	public ALDataSourceBean getDataSourceBean() {
		return dataSourceBean;
	}

	public void setDataSourceBean(ALDataSourceBean dataSourceBean) {
		this.dataSourceBean = dataSourceBean;
	}

	public List<String> getExtInputSQLList() {
		return extInputSQLList;
	}

	public void setExtInputSQLList(List<String> extInputSQLList) {
		this.extInputSQLList = extInputSQLList;
	}

	// public TargetSource getExtTargetSource() {
	// return extTargetSource;
	// }
	//
	// public void setExtTargetSource(TargetSource extTargetSource) {
	// this.extTargetSource = extTargetSource;
	// }

	public String getExtFilePathSuccess() {
		return extFilePathSuccess;
	}

	public void setExtFilePathSuccess(String extFilePathSuccess) {
		this.extFilePathSuccess = extFilePathSuccess;
	}

	public String getExtFilePathFail() {
		return extFilePathFail;
	}

	public void setExtFilePathFail(String extFilePathFail) {
		this.extFilePathFail = extFilePathFail;
	}

	public List<ALInputFileFieldBean> getAlInputFileFieldNotDimBeans() {
		return alInputFileFieldNotDimBeans;
	}

	public void setAlInputFileFieldNotDimBeans(List<ALInputFileFieldBean> alInputFileFieldNotDimBeans) {
		this.alInputFileFieldNotDimBeans = alInputFileFieldNotDimBeans;
	}

	public List<ALInputFileBeforeBean> getAlInputFileBeforeBeans() {
		return alInputFileBeforeBeans;
	}

	public void setAlInputFileBeforeBeans(List<ALInputFileBeforeBean> alInputFileBeforeBeans) {
		this.alInputFileBeforeBeans = alInputFileBeforeBeans;
	}

	public List<ALInputFileAfterBean> getAlInputFileAfterBeans() {
		return alInputFileAfterBeans;
	}

	public void setAlInputFileAfterBeans(List<ALInputFileAfterBean> alInputFileAfterBeans) {
		this.alInputFileAfterBeans = alInputFileAfterBeans;
	}

	public List<ALInputFileConstantBean> getAlInputFileConstantBeans() {
		return alInputFileConstantBeans;
	}

	public void setAlInputFileConstantBeans(List<ALInputFileConstantBean> alInputFileConstantBeans) {
		this.alInputFileConstantBeans = alInputFileConstantBeans;
	}

	public List<ALInputFileFieldBean> getAlInputFileFieldDimBeans() {
		return alInputFileFieldDimBeans;
	}

	public void setAlInputFileFieldDimBeans(List<ALInputFileFieldBean> alInputFileFieldDimBeans) {
		this.alInputFileFieldDimBeans = alInputFileFieldDimBeans;
	}

	public List<ALInputFileFieldBean> getAlInputFileFieldBeans() {
		return alInputFileFieldBeans;
	}

	public void setAlInputFileFieldBeans(List<ALInputFileFieldBean> alInputFileFieldBeans) {
		this.alInputFileFieldBeans = alInputFileFieldBeans;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileRegExp() {
		return fileRegExp;
	}

	public void setFileRegExp(String fileRegExp) {
		this.fileRegExp = fileRegExp;
	}

	public String getInputSql() {
		return inputSql;
	}

	public void setInputSql(String inputSql) {
		this.inputSql = inputSql;
	}

	public InputMode getInputMode() {
		return inputMode;
	}

	public void setInputMode(InputMode inputMode) {
		this.inputMode = inputMode;
	}

	public char getFileSeparator() {
		return fileSeparator;
	}

	public void setFileSeparator(char fileSeparator) {
		this.fileSeparator = fileSeparator;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
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

	@Override
	public String toString() {
		return "ALInputFileBean [recordId=" + recordId + ", groupId=" + groupId + ", fileName=" + fileName + ", filePath=" + filePath + ", fileRegExp=" + fileRegExp
				+ ", inputSql=" + inputSql + ", fileSeparator=" + fileSeparator + ", sort=" + sort + ", enable=" + enable + "]";
	}

}
