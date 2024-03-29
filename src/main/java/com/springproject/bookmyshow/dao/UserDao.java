package com.springproject.bookmyshow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springproject.bookmyshow.entity.UserEntity;
import com.springproject.bookmyshow.repo.UserRepo;

@Repository
public class UserDao 
{
	@Autowired
	UserRepo userRepo;
	
	public UserEntity saveUser(UserEntity user)
	{
	    return	userRepo.save(user);
	}
	
	public UserEntity findUser(int userId)
	{
		Optional<UserEntity> user = userRepo.findById(userId);
		if(user.isPresent())
		{
			return user.get();
		}
		
		return null;
		
	}
	
	public UserEntity updateUser(UserEntity user,int userId)
	{
		UserEntity userOne = findUser(userId);
		
		if(userOne!= null)
		{
			user.setUserId(userId);
			return userRepo.save(user);
			
		}
		return null;
	}
	
	public UserEntity deleteUser( int userId )
	{
		UserEntity user = findUser(userId);
		if(user != null)
		{
			userRepo.delete(user);
			
			return user;
		}
		return null;
	}
	
	public List<UserEntity> findAllUser(){
		return userRepo.findAll();
	}
	
	public UserEntity findByUserEmail(String userEmail)
	{
		return userRepo.findByEmail(userEmail);
	}
	
}
