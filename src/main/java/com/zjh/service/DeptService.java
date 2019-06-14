package com.zjh.service;

import org.apache.ibatis.annotations.Param;

import com.zjh.base.PageView;
import com.zjh.pojo.Dept;

public interface DeptService {
	boolean insertSelective(Dept items);
	
	boolean deleteDeptByDid(String dId);
	
	boolean updateSelective(Dept items);
	
	PageView<Dept> getList(String dCode,String dName,Integer dStatus,
			String startTime,String endTime,int pageNum,int pageSize);
	
	/**
	 * 根据ID查询部门信息
	 * 
	 */
	Dept getDeptByDid(String dId);
}
