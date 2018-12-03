package com.cmcc.service.mq.impl;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.cmcc.service.mq.ProducerService;

@Service
public class ProducerServiceImpl implements ProducerService {

	  private static Logger loger = Logger.getLogger(ProducerServiceImpl.class.getName());
	  
	  //ָ����ϢĿ�ĵصķ��ͷ���
	    @Resource(name="jmsTemplate")
	    private JmsTemplate jmsTemplate;
	    
	    public void sendMessage(Destination destination,final String msg){
	    	loger.info(Thread.currentThread().getName()+" �����"+destination.toString()+"������Ϣ----->"+msg); 
	    	jmsTemplate.send(destination,new MessageCreator() { 
	    		public Message createMessage(Session session) throws JMSException { 
	    			return session.createTextMessage(msg); 
	    			} 
	    		}); 
	    	}
	 
	    //Ĭ����ϢĿ�ĵصķ��� 
	    public void sendMessage(final String msg){ 
	    	Destination destination = jmsTemplate.getDefaultDestination(); 
	    	loger.info(Thread.currentThread().getName()+" �����"+destination+"������Ϣ----->"+msg); 
	    	jmsTemplate.send(new MessageCreator() { 
	    		public Message createMessage(Session session) throws JMSException {
	    			return session.createTextMessage(msg);
	    			} 
	    		}); 
	    	} 
	    }

