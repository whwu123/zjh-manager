package com.zjh.pojo;

import java.util.Date;

public class Dept {
	private String dId;
	private String dCode;
	private String dName;
	private Integer dSort;
	private Integer dStatus;
	private String dDesc;
	private Date dCreateTime;
	public String getdId() {
		return dId;
	}
	public void setdId(String dId) {
		this.dId = dId;
	}
	public String getdCode() {
		return dCode;
	}
	public void setdCode(String dCode) {
		this.dCode = dCode;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public Integer getdSort() {
		return dSort;
	}
	public void setdSort(Integer dSort) {
		this.dSort = dSort;
	}
	public Integer getdStatus() {
		return dStatus;
	}
	public void setdStatus(Integer dStatus) {
		this.dStatus = dStatus;
	}
	public String getdDesc() {
		return dDesc;
	}
	public void setdDesc(String dDesc) {
		this.dDesc = dDesc;
	}
	public Date getdCreateTime() {
		return dCreateTime;
	}
	public void setdCreateTime(Date dCreateTime) {
		this.dCreateTime = dCreateTime;
	}
	public Dept(String dId, String dCode, String dName, Integer dSort, Integer dStatus, String desc, Date dCreateTime) {
		super();
		this.dId = dId;
		this.dCode = dCode;
		this.dName = dName;
		this.dSort = dSort;
		this.dStatus = dStatus;
		this.dDesc = desc;
		this.dCreateTime = dCreateTime;
	}
	public Dept() {
		super();
	}
	
}
