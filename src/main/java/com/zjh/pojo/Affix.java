package com.zjh.pojo;

import java.util.Date;

public class Affix {
    private Integer id;

    private String affixname;

    private String affixtype;

    private Date affixtime;

    private Integer status;

    private byte[] content;
    
    private long size;
    private String descrption;
    
    private String affixpath;
    

	public String getAffixpath() {
		return affixpath;
	}

	public void setAffixpath(String affixpath) {
		this.affixpath = affixpath;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAffixname() {
        return affixname;
    }

    public void setAffixname(String affixname) {
        this.affixname = affixname == null ? null : affixname.trim();
    }

    public String getAffixtype() {
        return affixtype;
    }

    public void setAffixtype(String affixtype) {
        this.affixtype = affixtype == null ? null : affixtype.trim();
    }

    public Date getAffixtime() {
        return affixtime;
    }

    public void setAffixtime(Date affixtime) {
        this.affixtime = affixtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}