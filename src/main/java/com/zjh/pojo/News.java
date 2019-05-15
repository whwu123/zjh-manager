package com.zjh.pojo;

import java.util.Date;

public class News {
    private Integer id;

    private String title;

    private String author;

    private String sources;

    private Date createtime;

    private Integer type;

    private Integer status;
    
    private String sysimg;
    
    private String img;
    
    private String smallimg;
    
    private String description;

    private String content;
    
    private int affixid;
   
    private String staticpage;
    
    public String getStaticpage() {
		return staticpage;
	}

	public void setStaticpage(String staticpage) {
		this.staticpage = staticpage;
	}

	public int getAffixid() {
		return affixid;
	}

	public void setAffixid(int affixid) {
		this.affixid = affixid;
	}

	public String getSysimg() {
		return sysimg;
	}

	public void setSysimg(String sysimg) {
		this.sysimg = sysimg;
	}

	public Integer getId() {
        return id;
    }

    public String getSmallimg() {
		return smallimg;
	}

	public void setSmallimg(String smallimg) {
		this.smallimg = smallimg;
	}

	public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources == null ? null : sources.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}