package com.lee.mapper;

import java.util.List;

import com.lee.bean.KEYWORDS_T_MALL_SKU;

public interface ClassMapper {
	List<KEYWORDS_T_MALL_SKU> select_list_by_flbh2(int flbh2);
}
