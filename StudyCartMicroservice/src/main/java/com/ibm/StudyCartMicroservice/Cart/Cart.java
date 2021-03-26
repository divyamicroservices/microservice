package com.ibm.StudyCartMicroservice.Cart;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "cart")
public class Cart {

	/*
	 * @Autowired
	 * 
	 * @Transient private ProductRepository repo;
	 */

	
	/*
	 * @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(name = "countryName", table = "currency") */


	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cartid;
	
	@Column(name = "username", table = "cart")
	private String username;
	
	@Column(name = "productid", table = "cart")
	private String productid;


	
	public Cart(int cartid,String username,String productid) {
		super();
		
		this.cartid = cartid;
		this.username = username;
		this.productid = productid;
		
		
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCartid() {
		return cartid;
	}

	public void setCartid(int cartid) {
		this.cartid = cartid;
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

	
	
}
