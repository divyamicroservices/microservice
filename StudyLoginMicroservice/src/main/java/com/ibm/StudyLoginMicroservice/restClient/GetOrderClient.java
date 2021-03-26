package com.ibm.StudyLoginMicroservice.restClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.StudyLoginMicroservice.Login.Cart;
import com.ibm.StudyLoginMicroservice.Login.Order;
import com.ibm.StudyLoginMicroservice.Login.Product;




@FeignClient(name="order")
public interface GetOrderClient {

	@GetMapping(path = "/order/user/max/{loginname}")
	public int getMaxOrderNo(@PathVariable String loginname);
	
	@GetMapping(path = "/order/user/{loginname}")
	public List<Order> getUserOrders(@PathVariable String loginname);
	
}