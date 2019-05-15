package com.zjh.base;

import java.util.List;

public class PageView<T> {

	
	private int pageSize; 
	private int pageNum;
	private int totalPage;
	private int totalCount;
	private List<T> listData;
	
	public PageView() {
		super();
	}


	public PageView(int pageSize, int pageNum, int totalPage, int totalCount,
			List<T> listData) {
		super();
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
		this.listData = listData;
	}

	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getListData() {
		return listData;
	}

	public void setListData(List<T> listData) {
		this.listData = listData;
	}

	public static void main(String[] args) {
		int a = 3;
		int b =5;
		int c = (a+b-1)/5;
		System.out.println(c);
		
		
	}
	
	
}
