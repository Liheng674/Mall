package com.lee.mapper;

import java.util.List;

import com.lee.bean.T_MALL_SHOPPINGCAR;
import com.lee.bean.T_MALL_USER_ACCOUNT;

public interface CartMapper {

	void update_cart(T_MALL_SHOPPINGCAR cart);

	void insert_cart(T_MALL_SHOPPINGCAR cart);

	int select_exist_cart(T_MALL_SHOPPINGCAR cart);

	List<T_MALL_SHOPPINGCAR> select_list_cart_byUser(T_MALL_USER_ACCOUNT user);

}
