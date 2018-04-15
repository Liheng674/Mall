package com.lee.service.impl;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.lee.service.LoginMessage;

@Service
public class LoginMessageImpl implements LoginMessage {

	@Resource(name = "jmsTemplate")
	private JmsTemplate jmsTemplate;
	
	@Resource(name = "queueDestination")
	private Destination destination;
	
	public void sendMessage(String json_user) {
		// 发送mq消息
		jmsTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(json_user);
			}
		});
	}

}
