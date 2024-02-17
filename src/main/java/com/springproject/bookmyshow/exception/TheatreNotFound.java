package com.springproject.bookmyshow.exception;

public class TheatreNotFound extends RuntimeException
{
	String message;

	public String getMessage() {
		return message;
	}

	public TheatreNotFound(String message) 
	{
		this.message = message;
	}
	
	
}
