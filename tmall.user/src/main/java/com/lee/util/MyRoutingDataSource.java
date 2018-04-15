package com.lee.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
* @ClassName: MyRoutingDataSource
* @Description: TODO(自定义数据源切换)
* @author A18ccms a18ccms_gmail_com
* @date 2018年4月2日 下午6:15:35
*
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {

	//private static String key;
	// 并发时每个线程有独立的key
	private static ThreadLocal<String> key = new ThreadLocal<>();
	
	public static String getKey() {
		return key.get();
	}


	public static void setKey(String key_in) {
		key.set(key_in);;
	}


	@Override
	protected Object determineCurrentLookupKey() {
		return key.get();
	}

}
