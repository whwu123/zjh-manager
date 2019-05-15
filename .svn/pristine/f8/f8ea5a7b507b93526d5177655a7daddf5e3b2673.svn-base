package com.zjh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjh.base.PageView;
import com.zjh.mapper.NoteMapper;
import com.zjh.pojo.Affix;
import com.zjh.pojo.Note;
import com.zjh.pojo.NoteExample;
import com.zjh.service.NoteService;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteMapper noteMapper;
	
	@Override
	public PageInfo<Note> list(String keyWord, Integer page, Integer rows) {
		NoteExample example = new NoteExample();
		example.createCriteria().andStatusEqualTo(0);
		PageHelper.startPage(page, rows);
		return new PageInfo<Note>(noteMapper.selectByExample(example));
	}

	@Override
	public Note getById(Integer id) {
		return noteMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageView<Note> getList(String startTime, String endTime,
			String content, int pageNum, int pageSize) {
		PageView<Note> pageView = new PageView<Note>();
		//根据条件查询总数量
		int totalCount  = noteMapper.getTotalCount(startTime, endTime, content);
		//根据条件查询分页的数据
		List<Note> notes = noteMapper.getNoteData(startTime, endTime, content,(pageNum-1)*pageSize,pageSize);
		//计算出总页数
		int totalPage = (totalCount+pageSize-1)/pageSize;
		pageView.setTotalPage(totalPage);
		pageView.setPageNum(pageNum);
		pageView.setPageSize(pageSize);
		pageView.setListData(notes);
		pageView.setTotalCount(totalCount);
		return pageView;
	}

	@Override
	public int deleteNote(Integer id) {
		
		return noteMapper.deleteByPrimaryKey(id);
	}

	


}
