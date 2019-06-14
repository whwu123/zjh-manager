package com.zjh.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.zjh.pojo.Dept;
import com.zjh.pojo.Roles;

public interface RolesMapper {
	
	int insertSelective(Roles roles);
	
	int updateSelective(Roles roles);
	
	int deleteRolesByDid(@Param("rId")String rId);
	
	//取得分页总数量
	int getCountTotal(@Param("items")Map<String, Object> items);
		
	//取得分页数据
	List<Roles> getList(@Param("items")Map<String, Object> items);
	
	/**
	 * 根据ID查询角色信息
	 * 
	 */
	Roles getRolesByRid(@Param("rId")String rId);
		
}
