package com.zjh.pojo;

import java.util.Date;

public class Items {
	private String fId;
	private String fPid;
	private String fKey;
	private String fTitle;
	private String fContent;
	private String fDescrition;
	private Integer fType;
	private String fUrl;
	private String fOpennewTab;
	private String fImgsrc;
	private String fStyle;
	private Date fCreatetime;
	private Integer fStatus;
	private Integer fSort;
	public Items(String fId, String fPid, String fKey, String fTitle, String fContent, String fDescrition,
			Integer fType, String fUrl, String fOpennewTab, String fImgsrc, String fStyle, Date fCreatetime,
			Integer fStatus, Integer fSort) {
		super();
		this.fId = fId;
		this.fPid = fPid;
		this.fKey = fKey;
		this.fTitle = fTitle;
		this.fContent = fContent;
		this.fDescrition = fDescrition;
		this.fType = fType;
		this.fUrl = fUrl;
		this.fOpennewTab = fOpennewTab;
		this.fImgsrc = fImgsrc;
		this.fStyle = fStyle;
		this.fCreatetime = fCreatetime;
		this.fStatus = fStatus;
		this.fSort = fSort;
	}
	public Items() {
		super();
	}
	public String getfId() {
		return fId;
	}
	public void setfId(String fId) {
		this.fId = fId;
	}
	public String getfPid() {
		return fPid;
	}
	public void setfPid(String fPid) {
		this.fPid = fPid;
	}
	public String getfKey() {
		return fKey;
	}
	public void setfKey(String fKey) {
		this.fKey = fKey;
	}
	public String getfTitle() {
		return fTitle;
	}
	public void setfTitle(String fTitle) {
		this.fTitle = fTitle;
	}
	public String getfContent() {
		return fContent;
	}
	public void setfContent(String fContent) {
		this.fContent = fContent;
	}
	public String getfDescrition() {
		return fDescrition;
	}
	public void setfDescrition(String fDescrition) {
		this.fDescrition = fDescrition;
	}
	public Integer getfType() {
		return fType;
	}
	public void setfType(Integer fType) {
		this.fType = fType;
	}
	public String getfUrl() {
		return fUrl;
	}
	public void setfUrl(String fUrl) {
		this.fUrl = fUrl;
	}
	public String getfOpennewTab() {
		return fOpennewTab;
	}
	public void setfOpennewTab(String fOpennewTab) {
		this.fOpennewTab = fOpennewTab;
	}
	public String getfImgsrc() {
		return fImgsrc;
	}
	public void setfImgsrc(String fImgsrc) {
		this.fImgsrc = fImgsrc;
	}
	public String getfStyle() {
		return fStyle;
	}
	public void setfStyle(String fStyle) {
		this.fStyle = fStyle;
	}
	public Date getfCreatetime() {
		return fCreatetime;
	}
	public void setfCreatetime(Date fCreatetime) {
		this.fCreatetime = fCreatetime;
	}
	public Integer getfStatus() {
		return fStatus;
	}
	public void setfStatus(Integer fStatus) {
		this.fStatus = fStatus;
	}
	public Integer getfSort() {
		return fSort;
	}
	public void setfSort(Integer fSort) {
		this.fSort = fSort;
	}
	
	
}   
