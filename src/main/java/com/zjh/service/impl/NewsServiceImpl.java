package com.zjh.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjh.mapper.NewsMapper;
import com.zjh.pojo.News;
import com.zjh.pojo.NewsExample;
import com.zjh.pojo.NewsExample.Criteria;
import com.zjh.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	private NewsMapper newsMapper;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Override
	public PageInfo<News> list(String keyWord, Integer type, String startTime, String endTime, Integer page, Integer rows) throws Exception {
		NewsExample example = new NewsExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(0);
		if (keyWord != null && !keyWord.isEmpty()) {
			criteria.andTitleLike("%" + keyWord + "%");
		}
		if (type >0 ) {
			criteria.andTypeEqualTo(type);
		}
		if (startTime != null && !startTime.isEmpty()) {
			criteria.andCreatetimeGreaterThanOrEqualTo(sdf.parse(startTime));
		}
		if (endTime != null && !endTime.isEmpty()) {
			criteria.andCreatetimeLessThanOrEqualTo(sdf.parse(endTime));
		}
		example.setOrderByClause("createtime desc");
		PageHelper.startPage(page, rows);
		return new PageInfo<News>(newsMapper.selectByExample(example));
	}

	@Override
	public News getById(Integer id) {
		return newsMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int add(News mapper) {
		return newsMapper.insert(mapper);
	}

	@Override
	public int update(News mapper) {
		return newsMapper.updateByPrimaryKeyWithBLOBs(mapper);
	}

	@Override
	public News getNewsPrev(Integer newsId,Integer type) {
		return newsMapper.getNewsPrev(newsId,type);
	}

	@Override
	public News getNewsNext(Integer type) {
		return newsMapper.getNewsNext(type);
	}

}
