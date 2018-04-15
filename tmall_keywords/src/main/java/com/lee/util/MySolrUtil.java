package com.lee.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;

public class MySolrUtil {

	/**
	* @Title: getSolr
	* @Description: TODO(获取solr)
	* @param @param url
	* @param @return    参数
	* @return HttpSolrServer    返回类型
	* @throws
	 */
	public static HttpSolrServer getSolr(String url) {
		// 定义solr的server
		HttpSolrServer solr = null;
		try {
			solr = new HttpSolrServer(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 设置响应解析器
		solr.setParser(new XMLResponseParser());
		return solr;
	}

	/**
	* @Title: solrQuery
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param rows 行数, 默认为10
	* @param @param keywords 查询关键字
	* @param @param url solr配置地址
	* @param @param t 泛型
	* @param @return    参数
	* @return List<T>    返回类型
	* @throws
	 */
	public static <T> List<T> solrQuery(Integer rows, String keywords, String url, Class<T> t) {
		// 查询数据
		HttpSolrServer solr = getSolr(url);
		if (StringUtils.isBlank(keywords)) {
			return null;
			//keywords = "combine_item:*";
		}else {
			keywords = "combine_item:" + keywords;
		}
		SolrQuery solrQuery = new SolrQuery(keywords);
		solrQuery.setRows(rows);
		QueryResponse query = null;
		try {
			query = solr.query(solrQuery);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		List<T> list = query.getBeans(t);

		return list;
	}
}
