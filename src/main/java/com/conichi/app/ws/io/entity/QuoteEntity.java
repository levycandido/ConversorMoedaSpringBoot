
package com.conichi.app.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "quotes")
public class QuoteEntity implements Serializable {

	private static final long serialVersionUID = -244712593981348519L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false, length = 10)
	private String currency;

	@Column(nullable = false)
	private Double value;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
