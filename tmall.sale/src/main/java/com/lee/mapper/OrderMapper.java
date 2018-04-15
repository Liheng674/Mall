package com.lee.mapper;

import java.util.Map;

import com.lee.bean.OBJECT_T_MALL_FLOW;
import com.lee.bean.OBJECT_T_MALL_ORDER;
import com.lee.bean.T_MALL_ORDER_INFO;

public interface OrderMapper {

	void insert_order(Map<String, Object> map);

	void insert_flow(Map<String, Object> map);

	void insert_infos(Map<String, Object> map);

	void update_order(OBJECT_T_MALL_ORDER order);

	void update_flow(OBJECT_T_MALL_FLOW flow);

	void delete_cart(Map<String, Object> mapCart);

	long select_count_kc(T_MALL_ORDER_INFO info);

	long select_kc(T_MALL_ORDER_INFO t_MALL_ORDER_INFO);

	long select_kc_lock(T_MALL_ORDER_INFO t_MALL_ORDER_INFO);

	void update_kc(T_MALL_ORDER_INFO info);

}
