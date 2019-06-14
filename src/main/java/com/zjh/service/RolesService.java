package com.zjh.service;

import com.zjh.base.PageView;
import com.zjh.pojo.Roles;

public interface RolesService {
	boolean insertSelective(Roles roles);
	
	boolean deleteRolesByRid(String rId);
	
	boolean updateSelective(Roles roles);
	
	PageView<Roles> getList(String rCode,String rName,Integer rStatus,
			String startTime,String endTime,int pageNum,int pageSize);
	/**
	 * 根据ID查询部门信息
	 * 
	 */
	Roles getRolesByRid(String rId);
}
