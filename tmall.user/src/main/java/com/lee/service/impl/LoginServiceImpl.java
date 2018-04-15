package com.lee.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lee.bean.T_MALL_USER_ACCOUNT;
import com.lee.mapper.LoginMapper;
import com.lee.service.LoginService;
import com.lee.util.MyRoutingDataSource;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper loginMapper;
	
	@Override
	public T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user) {
		// 启用数据源1
		MyRoutingDataSource.setKey("1");
		return loginMapper.select_user(user);
	}

	@Override
	public T_MALL_USER_ACCOUNT login2(T_MALL_USER_ACCOUNT user) {
		// 启用数据源2
		MyRoutingDataSource.setKey("2");
		try {
			TimeUnit.SECONDS.sleep(8);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loginMapper.select_user(user);
	}

}
