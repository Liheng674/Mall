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
	public List<OBJECT_T_MALL_ATTR> get_attr_list(int flbh2) {
		return attrMapper.select_attr_list(flbh2);
	}

}
