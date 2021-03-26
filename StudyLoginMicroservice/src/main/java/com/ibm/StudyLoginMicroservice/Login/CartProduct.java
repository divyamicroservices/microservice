package com.ibm.StudyLoginMicroservice.Login;

public class CartProduct {
	private Integer cartId;
	private Integer id;
	private String productName;
	
	private Integer mrp;
	private String productcategory;
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	
	public Integer getMrp() {
		return mrp;
	}
	public void setMrp(Integer mrp) {
		this.mrp = mrp;
	}
	public String getProductcategory() {
		return productcategory;
	}
	public void setProductcategory(String productcategory) {
		this.productcategory = productcategory;
	}
	
	
	
}
