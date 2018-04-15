package com.lee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lee.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.lee.bean.T_MALL_USER_ACCOUNT;

@Controller
public class LoginController {

	@ResponseBody
	@RequestMapping("/login")
	public Object login(T_MALL_USER_ACCOUNT user, MODEL_T_MALL_SKU_ATTR_VALUE list_attr) {
		return "成功";
	}
}
