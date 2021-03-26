package com.ibm.auditmicroservice.audit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "audit_login")
public class Audit {

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
	
	@Column(name = "username", table = "audit_login")	
	private String userName;
	
	@Column(name = "status", table = "audit_login")
	private String status;
	
	@Column(name = "datetime", table = "audit_login")
	private String dateTime;
	
	
	public Audit(String userName,String status, String dateTime) {
		super();
		
		this.userName = userName;
		this.status = status;
		this.dateTime = dateTime;
		
	}

	public Audit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	
	

	
	
}
