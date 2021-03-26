package com.ibm.ordermicroservice.async;

import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.ibm.ordermicroservice.order.OrderJPARepository;
import com.ibm.ordermicroservice.order.UserOrders;


@Component
public class OrderQueueConsumer {

	Logger logger = LoggerFactory.getLogger(OrderQueueConsumer.class);
	@Autowired
	private OrderJPARepository repo;
	
	public void orderReceiveMessage(String message) {
		
		processMessage(message);
	}
	

	public void orderReceiveMessage(byte[] message) {
		String str = new String(message);
		processMessage(str);
	}

	
	public void processMessage(String message) {
		logger.info("message in Order Queue Consumer-->"+ message);
		StringTokenizer st1 = new StringTokenizer(message, "---"); 
	             
		
		
		String username =st1.nextToken().replaceAll("\"","");
		String productid =st1.nextToken().replaceAll("\"","");
		String orderTimeStamp = st1.nextToken().replaceAll("\"","");
		
		
		
		logger.info("username-->"+ username);
		logger.info("productid-->"+ productid);
		logger.info("currentOrder-->"+ orderTimeStamp);
		
		UserOrders ordr = new UserOrders();
		ordr.setUsername(username);
		ordr.setProductid(productid);
		ordr.setOrderno(orderTimeStamp);	
		
		repo.save(ordr);
		
	}


}
