package com.springproject.bookmyshow.dto;

import com.springproject.bookmyshow.entity.TheatreEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheatreAdminDto 
{
	private int theatreAdminID;
	private String theatreAdminName;
	private Long theatreAdminContact;
	private String theatreAdminEmail;
	private String theatreAdminPassword;
	private TheatreEntity adTheatre;

}
