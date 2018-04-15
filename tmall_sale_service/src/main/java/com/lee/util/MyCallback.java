package com.lee.util;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

public class MyCallback implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		// 配置username和password, 非校验
		WSPasswordCallback wspc = (WSPasswordCallback) callbacks[0];
		
		// 把用户名和密码加入到wss协议中
		wspc.setIdentifier("cxf");
		String password = "123"; // 查询数据库或配置文件
		
		wspc.setPassword(password);
	}
}
