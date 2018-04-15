package com.lee.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lee.bean.KEYWORDS_T_MALL_SKU;
import com.lee.util.MyPropertiesUtil;
import com.lee.util.MySolrUtil;

@Controller
public class KeywordsController {

	@ResponseBody
	@RequestMapping("/keywords")
	public List<KEYWORDS_T_MALL_SKU> keywords(String keywords) {
		String url = MyPropertiesUtil.loadProperties("solr.properties", "solr_sku");
		List<KEYWORDS_T_MALL_SKU> list_sku = MySolrUtil.solrQuery(50, keywords, url, KEYWORDS_T_MALL_SKU.class);
		return list_sku;
	}
}
