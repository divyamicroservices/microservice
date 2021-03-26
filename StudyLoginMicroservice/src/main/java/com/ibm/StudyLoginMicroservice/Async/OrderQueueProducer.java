package com.ibm.StudyLoginMicroservice.Async;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.StudyLoginMicroservice.Login.LoginController;

@Component
public class OrderQueueProducer {

	private final RabbitTemplate rabbitTemplate;
	
	Logger logger = LoggerFactory.getLogger(OrderQueueProducer.class);
	
	@Autowired
	public OrderQueueProducer(RabbitTemplate rabbitTemplate) {
		super();
		
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void produce(String message) throws Exception{
		
		rabbitTemplate.setExchange("StudyOrderExchange");
		logger.info("before producing message");
		rabbitTemplate.convertAndSend("StudyOrderExchange",message);
		logger.info("after producing message");
		
	}
	
	
}
