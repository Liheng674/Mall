package com.lee.service;

import com.lee.bean.OBJECT_T_MALL_ORDER;
import com.lee.bean.T_MALL_ADDRESS;
import com.lee.exception.OverSaleException;

public interface OrderService {

	void save_order(OBJECT_T_MALL_ORDER order, T_MALL_ADDRESS get_address);

	void pay_success(OBJECT_T_MALL_ORDER order) throws OverSaleException;

}
