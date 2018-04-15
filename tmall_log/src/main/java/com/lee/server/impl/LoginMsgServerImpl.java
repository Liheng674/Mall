package com.lee.server.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.lee.server.LoginMsgServer;
import com.lee.service.LoginMessage;

public class LoginMsgServerImpl implements LoginMsgServer {

	@Autowired
	private LoginMessage loginMessage;

	@Override
	public void sendMessage(String json_user) {
		loginMessage.sendMessage(json_user);
	}

}
