package com.ibm.StudyProductMicroservice.restClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.StudyProductMicroservice.Product.Cart;

@FeignClient(name="cart")
public interface addProductIntoCartClient {

	@GetMapping("/cart/add/product/{loginName}/{productName}/{mrp}")
	public List<Cart> addProductincart(@PathVariable String loginName,@PathVariable String productName,@PathVariable String  mrp) ;

}
