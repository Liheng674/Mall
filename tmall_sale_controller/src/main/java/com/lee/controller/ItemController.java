package com.lee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lee.bean.DETAIL_T_MALL_SKU;
import com.lee.bean.T_MALL_SKU;
import com.lee.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/goto_sku_detail")
	public String goto_sku_detail(int sku_id, int shp_id, Model model) {
		// 查询商品详细信息对象
		DETAIL_T_MALL_SKU detail_sku = itemService.get_sku_detail(sku_id);
		// 查询相同spu下的其他sku信息
		List<T_MALL_SKU> list_sku = itemService.get_sku_list(shp_id);
		
		model.addAttribute("detail_sku", detail_sku);
		model.addAttribute("list_sku", list_sku);
		return "sku_detail";
	}
}
