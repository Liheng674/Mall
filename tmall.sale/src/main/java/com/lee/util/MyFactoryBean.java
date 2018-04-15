package com.lee.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean<T> implements FactoryBean<T> {

	private String url;
	private Class<T> t;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Class<?> getT() {
		return t;
	}

	public void setT(Class<T> t) {
		this.t = t;
	}

	public static <T> T getMyWs(String url, Class<T> t) {
		JaxWsProxyFactoryBean jwfb = new JaxWsProxyFactoryBean();
		// 设置访问地址
		jwfb.setAddress(url);
		// 设置服务接口类
		jwfb.setServiceClass(t);
		
		// 加入安全协议
		// 需要安全拦截才加
		if ("TestServer".equals(t.getSimpleName())) {
			Map<String, Object> map = new HashMap<>();
			map.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
			map.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordText");
			map.put("user", "username");
			// MyCallback.class.getName()的全类名, 对应服务器端的配置
			map.put(WSHandlerConstants.PW_CALLBACK_CLASS, MyCallback.class.getName());
			WSS4JOutInterceptor wsoi = new WSS4JOutInterceptor(map);
			jwfb.getOutInterceptors().add(wsoi);
		}
		
		T bean = (T) jwfb.create();
		return bean;
	}

	@Override
	public T getObject() throws Exception {
		return getMyWs(url, t);
	}

	@Override
	public Class<?> getObjectType() {
		return t;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
