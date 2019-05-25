package com.zjh.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zjh.base.PageView;
import com.zjh.mapper.ItemsMapper;
import com.zjh.pojo.Items;
import com.zjh.service.ItemsService;
@Service
public class ItemsServiceImpl implements ItemsService {
	@Autowired
	private ItemsMapper itemsMapper;
	@Override
	public boolean insertSelective(Items items) {
		if(itemsMapper.insertSelective(items)>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteByPrimaryKey(String fId) {
		if(itemsMapper.deleteByPrimaryKey(fId)>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateSelective(Items items) {
		if(itemsMapper.updateSelective(items)>0) {
			return true;
		}
		return false;
	}

	@Override
	public PageView<Items> getList(String fPid, String fKey, String fTitle, Integer fType, String fOpennewTab,
			Integer fStatus, String startTime, String endTime, int pageNum, int pageSize) {
		PageView<Items> pageView = new PageView<Items>();
		Map<String, Object> itemsMap = new HashMap<String, Object>();
		List<Items> list = null;
		//根据条件查询总数量
		itemsMap.put("fPid", fPid);
		itemsMap.put("fKey", fKey);
		itemsMap.put("fTitle", fTitle);
		itemsMap.put("fType", fType);
		itemsMap.put("fOpennewTab", fOpennewTab);
		itemsMap.put("fStatus", fStatus);
		itemsMap.put("startTime", startTime);
		itemsMap.put("endTime", endTime);
		int totalCount  = itemsMapper.getCountTotal(itemsMap);
		pageView.setTotalCount(totalCount);
		//根据条件查询分页的数据
		if(totalCount>0) {
			itemsMap.put("pagenum", (pageNum-1)*pageSize);
			itemsMap.put("pagesize",pageSize);
			list = itemsMapper.getList(itemsMap);
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
	public List<Items> getItemsByFpid(String fPid, Integer fStatus) {
		if(fStatus==null) {
			fStatus = 1;
		}
		return itemsMapper.getItemsByFpid(fPid, fStatus);
	}

	@Override
	public Items selectItemsByFid(String fId, Integer fStatus) {
		return itemsMapper.selectItemsByFid(fId, fStatus);
	}

	@Override
	public Items selectItemsByKey(String fKey) {
		return itemsMapper.selectItemsByKey(fKey);
	}

	@Override
	public List<Items> getitemsYL() {
		return itemsMapper.getitemsYL();
	}

}
