package com.zjh.mapper;

import org.apache.ibatis.annotations.Param;

import com.zjh.pojo.Items;

public interface ItemsMapper {
	
	int insertSelective(Items items);
	
	int deleteByPrimaryKey(@Param("fId")String fId);
	
	int updateSelective(Items items);
}
