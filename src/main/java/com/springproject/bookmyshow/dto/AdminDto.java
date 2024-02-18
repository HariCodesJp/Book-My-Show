package com.springproject.bookmyshow.dto;
import java.util.List;

import com.springproject.bookmyshow.entity.TheatreEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto 
{
	private int adminId;
	private String adminName;
	private String adminEmail;
	private long adminContact;
	List<TheatreEntity> theatre;
	
}
