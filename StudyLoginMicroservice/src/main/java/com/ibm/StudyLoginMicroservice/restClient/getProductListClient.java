package com.ibm.StudyLoginMicroservice.restClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.StudyLoginMicroservice.Login.Cart;
import com.ibm.StudyLoginMicroservice.Login.Product;



@FeignClient(name="product")
public interface getProductListClient {

	@GetMapping("/product/")
	public List<Product> getAllProducts();

	
	@GetMapping("/product/id/{id}")	
	public Product getProduct(@PathVariable Integer id);
	
	
	
	@GetMapping(path = "/product/add/{name}/{mrp}/{category}")
	public List<Product> addProduct(@PathVariable String name, @PathVariable String mrp,@PathVariable String category);
	
}


