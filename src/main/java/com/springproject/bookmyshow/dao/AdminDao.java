package com.springproject.bookmyshow.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springproject.bookmyshow.entity.AdminEntity;
import com.springproject.bookmyshow.repo.AdminRepo;

@Repository
public class AdminDao 
{
	@Autowired
	
	AdminRepo adminRepo;
	
	public AdminEntity saveAdmin(AdminEntity admin)
	{
		return adminRepo.save(admin);
	}
	
}
