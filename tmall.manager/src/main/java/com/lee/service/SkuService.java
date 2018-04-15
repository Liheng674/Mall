package com.lee.service;

import java.util.List;

import com.lee.bean.T_MALL_PRODUCT;
import com.lee.bean.T_MALL_SKU;
import com.lee.bean.T_MALL_SKU_ATTR_VALUE;

public interface SkuService {

	void save_sku(List<T_MALL_SKU_ATTR_VALUE> list, T_MALL_SKU sku, T_MALL_PRODUCT spu);

}
