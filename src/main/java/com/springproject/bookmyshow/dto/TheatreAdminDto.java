package com.springproject.bookmyshow.dto;

import com.springproject.bookmyshow.entity.TheatreEntity;

import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheatreAdminDto 
{
	private int theatreAdminId;
	private String theatreAdminName;
	private Long theatreAdminContact;
	private String theatreAdminEmail;
	private String theatreAdminPassword;
	@OneToOne
	private TheatreEntity theatre;

}
