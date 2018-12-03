package com.cmcc.service.mq;

import javax.jms.TextMessage;

public interface ConsumerService {

	public TextMessage receive();
}
