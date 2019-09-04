package com.conichi.app.ws.ui.model.response;

public class RestQuote {
	private String currency;
	private Double value;
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
}
