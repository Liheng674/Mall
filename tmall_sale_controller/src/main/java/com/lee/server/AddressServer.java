package com.lee.server;

import javax.jws.WebService;

import com.lee.bean.T_MALL_USER_ACCOUNT;

@WebService
public interface AddressServer {

	public String select_address_byId(int id);
	
	public String select_address_byUser(T_MALL_USER_ACCOUNT user);
}
