package com.springproject.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springproject.bookmyshow.entity.TheatreAdminEntity;
import com.springproject.bookmyshow.repo.TheatreAdminRepo;

@Repository
public class TheatreAdminDao 
{
	@Autowired
	TheatreAdminRepo theatreAdminRepo;
	
	public TheatreAdminEntity saveTheatreAdmin(TheatreAdminEntity admin)
	{
		return theatreAdminRepo.save(admin);
	}
	
	public TheatreAdminEntity findTheatreAdmin(int theatreaAdminId)
	{
		Optional<TheatreAdminEntity> admin = theatreAdminRepo.findById(theatreaAdminId);
		if(admin.isPresent())
		{
			return admin.get();
		}
		return null;
	}
	
	public TheatreAdminEntity deleteTheatreAdmin(int theatreAdminId)
	{
		TheatreAdminEntity admin = findTheatreAdmin(theatreAdminId);
		if(admin != null)
		{
			theatreAdminRepo.delete(admin);
			return admin;
		}
		return null;
	}
	
	public TheatreAdminEntity updateTheatreAdmin(TheatreAdminEntity admin,int theatreAdminId)
	{
		TheatreAdminEntity adminOne = findTheatreAdmin(theatreAdminId);
		if(adminOne != null)
		{
			admin.setTheatreAdminId(theatreAdminId);
			return theatreAdminRepo.save(admin);
		}
		return null;
	}
	
	public TheatreAdminEntity findByTheatreAdminEmail(String theatreAdminEmail)
	{
		return theatreAdminRepo.findTheatreAdminByEmail(theatreAdminEmail);
	}
}
