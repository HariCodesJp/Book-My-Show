package com.springproject.bookmyshow.dto;

import java.util.List;

import com.springproject.bookmyshow.entity.MovieEntity;
import com.springproject.bookmyshow.entity.TicketsEntity;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto 

{
	private int userId;
	private int userName;
	private int userEmail;
	
	@OneToMany
	TicketsEntity ticket;
}
