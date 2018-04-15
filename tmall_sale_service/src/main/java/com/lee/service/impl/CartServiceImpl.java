package com.lee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lee.bean.T_MALL_SHOPPINGCAR;
import com.lee.bean.T_MALL_USER_ACCOUNT;
import com.lee.mapper.CartMapper;
import com.lee.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartMapper cartMapper;
	
	@Override
	public boolean if_exist_cart(T_MALL_SHOPPINGCAR cart) {
		int cart_count = cartMapper.select_exist_cart(cart);
		if(cart_count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void updata_cart(T_MALL_SHOPPINGCAR cart) {
		cartMapper.update_cart(cart);
	}

	@Override
	public void insert_cart(T_MALL_SHOPPINGCAR cart) {
		cartMapper.insert_cart(cart);
	}

	@Override
	public List<T_MALL_SHOPPINGCAR> get_list_cart_byUser(T_MALL_USER_ACCOUNT user) {
		return cartMapper.select_list_cart_byUser(user);
	}

}
