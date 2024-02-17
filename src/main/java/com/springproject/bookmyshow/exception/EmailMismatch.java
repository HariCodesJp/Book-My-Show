package com.springproject.bookmyshow.exception;

public class EmailMismatch extends RuntimeException
{
	String message;

	public String getMessage() 
	{
		return message;
	}

	public EmailMismatch(String message) 
	{
		super();
		this.message = message;
	}
	
	
}
