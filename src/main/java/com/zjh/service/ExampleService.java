package com.zjh.service;

import com.github.pagehelper.PageInfo;
import com.zjh.pojo.Example;

public interface ExampleService {

	/*分页搜索列表*/
	PageInfo<Example> list(String keyWord, String startTime, String endTime, Integer page, Integer rows) throws Exception;
	/*查询*/
	Example getById(Integer id);
	/*添加*/
	int add(Example mapper);
	/*更新*/
	int update(Example mapper);
	
}
