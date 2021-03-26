package com.ibm.StudyLoginMicroservice.Async;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.StudyLoginMicroservice.Login.LoginController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class QueueProducer {

	Logger logger = LoggerFactory.getLogger(LoginController.class);
	private final RabbitTemplate rabbitTemplate;
	
	@Autowired
	public QueueProducer(RabbitTemplate rabbitTemplate) {
		super();
		
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void produce(String message) throws Exception{
		
		rabbitTemplate.setExchange("StudyLoginExchange");
		logger.info("before producing message");
		rabbitTemplate.convertAndSend((new ObjectMapper()).writeValueAsString(message));
		logger.info("after producing message");
		
	}
	
	
}
