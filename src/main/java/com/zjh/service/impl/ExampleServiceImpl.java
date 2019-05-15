package com.zjh.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjh.mapper.ExampleMapper;
import com.zjh.pojo.Example;
import com.zjh.pojo.ExampleExample;
import com.zjh.pojo.ExampleExample.Criteria;
import com.zjh.service.ExampleService;

@Service
public class ExampleServiceImpl implements ExampleService {

	@Autowired
	private ExampleMapper exampleMapper;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Override
	public PageInfo<Example> list(String keyWord, String startTime, String endTime, Integer page, Integer rows) throws Exception {
		ExampleExample example = new ExampleExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(0);
		if (keyWord != null && !keyWord.isEmpty()) {
			criteria.andTitleLike("%" + keyWord + "%");
		}
		if (startTime != null && !startTime.isEmpty()) {
			criteria.andCreatetimeGreaterThanOrEqualTo(sdf.parse(startTime));
		}
		if (endTime != null && !endTime.isEmpty()) {
			criteria.andCreatetimeLessThanOrEqualTo(sdf.parse(endTime));
		}
		example.setOrderByClause("createtime desc");
		PageHelper.startPage(page, rows);
		return new PageInfo<Example>(exampleMapper.selectByExample(example));
	}

	@Override
	public Example getById(Integer id) {
		return exampleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int add(Example mapper) {
		return exampleMapper.insert(mapper);
	}

	@Override
	public int update(Example mapper) {
		return exampleMapper.updateByPrimaryKeyWithBLOBs(mapper);
	}

}
