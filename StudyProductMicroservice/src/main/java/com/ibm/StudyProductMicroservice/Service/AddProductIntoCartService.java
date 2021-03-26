package com.ibm.StudyProductMicroservice.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


import com.ibm.StudyProductMicroservice.Product.Cart;
import com.ibm.StudyProductMicroservice.restClient.addProductIntoCartClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class AddProductIntoCartService {

	Logger logger = LoggerFactory.getLogger(AddProductIntoCartService.class);
	
final addProductIntoCartClient addProductIntoCartClient_I;
	
	public AddProductIntoCartService(addProductIntoCartClient addProductIntoCartClient_I) {
		this.addProductIntoCartClient_I=addProductIntoCartClient_I;
	}
	
	@HystrixCommand(fallbackMethod="addProductInCartFallback")
	public List<Cart> addProductInCart(String loginName,String productName,String mrp) {
		logger.info("inside AddProfuctIntoCartService addProductInCart ");
		List<Cart> temp = addProductIntoCartClient_I.addProductincart(loginName,productName,mrp);
		
		return temp;
	}
	
	
	
	public List<Cart> addProductInCartFallback(String loginName,String productName,String mrp) {
		logger.info("inside AddProfuctIntoCartService addProductInCartFallback ");
		List<Cart> temp = new ArrayList<Cart>();
		
		return temp;
		
		
	}
}
