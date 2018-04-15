package com.lee.server.impl;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.lee.bean.T_MALL_ADDRESS;
import com.lee.bean.T_MALL_USER_ACCOUNT;
import com.lee.server.AddressServer;
import com.lee.service.AddressService;

public class AddressServerImpl implements AddressServer {

	@Autowired
	private AddressService addressService;
	
	@Override
	@Path("/address")
	@GET
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public String select_address_byId(int id) {
		T_MALL_ADDRESS address = addressService.select_address_byId(id);
		Gson gson = new Gson();
		return gson.toJson(address);
	}

	@Override
	@Path("/address")
	@GET
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public String select_address_byUser(@BeanParam T_MALL_USER_ACCOUNT user) {
		List<T_MALL_ADDRESS> list_addr  = addressService.select_address_byUser(user);
		Gson gson = new Gson();
		return gson.toJson(list_addr);
	}


}
