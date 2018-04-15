package com.lee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lee.bean.OBJECT_T_MALL_ATTR;
import com.lee.bean.T_MALL_VALUE;

public interface AttrMapper {

	void insertAttr(@Param("flbh2") int flbh2, @Param("attr") OBJECT_T_MALL_ATTR attr);

	void insert_values(@Param("shxm_id")int id, @Param("list_value") List<T_MALL_VALUE> list_value);

	List<OBJECT_T_MALL_ATTR> select_attr_list(int flbh2);

}
