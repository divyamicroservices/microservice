package com.ibm.ordermicroservice.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

	@Autowired
	private OrderJPARepository repo;
	
	Logger logger = LoggerFactory.getLogger(OrderController.class);
	

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public List<UserOrders> getAllOrder() {
		logger.info("Inside OrderController inside getAllOrder");
		return repo.findAll();
	}
	
	
	@RequestMapping(path = "/user/{loginname}", method = RequestMethod.GET)
	public List<UserOrders> getUserOrders(@PathVariable String loginname) {
		
		logger.info("Inside OrderController inside getUserOrders");
		return repo.findByusernameLike(loginname);
		
		
		
	}
	
	
	
	
}
