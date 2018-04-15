package com.lee.server;

import javax.jws.WebService;

@WebService
public interface LoginMsgServer {
	public void sendMessage(String json_user);
}
