package com.lee.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lee.bean.OBJECT_T_MALL_SKU;
import com.lee.bean.T_MALL_SKU_ATTR_VALUE;
import com.lee.mapper.ListMapper;
import com.lee.service.ListService;

@Service
public class ListServiceImpl implements ListService {
	
	@Autowired
	private ListMapper listMapper;
	
	@Override
	public List<OBJECT_T_MALL_SKU> get_tm_list(int flbh2) {
		return listMapper.select_tm_list(flbh2);
	}

	/**
	 * AND sku.Id 
		in (
		SELECT sku_id from
		(select sku_id from t_mall_sku_attr_value WHERE shxm_id = ? AND shxzh_id = ?)sku0,
		(select sku_id from t_mall_sku_attr_value WHERE shxm_id = ? AND shxzh_id = ?)sku1,
		(select sku_id from t_mall_sku_attr_value WHERE shxm_id = ? AND shxzh_id = ?)sku2
		where sku0.sku_id = sku0.sku_id and sku1.sku_id = sku2.sku_id
		)
	 */
	@Override
	public List<OBJECT_T_MALL_SKU> get_sku_list_attr(List<T_MALL_SKU_ATTR_VALUE> list_attr, int flbh2) {
		StringBuilder concat_str = new StringBuilder(" ");
		// 属性值id集合放入一个集合中
		List<Integer> old_ids = new ArrayList<>();
		if (list_attr != null && list_attr.size() > 0) {
			for (int i = 0; i < list_attr.size(); i++) {
				for (int j = 0; j < list_attr.size(); j++) {
					if (list_attr.get(i).getShxm_id() == list_attr.get(j).getShxm_id()) {
						old_ids.add(list_attr.get(i).getShxzh_id());
					}
				}
			}
			
			List<Integer> shxm_ids = removeDuplicate(old_ids);
			
			concat_str.append(" AND sku.id in ( SELECT sku0.sku_id from ");
			for (int i = 0; i < list_attr.size(); i++) {
				concat_str.append(" (select sku_id from t_mall_sku_attr_value WHERE shxm_id = "
						+ list_attr.get(i).getShxm_id() + " AND shxzh_id in ( ");
				for (int j = 0; j < shxm_ids.size(); j++) {
					concat_str.append(shxm_ids.get(j));
					if (j < shxm_ids.size() - 1) {
						concat_str.append(" , ");
					}
				}
				concat_str.append(" )) sku" + i);
				
				if (i < list_attr.size() - 1) {
					concat_str.append(" , ");
				}
			}
			if (list_attr.size() > 1) {
				concat_str.append(" where ");
				for (int i = 0; i < list_attr.size() - 1; i++) {
					concat_str.append(" sku"+ i +".sku_id = sku"+(i+1)+".sku_id ");
					if (i < list_attr.size() - 2) {
						concat_str.append(" and ");
					}
				}
			}
			concat_str.append(" )");
		}
		System.out.println(concat_str);
		Map<String, Object> map = new HashMap<>();
		map.put("flbh2", flbh2);
		map.put("concat_str", concat_str);
		return listMapper.select_sku_list_attr(map);
	}
	
	public static List<Integer> removeDuplicate(List<Integer> list)   {
	    HashSet<Integer> h = new HashSet<>(list);
	    list.clear();
	    list.addAll(h);
	    return list;
	}

}
