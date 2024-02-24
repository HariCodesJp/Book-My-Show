package com.springproject.bookmyshow.dao;

import java.util.Optional;

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
	
	public AdminEntity findAdmin(int adminId)
	{
		Optional<AdminEntity> admin = adminRepo.findById(adminId);
		if(admin.isPresent())
		{
			return admin.get();
		}
		return null;
	}
	
	public AdminEntity deleteAdmin(int adminId)
	{
		AdminEntity admin = findAdmin(adminId);
		if(admin != null)
		{
			 adminRepo.delete(admin);
			return admin;
		}
		return null;
	}
	
	public AdminEntity updateAdmin(AdminEntity admin,int adminId)
	{
		AdminEntity adminOne = findAdmin(adminId);
		if(adminOne != null)
		{
			admin.setAdminId(adminId);
			return adminRepo.save(admin);
		}
		return null;
	}
	
	public AdminEntity findByAdminEmail(String adminEmail)
	{
		return adminRepo.findAdminEmail(adminEmail);
	}
}