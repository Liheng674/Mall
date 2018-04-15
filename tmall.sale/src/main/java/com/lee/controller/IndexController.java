package com.lee.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/go_login")
	public String go_login() {
		return "login";
	}
	
	@RequestMapping("/go_loginCheck")
	public String go_loginCheck() {
		return "login_checkOrder";
	}
	
	/**
	* @Title: goto_logout
	* @Description: TODO(注销)
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/goto_logout")
	public String goto_logout(HttpSession session) {
		// 
		session.invalidate();
		return "redirect:/go_login.do";
	}
	
}
