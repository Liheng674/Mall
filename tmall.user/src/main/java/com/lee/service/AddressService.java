package com.lee.service;

import java.util.List;

import com.lee.bean.T_MALL_ADDRESS;
import com.lee.bean.T_MALL_USER_ACCOUNT;

public interface AddressService {

	T_MALL_ADDRESS select_address_byId(int id);

	List<T_MALL_ADDRESS> select_address_byUser(T_MALL_USER_ACCOUNT user);

}
