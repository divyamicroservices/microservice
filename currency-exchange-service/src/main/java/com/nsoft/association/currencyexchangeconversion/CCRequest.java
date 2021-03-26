package com.nsoft.association.currencyexchangeconversion;

public class CCRequest {
	
	private String countryCode;
	
	private double amount;
	
	

	public CCRequest(String countryCode, double amount) {
		super();
		this.countryCode = countryCode;
		this.amount = amount;
	}

	public CCRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "CCRequest [countryCode=" + countryCode + ", amount=" + amount + "]";
	}
	
	

}
