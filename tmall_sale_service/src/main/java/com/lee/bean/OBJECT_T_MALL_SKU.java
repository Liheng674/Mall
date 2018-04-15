package com.lee.bean;

/**
* @ClassName: OBJECT_T_MALL_SKU
* @Description: TODO(商品列表)
* @author A18ccms a18ccms_gmail_com
* @date 2018年3月28日 下午3:00:50
*
 */
public class OBJECT_T_MALL_SKU extends T_MALL_SKU {

	private T_MALL_PRODUCT spu;
	private T_MALL_TRADE_MARK tm;

	public T_MALL_PRODUCT getSpu() {
		return spu;
	}

	public void setSpu(T_MALL_PRODUCT spu) {
		this.spu = spu;
	}

	public T_MALL_TRADE_MARK getTm() {
		return tm;
	}

	public void setTm(T_MALL_TRADE_MARK tm) {
		this.tm = tm;
	}

}
