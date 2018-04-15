package com.lee.service;

import java.util.List;

import com.lee.bean.OBJECT_T_MALL_SKU;
import com.lee.bean.T_MALL_SKU_ATTR_VALUE;

public interface ListService {

	List<OBJECT_T_MALL_SKU> get_tm_list(int flbh2);

	List<OBJECT_T_MALL_SKU> get_sku_list_attr(List<T_MALL_SKU_ATTR_VALUE> list_attr, int flbh2);

}
