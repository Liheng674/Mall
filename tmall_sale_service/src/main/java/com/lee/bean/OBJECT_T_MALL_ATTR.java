package com.lee.bean;

import java.util.List;

/**
* @ClassName: OBJECT_T_MALL_ATTR
* @Description: TODO(封装商品属性值表T_MALL_VALUE的字段)
* @author A18ccms a18ccms_gmail_com
* @date 2018年3月24日 下午10:49:45
*
 */
public class OBJECT_T_MALL_ATTR extends T_MALL_ATTR {

	private List<T_MALL_VALUE> list_value;

	public List<T_MALL_VALUE> getList_value() {
		return list_value;
	}

	public void setList_value(List<T_MALL_VALUE> list_value) {
		this.list_value = list_value;
	}

}
