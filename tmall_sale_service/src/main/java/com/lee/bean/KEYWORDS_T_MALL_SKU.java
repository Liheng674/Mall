package com.lee.bean;

import org.apache.solr.client.solrj.beans.Field;

/**
 * @ClassName: KEYWORDS_T_MALL_SKU
 * @Description: TODO(solr)
 * @author A18ccms a18ccms_gmail_com
 * @date 2018年3月28日 下午3:00:50
 *
 */
public class KEYWORDS_T_MALL_SKU extends T_MALL_SKU {

	// 文本和字段之间需要有一个对应关系
	@Field("shp_tp")
	private String shp_tp;

	public String getShp_tp() {
		return shp_tp;
	}

	public void setShp_tp(String shp_tp) {
		this.shp_tp = shp_tp;
	}

}
