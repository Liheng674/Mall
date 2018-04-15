package com.lee.mapper;

import org.apache.ibatis.annotations.Select;

import com.lee.bean.T_MALL_USER_ACCOUNT;

public interface LoginMapper {

	@Select("select * from t_mall_user_account where yh_mch=#{yh_mch} and yh_mm=#{yh_mm}")
	T_MALL_USER_ACCOUNT select_user(T_MALL_USER_ACCOUNT user);

}
