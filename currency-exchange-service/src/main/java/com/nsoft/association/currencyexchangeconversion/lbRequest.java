package com.nsoft.association.currencyexchangeconversion;

public class lbRequest {
	
	private String fromcode;
	
	private String tocode;
	
	

	public lbRequest(String fromcode, String tocode) {
		super();
		this.tocode = tocode;
		this.fromcode = fromcode;
	}

	public lbRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTocode() {
		return tocode;
	}

	public void setTocode(String tocode) {
		this.tocode = tocode;
	}

	public String getFromcode() {
		return fromcode;
	}

	public void getFromcode(String fromcode) {
		this.fromcode = fromcode;
	}

	@Override
	public String toString() {
		return "CCRequest [fromcode=" + fromcode + ", tocode=" + tocode + "]";
	}
	
	

}
