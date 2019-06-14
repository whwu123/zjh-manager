package com.zjh.pojo;

import java.util.Date;

public class Roles {
	private String rId;
	private String rCode;
	private String rName;
	private Integer rSort;
	private Integer rStatus;
	private Date rDate;
	
	public String getrId() {
		return rId;
	}

	public void setrId(String rId) {
		this.rId = rId;
	}

	public String getrCode() {
		return rCode;
	}

	public void setrCode(String rCode) {
		this.rCode = rCode;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public Integer getrSort() {
		return rSort;
	}

	public void setrSort(Integer rSort) {
		this.rSort = rSort;
	}

	public Integer getrStatus() {
		return rStatus;
	}

	public void setrStatus(Integer rStatus) {
		this.rStatus = rStatus;
	}

	public Date getrDate() {
		return rDate;
	}

	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}

	public Roles() {
		super();
	}

	public Roles(String rId, String rCode, String rName, Integer rSort, Integer rStatus, Date rDate) {
		super();
		this.rId = rId;
		this.rCode = rCode;
		this.rName = rName;
		this.rSort = rSort;
		this.rStatus = rStatus;
		this.rDate = rDate;
	}
	
}
