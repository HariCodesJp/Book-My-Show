package com.springproject.bookmyshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.bookmyshow.dto.AdminDto;
import com.springproject.bookmyshow.entity.AdminEntity;
import com.springproject.bookmyshow.service.AdminService;
import com.springproject.bookmyshow.util.ResponseStructure;

@RestController
public class AdminController 
{
	
	@Autowired
	AdminService adminService;
	
	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(AdminEntity admin)
	{
		return adminService.saveAdmin(admin);
	}
}
