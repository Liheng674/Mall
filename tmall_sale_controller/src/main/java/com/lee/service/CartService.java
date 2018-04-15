package com.lee.service;

import java.util.List;

import com.lee.bean.T_MALL_SHOPPINGCAR;
import com.lee.bean.T_MALL_USER_ACCOUNT;

public interface CartService {

	boolean if_exist_cart(T_MALL_SHOPPINGCAR cart);

	void updata_cart(T_MALL_SHOPPINGCAR cart);

	void insert_cart(T_MALL_SHOPPINGCAR cart);

	List<T_MALL_SHOPPINGCAR> get_list_cart_byUser(T_MALL_USER_ACCOUNT user);

}
