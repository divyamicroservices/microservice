package com.ibm.StudyProductMicroservice.Product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "product")
public class Product {

	/*
	 * @Autowired
	 * 
	 * @Transient private ProductRepository repo;
	 */

	
	/*
	 * @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(name = "countryName", table = "currency") */
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Integer id;
	
	@Column(name = "productname", table = "product")
	private String productName;
	@Column(name = "mrp", table = "product")
	private String mrp;
	                
	@Column(name = "productcategory", table = "product")
	private String productcategory;

	

	public Product(String productName,String mrp,String productcategory) {
		super();
		
		this.productName = productName;
		this.mrp = mrp;
		this.productcategory = productcategory;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public String getProductcategory() {
		return productcategory;
	}

	public void setProductcategory(String productcategory) {
		this.productcategory = productcategory;
	}


	

	
	

	
	
}
