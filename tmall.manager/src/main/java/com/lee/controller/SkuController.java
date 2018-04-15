package com.lee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lee.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.lee.bean.OBJECT_T_MALL_ATTR;
import com.lee.bean.T_MALL_PRODUCT;
import com.lee.bean.T_MALL_SKU;
import com.lee.service.AttrService;
import com.lee.service.SkuService;

@Controller
public class SkuController {

	@Autowired
	private AttrService attrService;
	
	@Autowired
	private SkuService skuService;
	
	@RequestMapping("/goSkuAdd")
	public String goSkuAdd(int flbh1, int flbh2, Model model) {
		// 页面加载属性和属性值列表
		List<OBJECT_T_MALL_ATTR> list_attr = attrService.get_attr_list(flbh2);
		model.addAttribute("list_attr", list_attr);
		
		model.addAttribute("flbh1", flbh1);
		model.addAttribute("flbh2", flbh2);
		return "skuAdd";
	}
	
	@RequestMapping("/save_sku")
	public ModelAndView save_sku(MODEL_T_MALL_SKU_ATTR_VALUE list_attr, T_MALL_SKU sku, T_MALL_PRODUCT spu) {
		skuService.save_sku(list_attr.getList_attr(), sku, spu);
		
		ModelAndView mv = new ModelAndView("redirect:/goSkuAdd.do");
		mv.addObject("flbh1", spu.getFlbh1());
		mv.addObject("flbh2", spu.getFlbh2());
		return mv;
	}
}
