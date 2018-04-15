package com.lee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lee.bean.T_MALL_ADDRESS;
import com.lee.bean.T_MALL_USER_ACCOUNT;
import com.lee.mapper.AddressMapper;
import com.lee.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressMapper addressMapper;
	
	@Override
	public T_MALL_ADDRESS select_address_byId(int id) {
		return addressMapper.select_address_byId(id);
	}

	@Override
	public List<T_MALL_ADDRESS> select_address_byUser(T_MALL_USER_ACCOUNT user) {
		return addressMapper.select_address_byUser(user);
	}

}
