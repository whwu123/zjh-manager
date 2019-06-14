package com.zjh.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjh.base.PageView;
import com.zjh.mapper.RolesMapper;
import com.zjh.pojo.Dept;
import com.zjh.pojo.Roles;
import com.zjh.service.RolesService;
@Service
public class RolesServiceImpl implements RolesService {
	
	@Autowired
	private RolesMapper rolesMapper;
	@Override
	public boolean insertSelective(Roles roles) {
		if(rolesMapper.insertSelective(roles)>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteRolesByRid(String rId) {
		if(rolesMapper.deleteRolesByDid(rId)>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateSelective(Roles roles) {
		if(rolesMapper.updateSelective(roles)>0) {
			return true;
		}
		return false;
	}

	@Override
	public PageView<Roles> getList(String rCode, String rName, Integer rStatus, String startTime, String endTime,
			int pageNum, int pageSize) {
		PageView<Roles> pageView = new PageView<Roles>();
		Map<String, Object> itemsMap = new HashMap<String, Object>();
		List<Roles> list = null;
		//根据条件查询总数量
		itemsMap.put("rCode", rCode);
		itemsMap.put("rName", rName);
		itemsMap.put("rStatus", rStatus);
		itemsMap.put("startTime", startTime);
		itemsMap.put("endTime", endTime);
		int totalCount  = rolesMapper.getCountTotal(itemsMap);
		pageView.setTotalCount(totalCount);
		//根据条件查询分页的数据
		if(totalCount>0) {
			itemsMap.put("pagenum", (pageNum-1)*pageSize);
			itemsMap.put("pagesize",pageSize);
			list = rolesMapper.getList(itemsMap);
			//计算出总页数
			int totalPage = (totalCount+pageSize-1)/pageSize;
			pageView.setTotalPage(totalPage);
			pageView.setPageNum(pageNum);
			pageView.setPageSize(pageSize);
			pageView.setListData(list);
		}
		return pageView;
	}

	@Override
	public Roles getRolesByRid(String rId) {
		return rolesMapper.getRolesByRid(rId);
	}

}
