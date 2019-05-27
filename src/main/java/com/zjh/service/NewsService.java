package com.zjh.service;

import org.apache.ibatis.annotations.Param;

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
	/**
	 * 取得上一篇
	 * @param newsId
	 * @return
	 */
	News getNewsPrev(Integer newsId,Integer type);
	/**
	 * 取得第二篇
	 * @param newsId
	 * @return
	 */
	News getNewsNext(Integer type);
	
}
