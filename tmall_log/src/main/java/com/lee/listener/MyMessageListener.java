package com.lee.listener;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lee.bean.T_MALL_USER_ACCOUNT;
import com.lee.mapper.LoginMsgMapper;
import com.lee.util.MyJsonUtil;

@Service
public class MyMessageListener implements MessageListener {
	
	@Autowired
	private LoginMsgMapper loginMsgMapper;
	
	@Override
	public void onMessage(Message message) {
		// 消息内容
		TextMessage textMessage = (TextMessage) message;
		String text = "";
		try {
			text = textMessage.getText();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		System.out.println(text);
		// 保存用户id和登录时间到数据库
		Map<String, Object> map = new HashMap<>();
		T_MALL_USER_ACCOUNT user = MyJsonUtil.json_to_object(text, T_MALL_USER_ACCOUNT.class);
		map.put("yh_id", user.getId());
		map.put("message", user.getYh_mch() + "登录系统");
		loginMsgMapper.insert_login_msg(map);
	}
}
