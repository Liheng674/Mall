package com.lee.mapper;

import java.util.List;
import java.util.Map;

import com.lee.bean.DETAIL_T_MALL_SKU;
import com.lee.bean.T_MALL_SKU;

public interface ItemMapper {

	DETAIL_T_MALL_SKU select_sku_detail(Map<String, Object> map);

	List<T_MALL_SKU> select_sku_list(Map<String, Object> map);

}
