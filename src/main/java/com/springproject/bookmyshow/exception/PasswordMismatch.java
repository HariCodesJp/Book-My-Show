package com.springproject.bookmyshow.exception;

public class PasswordMismatch extends RuntimeException
{
	String message;

	public String getMessage() 
	{
		return message;
	}

	public PasswordMismatch(String message) 
	{
		this.message = message;
	}
	
	
}
