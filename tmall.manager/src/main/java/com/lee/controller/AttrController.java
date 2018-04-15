package com.lee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lee.bean.MODEL_T_MALL_ATTR;
import com.lee.bean.OBJECT_T_MALL_ATTR;
import com.lee.service.AttrService;

@Controller
public class AttrController {
	@Autowired
	private AttrService attrService;
	
	/**
	* @Title: get_attr_list_json
	* @Description: TODO(easyUI获取表格)
	* @param @param model
	* @param @param flbh2
	* @param @return    参数
	* @return Object    返回类型
	* @throws
	 */
	@ResponseBody
	@RequestMapping("/get_attr_list_json")
	public Object get_attr_list_json(Model model, int flbh2) {
		List<OBJECT_T_MALL_ATTR> list_attr = attrService.get_attr_list(flbh2);
		return list_attr;
	}
	
	/**
	* @Title: get_attr_list
	* @Description: TODO(查询属性内嵌页)
	* @param @param model
	* @param @param flbh2
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/get_attr_list")
	public String get_attr_list(Model model, int flbh2) {
		List<OBJECT_T_MALL_ATTR> list_attr = attrService.get_attr_list(flbh2);
		model.addAttribute("flbh2", flbh2);
		model.addAttribute("list_attr", list_attr);
		return "attrListInner";
	}
	
	@RequestMapping("/goAttrAdd")
	public String goSpuAdd(Model model, int flbh2) {
		model.addAttribute("flbh2", flbh2);
		return "attrAdd";
	}
	
	@RequestMapping("/arrtAdd")
	public ModelAndView arrtAdd(int flbh2, MODEL_T_MALL_ATTR list_attr) {
		// 插入到attr
		attrService.insertAttr(flbh2, list_attr.getList_attr());
		// 保存数据
		ModelAndView mv = new ModelAndView("redirect:/index.do");
		mv.addObject("flbh2", flbh2);
		mv.addObject("url", "goAttrAdd.do?flbh2=" + flbh2);
		mv.addObject("title", "添加商品属性");
		return mv;
	}
}
