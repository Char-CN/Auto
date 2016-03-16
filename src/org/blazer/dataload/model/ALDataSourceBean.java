package org.blazer.dataload.model;

import org.blazer.common.dao.Dao;

public class ALDataSourceBean {

	private Integer recordId;

	private String targetSourceDBName;

	private String name;

	private String url;

	private String userName;

	private String password;

	private Dao dao;

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getTargetSourceDBName() {
		return targetSourceDBName;
	}

	public void setTargetSourceDBName(String targetSourceDBName) {
		this.targetSourceDBName = targetSourceDBName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
