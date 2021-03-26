package com.ibm.StudyLoginMicroservice.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ibm.StudyLoginMicroservice.Login.LoginController;
import com.ibm.StudyLoginMicroservice.Login.Order;
import com.ibm.StudyLoginMicroservice.restClient.GetOrderClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class GetOrderService {

final GetOrderClient getOrderClient_I;
Logger logger = LoggerFactory.getLogger(GetOrderService.class);

	public GetOrderService(GetOrderClient getOrderClient_I) {
		this.getOrderClient_I=getOrderClient_I;
	}
	
	@HystrixCommand(fallbackMethod="getMaxOrderNoFallback")
	public int getMaxOrderNo(String userName) {
		logger.info("inside GetOrderService getMaxOrderNo Service");
		int temp = getOrderClient_I.getMaxOrderNo(userName);
		
		return temp;
	}
	
	
	
	public int getMaxOrderNoFallback(String userName) {
		
		logger.info("inside GetOrderService getMaxOrderNoFallback");
		
		return 9999;
		
		
	}
	
	
	@HystrixCommand(fallbackMethod="getUserOrdersFallback")
	public List<Order> getUserOrders(String userName) {
		logger.info("inside GetOrderService getUserOrders ");
		List<Order> temp = getOrderClient_I.getUserOrders(userName);
		
		return temp;
	}
	
	
	
	public List<Order> getUserOrdersFallback(String userName) {
		
		logger.info("inside GetOrderService getUserOrdersFallback ");
		List<Order> temp = new ArrayList<Order>();
		
		return temp;
		
		
	}
}
