package com.lee.mapper;

import java.util.Map;

import com.lee.bean.T_MALL_SKU;

public interface SkuMapper {

	void insert_sku(T_MALL_SKU sku);

	void insert_sku_attr_value(Map<String, Object> map);

}
