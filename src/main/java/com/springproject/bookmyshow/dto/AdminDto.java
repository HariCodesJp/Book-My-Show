package com.springproject.bookmyshow.dto;



import com.springproject.bookmyshow.entity.MovieEntity;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto 
{
	private int adminId;
	private String adminName;
	private String adminEmail;
	
	@OneToMany
	MovieEntity dto;
}
