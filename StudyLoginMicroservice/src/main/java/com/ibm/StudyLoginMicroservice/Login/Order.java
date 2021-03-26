package com.ibm.StudyLoginMicroservice.Login;

import javax.persistence.Column;

public class Order {

	private Integer id;
	
	
	private String username;
	
	
	private String productid;
	
	
	private String orderno;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getProductid() {
		return productid;
	}


	public void setProductid(String productid) {
		this.productid = productid;
	}


	public String getOrderno() {
		return orderno;
	}


	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}


	
	
	
	
	
	
	
}
