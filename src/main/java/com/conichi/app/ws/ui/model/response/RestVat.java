package com.conichi.app.ws.ui.model.response;

public class RestVat {
	private String CountryCode;
	private String VatNumber;
	private boolean isValid;

	public RestVat(String countryCode, String vatNumber, boolean isValid) {
		super();
		CountryCode = countryCode;
		VatNumber = vatNumber;
		this.isValid = isValid;
	}

	public String getCountryCode() {
		return CountryCode;
	}

	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}

	public String getVatNumber() {
		return VatNumber;
	}

	public void setVatNumber(String vatNumber) {
		VatNumber = vatNumber;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	

}
