package com.lee.service;

import java.util.List;

import com.lee.bean.DETAIL_T_MALL_SKU;
import com.lee.bean.T_MALL_SKU;

public interface ItemServcie {

	DETAIL_T_MALL_SKU get_sku_detail(int sku_id);

	List<T_MALL_SKU> get_sku_list(int shp_id);

}
