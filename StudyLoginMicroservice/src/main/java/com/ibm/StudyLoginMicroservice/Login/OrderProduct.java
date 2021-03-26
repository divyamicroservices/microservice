package com.ibm.StudyLoginMicroservice.Login;

public class OrderProduct {

	private String orderno;
	private Integer id;
	private String productName;
	
	private Integer mrp;
	private String productcategory;
	
	
	
	
	
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
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
