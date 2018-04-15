package com.lee.mapper;

import java.util.List;
import java.util.Map;

import com.lee.bean.T_MALL_PRODUCT;

public interface SpuMapper {

	void insertIntoProduct(T_MALL_PRODUCT tmp);

	void insert_spu_img(Map<String, Object> map);

	List<T_MALL_PRODUCT> select_spu_list(Map<String, Object> map);

}
