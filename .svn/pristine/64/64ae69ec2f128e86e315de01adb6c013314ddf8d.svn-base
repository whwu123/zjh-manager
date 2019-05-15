package com.zjh.service;

import com.github.pagehelper.PageInfo;
import com.zjh.base.PageView;
import com.zjh.pojo.Affix;
import com.zjh.pojo.Note;

public interface NoteService {

	/*分页搜索列表*/
	PageInfo<Note> list(String keyWord, Integer page, Integer rows);
	/*查询*/
	Note getById(Integer id);
	
	PageView<Note> getList(String startTime,String endTime,String content,int pageNum,int pageSize);
	

	int deleteNote(Integer id);
	
}
