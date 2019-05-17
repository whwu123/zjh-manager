package com.zjh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zjh.base.BaseController;

@Controller
@RequestMapping("admin")
public class AdminController extends BaseController {
	
	@RequestMapping(value = "/system-base", method = RequestMethod.GET)
	public String base(Model model,String keyWord, Integer page) {
		model.addAttribute("keyWord", keyWord);
		model.addAttribute("page", page == null ? 1 : page);
		model.addAttribute("rows", rows);
		return "admin/base_list";
	}
}
