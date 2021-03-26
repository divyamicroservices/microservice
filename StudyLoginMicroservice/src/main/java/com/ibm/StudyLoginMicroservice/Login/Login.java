package com.ibm.StudyLoginMicroservice.Login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "login")
public class Login {

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
	private String username;
	
	@Column(name = "usertype", table = "login")
	private String usertype;
	
	@Column(name = "password", table = "login")
	private String password;

	

	public Login(String username,String password,String usertype) {
		super();
		
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		
	}

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	
	
	
}
