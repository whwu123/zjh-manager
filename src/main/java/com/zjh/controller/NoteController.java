package com.zjh.controller;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zjh.base.BaseController;
import com.zjh.base.PageView;
import com.zjh.common.constant.StatusConstant;
import com.zjh.pojo.Affix;
import com.zjh.pojo.Note;
import com.zjh.service.NoteService;

@Controller
@RequestMapping("/note")
public class NoteController extends BaseController {

	@Autowired
	private NoteService noteService;
	
	@RequestMapping(value = "/note_list", method = RequestMethod.GET)
	public String list(String startTime,String endTime,String content,Integer pageNum,Model model) throws Exception {
		if(pageNum==null){
			pageNum=1;
		}
		int pageSize = 10;
		PageView<Note> pageView   = noteService.getList(startTime, endTime, content, pageNum, pageSize);
		model.addAttribute("pageView", pageView);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("affixName", content);
		
		return "note/note_list";
	}
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public String select(Model model, Integer id) {
		// 新增页/更新页
		id = id == null ? 0 : id;
		if (id > 0){
			model.addAttribute("note", noteService.getById(id));
		}
		return "note/select";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(Integer id) {
		int a = noteService.deleteNote(id);
		System.out.println(a);
		if(a>0){
			return "ok";
		}else{
			return "fail";
		}
	}
	/**
	 * 查看留言
	 */
	@RequestMapping("note_view")
	public String note_view(Integer id,Model model){
		if(id!=null){
			model.addAttribute("note", noteService.getById(id));
		}
		return "note/note_view";
	}
	
	
}
