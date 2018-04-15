package com.lee.server;

import javax.jws.WebService;

@WebService
public interface TestServer {

	public String hello(String hello);
}
