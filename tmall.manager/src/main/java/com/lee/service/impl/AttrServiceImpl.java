package com.lee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lee.bean.OBJECT_T_MALL_ATTR;
import com.lee.mapper.AttrMapper;
import com.lee.service.AttrService;

@Service
public class AttrServiceImpl implements AttrService {
	@Autowired
	private AttrMapper attrMapper;
	
	@Override
	public void insertAttr(int flbh2, List<OBJECT_T_MALL_ATTR> list_attr) {
		for (OBJECT_T_MALL_ATTR attr : list_attr) {
			// 插入属性, 返回主键
			attrMapper.insertAttr(flbh2, attr);
			
			// 根据返回的主键批量插入属性值
			attrMapper.insert_values(attr.getId(), attr.getList_value());
		}
	}

	@Override
	public List<OBJECT_T_MALL_ATTR> get_attr_list(int flbh2) {
		return attrMapper.select_attr_list(flbh2);
	}

}
