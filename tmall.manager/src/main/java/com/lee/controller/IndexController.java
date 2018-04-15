package com.lee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/index")
	public String index(String url, String title, Model model) {
		model.addAttribute("url", url);
		model.addAttribute("title", title);
		return "main";
	}
	
	@RequestMapping("/goSpu")
	public String goSpu() {
		return "spu";
	}
	
	@RequestMapping("/goAttr")
	public String goAttr() {
		return "attr";
	}
	
	@RequestMapping("/goSku")
	public String goSku() {
		return "sku";
	}
}
