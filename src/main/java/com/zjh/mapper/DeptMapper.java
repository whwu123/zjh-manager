package com.zjh.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.zjh.pojo.Dept;

public interface DeptMapper {
	
	int insertSelective(Dept dept);
	
	int updateSelective(Dept dept);
	
	int deleteDeptByDid(@Param("dId")String dId);
	
	//取得分页总数量
	int getCountTotal(@Param("items")Map<String, Object> items);
		
	//取得分页数据
	List<Dept> getList(@Param("items")Map<String, Object> items);
	
	/**
	 * 根据ID查询部门信息
	 * 
	 */
	Dept getDeptByDid(@Param("dId")String dId);
		
}
