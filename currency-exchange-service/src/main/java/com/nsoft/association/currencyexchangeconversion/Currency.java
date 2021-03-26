package com.nsoft.association.currencyexchangeconversion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "CURRENCY")
public class Currency {
	
	@Id
	Integer id;
	
	@Column(name = "CURRENCYCODE")
	private String currencyCode;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "CONVERSIONFACTOR")
	private double conversionFactor;
	
	
	

	public Currency(Integer id, String currencyCode, String country, double conversionFactor) {
		super();
		this.id = id;
		this.currencyCode=currencyCode;
		this.country = country;
		this.conversionFactor = conversionFactor;
	}
	
	public Currency() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCurrencyCode() {
		return currencyCode;
	}


	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}


	public double getConversionFactor() {
		return conversionFactor;
	}


	public void setConversionFactor(double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}
	
	
	
	
	
	
}
