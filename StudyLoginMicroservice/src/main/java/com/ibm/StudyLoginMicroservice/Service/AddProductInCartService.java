package com.ibm.StudyLoginMicroservice.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ibm.StudyLoginMicroservice.Login.Cart;
import com.ibm.StudyLoginMicroservice.Login.LoginController;
import com.ibm.StudyLoginMicroservice.Login.Product;
import com.ibm.StudyLoginMicroservice.restClient.AddProductInCartClient;
import com.ibm.StudyLoginMicroservice.restClient.getProductListClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AddProductInCartService {
	Logger logger = LoggerFactory.getLogger(AddProductInCartService.class);
	
final AddProductInCartClient addProductInCartClient_I;
	
	public AddProductInCartService(AddProductInCartClient addProductInCartClient_I) {
		this.addProductInCartClient_I=addProductInCartClient_I;
	}
	
	@HystrixCommand(fallbackMethod="addProductInCartFallback")
	public List<Cart> addProductInCart(String userName,String productid) {
		logger.info("inside AddProductInCartService addProductInCart");
		List<Cart> temp = addProductInCartClient_I.addProductincart(userName,productid);
		
		return temp;
	}
	
	
	
	public List<Cart> addProductInCartFallback(String userName,String productid) {
		logger.info("inside AddProductInCartService addProductInCartFallback");
		List<Cart> temp = new ArrayList<Cart>();
		
		return temp;
		
		
	}
	
	@HystrixCommand(fallbackMethod="deleteProductInCartFallback")
	public List<Cart> deleteProductInCart(String userName,String cartid) {
		logger.info("inside AddProductInCartService deleteProductInCart " + cartid);
		List<Cart> temp = addProductInCartClient_I.deleteProductincart(userName,cartid);
		
		return temp;
	}
	
	
	
	public List<Cart> deleteProductInCartFallback(String userName,String productid) {
		logger.info("inside AddProductInCartService deleteProductInCartFallback " + productid);
		List<Cart> temp = new ArrayList<Cart>();
		
		return temp;
		
		
	}
}
