package com.lee.service;

import java.util.List;

import com.lee.bean.OBJECT_T_MALL_ATTR;

public interface AttrService {

	void insertAttr(int flbh2, List<OBJECT_T_MALL_ATTR> list_attr);

	List<OBJECT_T_MALL_ATTR> get_attr_list(int flbh2);

}
