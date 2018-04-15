package com.lee.service;

import java.util.List;

import com.lee.bean.T_MALL_PRODUCT;

public interface SpuService {

	void insertIntoProduct(T_MALL_PRODUCT tmp, List<String> list, Integer img_cover);

	List<T_MALL_PRODUCT> get_spu_list(int pp_id, int flbh2);

	
}
