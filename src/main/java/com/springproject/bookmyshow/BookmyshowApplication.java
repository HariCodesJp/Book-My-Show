package com.springproject.bookmyshow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(servers = {@Server(url = "http://localhost:8080", description = "It is an Testing Server"), @Server(url = "Bookmyshow", description = "It is basically an ticket Booking application")})
public class BookmyshowApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(BookmyshowApplication.class, args);
	}
	
	

}
