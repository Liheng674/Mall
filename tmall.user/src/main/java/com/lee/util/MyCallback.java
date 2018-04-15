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
		
		// 自己需要写的代码
		String username = wspc.getIdentifier();
		String password = ""; // 查询数据库或配置文件
		if ("cxf".equals(username)) {
			password = "123";
		}
		
		wspc.setPassword(password);
	}
}
