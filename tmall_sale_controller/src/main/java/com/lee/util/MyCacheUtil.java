package com.lee.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class MyCacheUtil {

	/**
	* @Title: getList
	* @Description: TODO(调用Redis获取数据)
	* @param @param key
	* @param @param t
	* @param @return    参数
	* @return List<T>    返回类型
	* @throws
	 */
	public static <T> List<T> getList(String key, Class<T> t) {
		List<T> list = new ArrayList<>();
		Jedis jedis;
		try {
			jedis = JedisPoolUtils.getJedis();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		Set<String> zrange = jedis.zrange(key, 0, -1);
		for (String string : zrange) {
			T obj = MyJsonUtil.json_to_object(string, t);
			list.add(obj);
		}
		try {
			jedis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @param <T>
	* @Title: setKey
	* @Description: TODO(插入key class_2_flbh2)
	* @param @param key
	* @param @param list_sku    参数
	* @return void    返回类型
	* @throws
	 */
	public static <T> void setKey(String key, List<T> list) {
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtils.getJedis();
		} catch (Exception e) {
			// 记日志
			e.printStackTrace();
			return;
		}
		// zset一个key放入数据, 不同的数据不会覆盖而是添加
		jedis.del(key);
		for (int i = 0; i < list.size(); i++) {
			jedis.zadd(key, i, MyJsonUtil.object_to_json(list.get(i)));
		}
		try {
			jedis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	* @Title: interKeys
	* @Description: TODO(交叉检索)
	* @param @param keys
	* @param @param list_attr2
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	public static String interKeys(String[] keys) {
		// 单个属性无需交叉检索
		if (keys.length == 1) {
			return keys[0];
		}
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtils.getJedis();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		StringBuilder sBuilder = new StringBuilder("combine");
		for (int i = 0; i < keys.length; i++) {
			sBuilder.append("_").append(keys[i]);
		}
		String str_key = sBuilder.toString();
		// 判断key是否存在
		if (!if_exist(str_key)) {
			jedis.zinterstore(str_key, keys);
		}
		try {
			jedis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str_key;
	}
	
	/**
	* @Title: if_exist
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param key
	* @param @return    参数
	* @return boolean    返回类型
	* @throws
	 */
	public static boolean if_exist(String key) {
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtils.getJedis();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		Boolean b = jedis.exists(key);
		try {
			jedis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

}
