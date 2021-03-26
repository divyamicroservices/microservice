package com.ibm.StudyCartMicroservice.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@RestController
@RequestMapping(path = "/cart")
public class CartController {

	Logger logger = LoggerFactory.getLogger(CartController.class);
	@Autowired
	private CartJPARepository repo;

	@RequestMapping(path = "/user/{loginName}", method = RequestMethod.GET)
	public List<Cart> getUserCart(@PathVariable String loginName) {
		logger.info("Inside CartController inside getUserCart");
		 return repo.findByusernameLike(loginName);
	}
	
	@RequestMapping(path = "/add/{username}/{productid}", method = RequestMethod.GET)
	public List<Cart> addProduct(@PathVariable String username, @PathVariable String productid,String mrp) {
		
		logger.info("Inside CartController inside addProductinCart");
		Cart temp = new Cart();
		temp.setUsername(username);
		temp.setProductid(productid);
				
		repo.save(temp);
		
		
		return repo.findByusernameLike(username);
	}


	@RequestMapping(path = "/{loginname}", method = RequestMethod.GET)
	public List<Cart> getCart(@PathVariable String loginname) {
		logger.info("Inside CartController inside getcart");
		List<Cart> temp = repo.findByusernameLike(loginname);
		
		for(int cnt=0; cnt< temp.size(); cnt++) {
			logger.info("inside get cart in Cart Controller"+temp.get(cnt).getCartid());
		}
		
		return temp;
		
		
		
	}
	
	/*
	
	*/
	/*
	@RequestMapping(path = "/update/{loginName}/{productName}", method = RequestMethod.GET)
	public List<Cart> updateCart(@PathVariable String loginName, @PathVariable String productName) {
		
		Optional<Cart> oCart =repo.findById(name);
		Product temp = oProduct.get();
		logger.info("before conversion set" + temp.getMrp());
		temp.setMrp(mrp);
		logger.info("after conversion set" + temp.getMrp());
		
		repo.save(temp);
		return repo.findAll();
	}
	*/
	
	
	@RequestMapping(path = "/add/product/{username}/{productid}", method = RequestMethod.GET)
	public List<Cart> addProductincart(@PathVariable String username,@PathVariable String productid) {
		logger.info("Inside CartController inside addProductInCart");
		logger.info("inside addProductincart catController");
		
		logger.info("username"+username);
		logger.info("productid"+productid);
		
		
		Cart temp = new Cart();
		temp.setUsername(username);
		temp.setProductid(productid);
		
		
		repo.save(temp);
		
		logger.info("inside addProductincart catController after save");
		
		return repo.findByusernameLike(username);
	}
	
	
	@RequestMapping(path = "/delete/product/{username}/{cartid}", method = RequestMethod.GET)
	public List<Cart> deleteProductincart(@PathVariable String username,@PathVariable String cartid) {
		logger.info("Inside CartController inside deleteProductincart");
		logger.info("inside deleteProductincart cartController");
		
		logger.info("username"+username);
		logger.info("productid"+cartid);
		
		repo.deleteBycartid(Integer.parseInt(cartid));
			
		logger.info("inside addProductincart catController after save");
		
		return repo.findByusernameLike(username);
	}
	
	/*
	@GetMapping("/deletecart/{username}")	
	public List<Cart> deleteCart(@PathVariable String username);
	*/
	
	@RequestMapping(path = "/deletecart/{loginname}", method = RequestMethod.GET)
	public List<Cart> deleteCart(@PathVariable String loginname) {
		logger.info("Inside CartController inside getcart");
		repo.deleteByusername(loginname);
		
		
		
		return new ArrayList<Cart>();
		
		
		
	}
	
}
