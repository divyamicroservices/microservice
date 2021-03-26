package com.nsoft.association.currencyexchangeconversion;

public class CCResponse {
	
	private String countryCode;
	
	private double amount;
	
	private double convertedAmount;

	public CCResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CCResponse(String countryCode, double amount, double convertedAmount) {
		super();
		this.countryCode = countryCode;
		this.amount = amount;
		this.convertedAmount = convertedAmount;
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

	public double getConvertedAmount() {
		return convertedAmount;
	}

	public void setConvertedAmount(double convertedAmount) {
		this.convertedAmount = convertedAmount;
	}

	@Override
	public String toString() {
		return "CCResponse [countryCode=" + countryCode + ", amount=" + amount + ", convertedAmount=" + convertedAmount
				+ ", getCountryCode()=" + getCountryCode() + ", getAmount()=" + getAmount() + ", getConvertedAmount()="
				+ getConvertedAmount() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	
	
	
	
	

}
