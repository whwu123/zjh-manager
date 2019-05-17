package com.zjh.service;

import java.util.List;
import com.zjh.base.PageView;
import com.zjh.pojo.Items;

public interface ItemsService {
	boolean insertSelective(Items items);
	
	boolean deleteByPrimaryKey(String fId);
	
	boolean updateSelective(Items items);
	
	PageView<Items> getList(String fPid,String fKey,String fTitle,Integer fType,String fOpennewTab,
			Integer fStatus,String startTime,String endTime,int pageNum,int pageSize);
	
	Items getItemsByKeyOrFid(String fKey,String fId,Integer fStatus);
	
	List<Items> getItemsByFpid(String fPid,Integer fStatus);
}
