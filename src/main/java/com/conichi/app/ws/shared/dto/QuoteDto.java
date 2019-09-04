package com.conichi.app.ws.shared.dto;

import java.math.BigDecimal;

public class QuoteDto {
	private String currency;
	private Double value;
	
	public QuoteDto (String currency,Double value ){
		this.currency = currency;
		this.value = value;
	}
	
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
