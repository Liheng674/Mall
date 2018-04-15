package com.lee.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lee.bean.T_MALL_PRODUCT;
import com.lee.bean.T_MALL_SKU;
import com.lee.bean.T_MALL_SKU_ATTR_VALUE;
import com.lee.mapper.SkuMapper;
import com.lee.service.SkuService;

@Service
public class SkuServiceImpl implements SkuService {

	@Autowired
	private SkuMapper skuMapper;
	
	@Override
	public void save_sku(List<T_MALL_SKU_ATTR_VALUE> list, T_MALL_SKU sku, T_MALL_PRODUCT spu) {
		// 保存sku信息. 返回值主键
		skuMapper.insert_sku(sku);
		List<T_MALL_SKU_ATTR_VALUE> list_attr = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getShxm_id() != 0 && list.get(i).getShxzh_id() != 0) {
				list_attr.add(list.get(i));
			}
		}
		// 根据返回的主键批量插入属性关联表
		Map<String, Object> map = new HashMap<>();
		map.put("list_attr", list_attr);
		map.put("shp_id", sku.getShp_id());
		map.put("sku_id", sku.getId());
		skuMapper.insert_sku_attr_value(map);
	}

}
