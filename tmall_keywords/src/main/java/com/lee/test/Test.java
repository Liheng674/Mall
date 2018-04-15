package com.lee.test;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.lee.bean.KEYWORDS_T_MALL_SKU;
import com.lee.factory.MySqlSessionFactory;
import com.lee.mapper.ClassMapper;
import com.lee.util.MyPropertiesUtil;
import com.lee.util.MySolrUtil;

public class Test {

	public static void main(String[] args) throws IOException, SolrServerException {
		SqlSessionFactory sqlSessionFactory = MySqlSessionFactory.getMyFactory();
		SqlSession session = sqlSessionFactory.openSession();
		ClassMapper mapper = session.getMapper(ClassMapper.class);
		
		List<KEYWORDS_T_MALL_SKU> list = mapper.select_list_by_flbh2(28);
		System.out.println(list);
		String solr_sku = MyPropertiesUtil.loadProperties("solr.properties", "solr_sku");
		HttpSolrServer solr = MySolrUtil.getSolr(solr_sku);
		// 向solr中插入数据
		solr.addBeans(list);
		solr.commit();
		
		// 查询数据
		String q = "combine_item:小明北七家";
		List<KEYWORDS_T_MALL_SKU> list_sku = MySolrUtil.solrQuery(null, q, solr_sku, KEYWORDS_T_MALL_SKU.class);
		System.out.println(list_sku);
	}

}
