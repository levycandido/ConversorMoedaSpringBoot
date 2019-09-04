package com.conichi.app.ws.exception;

public class CurrencyNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1348771109171435607L;

	public CurrencyNotFoundException(String message)
	{
		super(message);
	} 
}