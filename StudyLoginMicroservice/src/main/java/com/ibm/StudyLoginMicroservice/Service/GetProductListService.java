package com.ibm.StudyLoginMicroservice.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ibm.StudyLoginMicroservice.Login.LoginController;
import com.ibm.StudyLoginMicroservice.Login.Product;
import com.ibm.StudyLoginMicroservice.restClient.getProductListClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class GetProductListService {

	Logger logger = LoggerFactory.getLogger(GetProductListService.class);
final getProductListClient getProductListClient_I;
	
	public GetProductListService(getProductListClient getProductListClient_I) {
		this.getProductListClient_I=getProductListClient_I;
	}
	
	@HystrixCommand(fallbackMethod="getProductListFallback")
	public List<Product> getAllProducts() {
		logger.info("inside GetProductListService getAllProducts");
		List<Product> temp = getProductListClient_I.getAllProducts();
		
		return temp;
	}	
	
	public List<Product> getProductListFallback() {
		logger.info("inside GetProductListService getProductListFallback");
		List<Product> temp = new ArrayList<Product>();
		
		return temp;
		
		
	}
	
	@HystrixCommand(fallbackMethod="getProductFallback")
	public Product getProduct(String productid) {
		logger.info("inside GetProductListService getProduct "+ productid);
		Product temp = getProductListClient_I.getProduct(Integer.parseInt(productid));
		
		return temp;
	}	
	
	public Product getProductFallback(String productid) {
		logger.info("inside GetProductListService getProductFallback "+ productid);
		Product temp = new Product();
		
		return temp;
		
		
	}
	
	
	@HystrixCommand(fallbackMethod="addProductFallback")
	public List<Product> addProduct(String add_productname,String add_mrp,String add_productcategory) {
		logger.info("inside GetProductListService addProduct");
		List<Product> temp = getProductListClient_I.addProduct( add_productname, add_mrp, add_productcategory);
		
		return temp;
	}	
	
	public List<Product> addProductFallback(String add_productname,String add_mrp,String add_productcategory) {
		logger.info("inside GetProductListService addProductFallback");
		List<Product> temp = new ArrayList<Product>();
		
		return temp;
		
		
	}
	
	
}
