package com.ibm.StudyLoginMicroservice.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ibm.StudyLoginMicroservice.Login.Cart;
import com.ibm.StudyLoginMicroservice.Login.LoginController;
import com.ibm.StudyLoginMicroservice.restClient.AddProductInCartClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class GetUserCartService {
	Logger logger = LoggerFactory.getLogger(GetUserCartService.class);
final AddProductInCartClient addProductInCartClient_I;
	
	public GetUserCartService(AddProductInCartClient addProductInCartClient_I) {
		this.addProductInCartClient_I=addProductInCartClient_I;
	}
	
	@HystrixCommand(fallbackMethod="getUserCartFallback")
	public List<Cart> getUserCart(String userName) {
		logger.info("inside GetUserCartService getUserCart");
		List<Cart> temp = addProductInCartClient_I.getCart(userName);
		
		return temp;
	}
	
	
	
	public List<Cart> getUserCartFallback(String userName) {
		logger.info("inside GetUserCartService getUserCartFallback");
		List<Cart> temp = new ArrayList<Cart>();
		
		return temp;
		
		
	}
	
	
	@HystrixCommand(fallbackMethod="deleteUserCartFallback")
	public List<Cart> deleteUserCart(String userName) {
		logger.info("inside GetUserCartService deleteUserCart");
		List<Cart> temp = addProductInCartClient_I.deleteCart(userName);
		
		return temp;
	}
	
	
	
	public List<Cart> deleteUserCartFallback(String userName) {
		logger.info("inside GetUserCartService deleteUserCartFallback");
		List<Cart> temp = new ArrayList<Cart>();
		
		return temp;
		
		
	}
	
	
}
