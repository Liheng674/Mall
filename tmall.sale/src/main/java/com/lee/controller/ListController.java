package com.lee.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lee.bean.KEYWORDS_T_MALL_SKU;
import com.lee.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.lee.bean.OBJECT_T_MALL_ATTR;
import com.lee.bean.OBJECT_T_MALL_SKU;
import com.lee.bean.T_MALL_SKU_ATTR_VALUE;
import com.lee.service.AttrService;
import com.lee.service.ListService;
import com.lee.util.MyCacheUtil;
import com.lee.util.MyHttpGetUtil;
import com.lee.util.MyJsonUtil;
import com.lee.util.MyPropertiesUtil;

@Controller
public class ListController {

	@Autowired
	private AttrService attrService;
	
	@Autowired
	private ListService listService;
	
	/**
	* @Title: keywords
	* @Description: TODO(远程调用keywords服务)
	* @param @param keywords
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/keywords")
	public String keywords(String keywords, Model model) {
		// 远程调用solr检索sku
		String keywords_url = MyPropertiesUtil.loadProperties("ws.properties", "keywords_url")+"?keywords="+keywords;
		String doGet = "";
		try {
			doGet = MyHttpGetUtil.doGet(keywords_url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<KEYWORDS_T_MALL_SKU> list_sku = MyJsonUtil.json_to_list(doGet, KEYWORDS_T_MALL_SKU.class);
		model.addAttribute("keywords", keywords);
		model.addAttribute("list_sku", list_sku);
		return "search";
	}
	
	
	/**
	* @Title: asyn_list_sku
	* @Description: TODO(选择商品属性后异步加载商品信息)
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/asyn_list_sku")
	// @RequestParam("json_array[]") String[] json_array
	public ModelAndView asyn_list_sku(int flbh2, MODEL_T_MALL_SKU_ATTR_VALUE list_attr) {
		List<OBJECT_T_MALL_SKU> list_sku = new ArrayList<>();
		List<T_MALL_SKU_ATTR_VALUE> list_attr2 = list_attr.getList_attr();
		if (list_attr2 == null || list_attr2.size() == 0) {
			// 取消选中所有的属性
			String key = "class_2_" + flbh2;
			list_sku = MyCacheUtil.getList(key, OBJECT_T_MALL_SKU.class);
		} else {
			String[] keys = new String[list_attr2.size()];
			for (int i = 0; i < list_attr2.size(); i++) {
				// 属性名id
				int shxm_id = list_attr2.get(i).getShxm_id();
				// 属性值
				int shxzh_id = list_attr2.get(i).getShxzh_id();
				keys[i] = "attr_" + flbh2 + "_" + shxm_id + "_" + shxzh_id;
			}
			// 交叉检索, 返回生成的key
			String interKey = MyCacheUtil.interKeys(keys);
			// 缓存交叉检索
			list_sku = MyCacheUtil.getList(interKey, OBJECT_T_MALL_SKU.class);
			// db检索
			if (list_sku == null || list_sku.size() < 1) {
				list_sku = listService.get_sku_list_attr(list_attr.getList_attr(), flbh2);
				// 同步Redis
				for (int i = 0; i < list_attr2.size(); i++) {
					// 获取每个属性值key
					String key = keys[i];
					// 判断当前key是否存在
					boolean b = MyCacheUtil.if_exist(key);
					if (!b) {
						// 根据属性值id, 查询出对应的属性值集合(没用)
						// 每一个属性都要新生成一个sku属性值集合
						List<T_MALL_SKU_ATTR_VALUE> list_attr_for_redis = new ArrayList<>();
						T_MALL_SKU_ATTR_VALUE attr_value = new T_MALL_SKU_ATTR_VALUE();
						attr_value.setShxm_id(list_attr2.get(i).getShxm_id());
						attr_value.setShxzh_id(list_attr2.get(i).getShxzh_id());
						list_attr_for_redis.add(attr_value);
						// 单个key对应的sku集合
						List<OBJECT_T_MALL_SKU> list_sku_for_redis = listService.get_sku_list_attr(list_attr_for_redis,
								flbh2);
						// attr的key和sku的集合循环插入到redis
						MyCacheUtil.setKey(key, list_sku_for_redis);
					}
				}
			}
		}
		ModelAndView mv = new ModelAndView("sku_list");
		mv.addObject("list_sku", list_sku);
		mv.addObject("flbh2", flbh2);
		return mv;
	}
	
	/**
	* @Title: goto_list
	* @Description: TODO(主页面选择后展示属性列表)
	* @param @param model
	* @param @param flbh2
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/goto_list")
	public String goto_list(Model model, int flbh2) {
		// flbh2属性列表
		List<OBJECT_T_MALL_ATTR> list_attr = attrService.get_attr_list(flbh2);
		
		// flbh2商品列表
		// 缓存检索
		String key = "class_2_" + flbh2;
		List<OBJECT_T_MALL_SKU> list_sku = MyCacheUtil.getList(key, OBJECT_T_MALL_SKU.class);
		
		// db检索
		if (list_sku == null || list_sku.size() < 1) {
			list_sku =  listService.get_tm_list(flbh2);
			// 同步Redis
			MyCacheUtil.setKey(key, list_sku);
		}
		
		model.addAttribute("flbh2", flbh2);
		model.addAttribute("list_attr", list_attr);
		model.addAttribute("list_sku", list_sku);
		return "list";
	}

}
