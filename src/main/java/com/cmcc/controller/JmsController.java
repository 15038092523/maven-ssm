package com.cmcc.controller;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmcc.service.mq.ConsumerService;
import com.cmcc.service.mq.ProducerService;

@Controller
@RequestMapping("/jms")
public class JmsController {
	@Autowired(required = false)
	ProducerService producerService;

	@Autowired(required = false)
	ConsumerService consumerService;

	@RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
	@ResponseBody
	public String send(String msg) {
		if (StringUtils.isBlank(msg))
			msg = "helloActiveMq";
		producerService.sendMessage(msg);
		return msg;
	}

	@RequestMapping(value = "/receiveMessage", method = RequestMethod.GET)
	@ResponseBody
	public String receive() throws JMSException {
		 return consumerService.receive().getText().toString();
	}
}
