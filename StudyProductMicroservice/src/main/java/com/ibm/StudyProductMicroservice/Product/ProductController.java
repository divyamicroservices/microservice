package com.ibm.StudyProductMicroservice.Product;

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


import com.ibm.StudyProductMicroservice.Service.AddProductIntoCartService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
	
	
	Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductJPARepository repo;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public List<Product> getAllProducts() {
		logger.info("Inside ProductController inside getAllProducts");
		List<Product> listofProducts = repo.findAll();
		for(int cnt=1; cnt < listofProducts.size(); cnt++ ) {
			
			logger.info("In ProductController productName" + listofProducts.get(cnt).getProductName()+" cat- "+listofProducts.get(cnt).getProductcategory());
		}
		return listofProducts;
	}
	
	
	@RequestMapping(path = "/id/{id}", method = RequestMethod.GET)	
	public Product getProduct(@PathVariable String id) {
		logger.info("Inside ProductController inside getProduct");
		Optional<Product> oProduct =repo.findById(Integer.parseInt(id));
		if(oProduct.isPresent())
		{
			return oProduct.get();
		}
		return null;
		
	}
	
	@RequestMapping(path = "/add/{name}/{mrp}/{category}", method = RequestMethod.GET)
	public List<Product> addProduct(@PathVariable String name, @PathVariable String mrp,@PathVariable String category) {
		
		logger.info("Inside ProductController inside addProducts");
		Product temp = new Product();
		temp.setProductName(name);
		temp.setMrp(mrp);
		temp.setProductcategory(category);
		
		repo.save(temp);
		
		
		return repo.findAll();
	}


	/*
	@RequestMapping(path = "/{name}", method = RequestMethod.GET)
	public Product getCurrency(@PathVariable String name) {
		
		
		Optional<Product> oProduct =repo.findById(name);
		if(oProduct.isPresent())
		{
			return oProduct.get();
		}
		return null;
	}
	*/
	
	/*
	
	
	*/
	/*
	@RequestMapping(path = "/update/{name}/{mrp}", method = RequestMethod.GET)
	public List<Product> updateProduct(@PathVariable String name, @PathVariable String mrp) {
		
		Optional<Product> oProduct =repo.findById(name);
		Product temp = oProduct.get();
		logger.info("before conversion set" + temp.getMrp());
		temp.setMrp(mrp);
		logger.info("after conversion set" + temp.getMrp());
		
		repo.save(temp);
		return repo.findAll();
	}

	*/
	@Autowired
	AddProductIntoCartService addProductIntoCartService;
	
		
	@RequestMapping(path = "/add/cart/{loginName}/{productName}/{mrp}", method = RequestMethod.GET)
	public List<Cart> addProductInCart(@PathVariable String loginName, @PathVariable String productName,@PathVariable String mrp) {
		
		logger.info("Inside ProductController inside  addProductInCart");
		return addProductIntoCartService.addProductInCart(loginName, productName,mrp);
		
	}

}
