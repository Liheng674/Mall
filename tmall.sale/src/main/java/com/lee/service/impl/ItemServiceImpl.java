package com.lee.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lee.bean.DETAIL_T_MALL_SKU;
import com.lee.bean.T_MALL_SKU;
import com.lee.mapper.ItemMapper;
import com.lee.service.ItemServcie;

@Service
public class ItemServiceImpl implements ItemServcie {

	@Autowired
	private ItemMapper itemMapper;
	
	@Override
	public DETAIL_T_MALL_SKU get_sku_detail(int sku_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("sku_id", sku_id);
		return itemMapper.select_sku_detail(map);
	}

	@Override
	public List<T_MALL_SKU> get_sku_list(int shp_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("shp_id", shp_id);
		return itemMapper.select_sku_list(map);
	}

}
