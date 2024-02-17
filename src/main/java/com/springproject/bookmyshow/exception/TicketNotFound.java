package com.springproject.bookmyshow.exception;

public class TicketNotFound extends RuntimeException
{
	String message;
	
	public String getMessage() {
		return message;
	}

	public TicketNotFound(String message) {
		this.message = message;
	}
}
