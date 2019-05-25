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
	int getCountTotal(@Param("items")Map<String, Object> items);
	
	//取得分页数据
	List<Items> getList(@Param("items")Map<String, Object> items);
	
	Items selectItemsByFid(@Param("fId")String fId,@Param("fStatus")Integer fStatus);
	
	Items selectItemsByKey(@Param("fKey")String fKey);
	
	List<Items> getItemsByFpid(@Param("fPid")String fPid,@Param("fStatus")Integer fStatus);
	
	//取得友链前7个
	List<Items> getitemsYL();
}
