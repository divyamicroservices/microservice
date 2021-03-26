package com.ibm.StudyLoginMicroservice.restClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.StudyLoginMicroservice.Login.Cart;


@FeignClient(name="cart")
public interface AddProductInCartClient {

	/*
	
	@GetMapping("/cart/add/product/{loginName}/{productName}/{mrp}")
	public List<Cart> addProductincart(@PathVariable String loginName,@PathVariable String productName,@PathVariable String  mrp);
	
	
	@RequestMapping(path = "/add/product/{loginName}/{productName}/{mrp}", method = RequestMethod.GET)
	public List<Cart> addProductincart(@PathVariable String loginName,@PathVariable String productName,@PathVariable String  mrp) 
	
	
	@RequestMapping(path = "/add/{loginName}/{productName}/{mrp}", method = RequestMethod.GET)
	public List<Cart> addProduct(@PathVariable String loginName, @PathVariable String productName,String mrp) {

	
	
	*/
	
	@GetMapping("/cart/add/product/{username}/{productid}")	
	public List<Cart> addProductincart(@PathVariable String username,@PathVariable String productid);
	
	@GetMapping("/cart/{username}")	
	public List<Cart> getCart(@PathVariable String username);

	
	@GetMapping(path = "/cart/delete/product/{username}/{cartid}")
	public List<Cart> deleteProductincart(@PathVariable String username,@PathVariable String cartid);
	
	
	@GetMapping("/cart/deletecart/{username}")	
	public List<Cart> deleteCart(@PathVariable String username);
	
}	