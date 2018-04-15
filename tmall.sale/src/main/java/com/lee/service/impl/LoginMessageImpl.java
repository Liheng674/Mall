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
	//队列模式
	@Resource(name = "jmsTemplate")
	private JmsTemplate jmsTemplate;
	
	//主题模式，消费者和订阅者
	//因为我们可能在spring容器中注入多个目的地，所以这里使用@Resource注解
	@Resource(name = "queueDestination")
	private Destination destination;
	
	@Resource(name = "topicDestination")
	private Destination topicDestination;
	
	public void sendMessage(String json_user) {
		// 发送queue消息
		jmsTemplate.send(destination, new MessageCreator() {
			//创建一个消息
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(json_user);
			}
		});
		// 发送topic消息
		jmsTemplate.send(topicDestination, new MessageCreator() {
			//创建一个消息
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(json_user);
			}
		});
	}

}
