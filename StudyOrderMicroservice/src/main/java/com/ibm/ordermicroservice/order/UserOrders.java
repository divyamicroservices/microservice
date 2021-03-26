package com.ibm.ordermicroservice.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "userorders")
public class UserOrders {

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
	
	@Column(name = "username", table = "userorders")	
	private String username;
	
	@Column(name = "productid", table = "userorders")
	private String productid;
	
	@Column(name = "orderno", table = "userorders")
	private String  orderno;
	
	public UserOrders(int id, String username,String productid, String orderno) {
		super();
		this.id = id;
		this.username = username;
		this.productid = productid;
		this.orderno = orderno;
		
	}

	public UserOrders() {
		super();
		// TODO Auto-generated constructor stub
	}

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
