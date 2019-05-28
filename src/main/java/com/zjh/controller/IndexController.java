package com.zjh.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zjh.base.BaseController;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model,HttpSession session) {
		return "index";
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(Model model,HttpSession session) {
		return "util/welcome";
	}
	
}
