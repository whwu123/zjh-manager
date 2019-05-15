package com.zjh.service;

import com.github.pagehelper.PageInfo;
import com.zjh.pojo.News;

public interface NewsService {

	/*分页搜索列表*/
	PageInfo<News> list(String keyWord, Integer type, String startTime, String endTime, Integer page, Integer rows) throws Exception;
	/*查询*/
	News getById(Integer id);
	/*添加*/
	int add(News mapper);
	/*更新*/
	int update(News mapper);
	
}
