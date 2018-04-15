package com.lee.mapper;

import java.util.List;
import java.util.Map;

import com.lee.bean.OBJECT_T_MALL_SKU;

public interface ListMapper {

	List<OBJECT_T_MALL_SKU> select_tm_list(int flbh2);

	List<OBJECT_T_MALL_SKU> select_sku_list_attr(Map<String, Object> map);
}
