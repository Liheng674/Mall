package com.lee.server;

public class TestServerImpl implements TestServer {

	@Override
	public String hello(String hello) {
		System.out.println("发送消息: " + hello);
		return "pong";
	}

}
