package com.my.spring.exception;

public class CartException extends Exception
{
	public CartException(String message)
	{
		super("CartException-"+ message);
	}
	
	public CartException(String message, Throwable cause)
	{
		super("CartException-"+ message,cause);
	}
}