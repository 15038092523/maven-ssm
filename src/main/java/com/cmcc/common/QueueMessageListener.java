package com.cmcc.common;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.cmcc.service.GoodsService;

public class QueueMessageListener implements MessageListener  {
	
	@Autowired
	private GoodsService goodsService;
	
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        try {
            System.out.println("QueueMessageListener监听到了文本消息：\t"
                    + tm.getText());
            //do something ...
           //System.out.println(goodsService.deleteGoods(12)); 
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}