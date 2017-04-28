package com.my.spring.exception;

public class CategoryException extends Exception {

	public CategoryException(String message)
	{
		super("CategoryException-"+message);
	}
	
	public CategoryException(String message, Throwable cause)
	{
		super("CategoryException-"+message,cause);
	}
	
}
