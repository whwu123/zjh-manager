package com.zjh.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjh.common.constant.StatusConstant;
import com.zjh.util.EncryptionTool;

@Controller
public class LoginController {

	@RequestMapping("login")
	@ResponseBody
	public String login(HttpServletRequest request,String userName,String password){
		
		byte[] a = EncryptionTool.decode(userName);
		userName = new String(a);
		byte[] b = EncryptionTool.decode(password);
		password = new String(b);
		if(userName.equalsIgnoreCase("admin")&&password.equals("11")){
			HttpSession session = request.getSession();
			session.setAttribute("userName", "Admin");
			session.setAttribute("role", "超级管理员");
			session.setAttribute(StatusConstant.LOGIN_SING, "超级管理员");
			return "success";
		}else{
			return "fail";
		}
	}
	
	@RequestMapping("logout")
	@ResponseBody
	public String login(HttpServletRequest request){
		request.getSession().removeAttribute("userName");
		request.getSession().removeAttribute("role");
		request.getSession().removeAttribute(StatusConstant.LOGIN_SING);
		request.getSession().invalidate();
		return "success";
	}
	
}
