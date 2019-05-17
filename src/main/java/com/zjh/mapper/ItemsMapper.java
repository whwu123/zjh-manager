package com.zjh.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zjh.pojo.Items;

public interface ItemsMapper {
	
	int insertSelective(Items items);
	
	int deleteByPrimaryKey(@Param("fId")String fId);
	
	int updateSelective(Items items);
	
	//取得分页总数量
	int getCountTotal(Map<String, Object> items);
	
	//取得分页数据
	List<Items> getList(Map<String, Object> items);
	
	Items getItemsByKeyOrFid(@Param("fKey")String fKey,@Param("fId")String fId,@Param("fStatus")Integer fStatus);
	
	List<Items> getItemsByFpid(@Param("fPid")String fPid,@Param("fStatus")Integer fStatus);
}
