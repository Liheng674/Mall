package com.lee.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyPropertiesUtil {

	public static String loadProperties(String properties, String key) {
		Properties properties2 = new Properties();
		InputStream is = MyPropertiesUtil.class.getClassLoader().getResourceAsStream(properties);
		try {
			properties2.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String url = properties2.getProperty(key);
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return url;
	}

}
