package com.zjh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjh.base.PageView;
import com.zjh.mapper.AffixMapper;
import com.zjh.pojo.Affix;
import com.zjh.pojo.AffixExample;
import com.zjh.service.AffixService;

@Service
public class AffixServiceImpl implements AffixService {

	@Autowired
	private AffixMapper affixMapper;
	
	@Override
	public PageInfo<Affix> list(String keyWord, Integer page, Integer rows) {
		AffixExample example = new AffixExample();
		example.createCriteria().andStatusEqualTo(0);
		PageHelper.startPage(page, rows);
		return new PageInfo<Affix>(affixMapper.selectByExample(example));
	}

	@Override
	public Affix getById(Integer id) {
		return affixMapper.selectByPrimaryKey(id);
	}

	@Override
	public int add(Affix mapper) {
		return affixMapper.insert(mapper);
	}

	@Override
	public int update(Affix mapper) {
		return affixMapper.updateByPrimaryKeySelective(mapper);
	}

	@Override
	public PageView<Affix> getList(String startTime, String endTime,
			String affixName, int pageNum, int pageSize) {
		PageView<Affix> pageView = new PageView<Affix>();
		//根据条件查询总数量
		int totalCount  = affixMapper.getTotalCount(startTime, endTime, affixName);
		//根据条件查询分页的数据
		List<Affix> affixs = affixMapper.getAffixData(startTime, endTime, affixName,(pageNum-1)*pageSize,pageSize);
		//计算出总页数
		int totalPage = (totalCount+pageSize-1)/pageSize;
		pageView.setTotalPage(totalPage);
		pageView.setPageNum(pageNum);
		pageView.setPageSize(pageSize);
		pageView.setListData(affixs);
		pageView.setTotalCount(totalCount);
		return pageView;
	}

	@Override
	public Affix findById(Integer id) {
		return affixMapper.findById(id);
	}

	@Override
	public int deleteAffix(int id, int status) {
		int result =affixMapper.deleteAffix(id,status);
		return result ;
	}

	@Override
	public PageView<Affix> getRecyleList(String startTime, String endTime,
			String affixName, int pageNum, int pageSize) {
		PageView<Affix> pageView = new PageView<Affix>();
		//根据条件查询总数量
		int totalCount  = affixMapper.getTotalReclycCount(startTime, endTime, affixName);
		//根据条件查询分页的数据
		List<Affix> affixs = affixMapper.getAffixReclycData(startTime, endTime, affixName,(pageNum-1)*pageSize,pageSize);
		//计算出总页数
		int totalPage = (totalCount+pageSize-1)/pageSize;
		pageView.setTotalPage(totalPage);
		pageView.setPageNum(pageNum);
		pageView.setPageSize(pageSize);
		pageView.setListData(affixs);
		pageView.setTotalCount(totalCount);
		return pageView;
	}

	@Override
	public int affixBack(Integer id) {
		Affix affix = affixMapper.selectByPrimaryKey(id);
		if(affix!=null){
			affix.setSize(0);
		}
		int result = affixMapper.affixback(affix.getId());
		if(result>0){
			return result;
		}
		return -1;
	}

	@Override
	public int affix_realdel(Integer id) {
		Affix affix = affixMapper.selectByPrimaryKey(id);
		if(affix!=null){
			int result = affixMapper.deleteByPrimaryKey(affix.getId());
			if(result>0){
				return result;
			}
		}
		
		return -1;
	}

	@Override
	public int insertAffixBackId(Affix record) {
		return affixMapper.insertAffixBackId(record);
	}

}
