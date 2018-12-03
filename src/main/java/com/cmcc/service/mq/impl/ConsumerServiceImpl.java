package com.cmcc.service.mq.impl;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.cmcc.service.mq.ConsumerService;
@Service
public class ConsumerServiceImpl implements ConsumerService {

	// 消息目的地
	@Resource(name = "demoQueueDestination")
	Destination destination;

	@Resource(name = "jmsTemplate")
	JmsTemplate jmsTemplate;

	public TextMessage receive() {
		TextMessage textMessage = (TextMessage) jmsTemplate.receive(destination);
		try {
			System.out.println("从队列" + destination + "收到了消息：\t" + textMessage.getText().toString());
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return textMessage;
	}

}
