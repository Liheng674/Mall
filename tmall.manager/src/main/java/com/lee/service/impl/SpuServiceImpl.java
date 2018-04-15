package com.lee.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lee.bean.T_MALL_PRODUCT;
import com.lee.mapper.SpuMapper;
import com.lee.service.SpuService;

@Service
public class SpuServiceImpl implements SpuService {

	@Autowired
	private SpuMapper spuMapper;
	
	@Override
	public void insertIntoProduct(T_MALL_PRODUCT tmp, List<String> list, Integer img_cover) {
		// 插入spu信息, 返回自动生成的主键
		for (int i = 0; i < list.size(); i++) {
			if((img_cover - 1) == i) {
				tmp.setShp_tp(list.get(i));
			}
		}
		spuMapper.insertIntoProduct(tmp);
		
		// 根据spu的id批量插入图片
		Map<String, Object> map = new HashMap<>();
		map.put("shp_id", tmp.getId());
		map.put("url", list);
		spuMapper.insert_spu_img(map);
	}

	@Override
	public List<T_MALL_PRODUCT> get_spu_list(int pp_id, int flbh2) {
		Map<String, Object> map = new HashMap<>();
		map.put("pp_id", pp_id);
		map.put("flbh2", flbh2);
		return spuMapper.select_spu_list(map);
	}

}
