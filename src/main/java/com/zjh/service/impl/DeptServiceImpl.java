package com.zjh.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjh.base.PageView;
import com.zjh.mapper.DeptMapper;
import com.zjh.pojo.Dept;
import com.zjh.pojo.Items;
import com.zjh.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptMapper deptMapper;
	@Override
	public boolean insertSelective(Dept items) {
		if(deptMapper.insertSelective(items)>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteDeptByDid(String dId) {
		if(deptMapper.deleteDeptByDid(dId)>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateSelective(Dept items) {
		if(deptMapper.updateSelective(items)>0) {
			return true;
		}
		return false;
	}

	public PageView<Dept> getList(String dCode,String dName,Integer dStatus,String startTime,
			String endTime,int pageNum,int pageSize) {
		PageView<Dept> pageView = new PageView<Dept>();
		Map<String, Object> itemsMap = new HashMap<String, Object>();
		List<Dept> list = null;
		//根据条件查询总数量
		itemsMap.put("dCode", dCode);
		itemsMap.put("dName", dName);
		itemsMap.put("dStatus", dStatus);
		itemsMap.put("startTime", startTime);
		itemsMap.put("endTime", endTime);
		int totalCount  = deptMapper.getCountTotal(itemsMap);
		pageView.setTotalCount(totalCount);
		//根据条件查询分页的数据
		if(totalCount>0) {
			itemsMap.put("pagenum", (pageNum-1)*pageSize);
			itemsMap.put("pagesize",pageSize);
			list = deptMapper.getList(itemsMap);
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
	public Dept getDeptByDid(String dId) {
		return deptMapper.getDeptByDid(dId);
	}

}
