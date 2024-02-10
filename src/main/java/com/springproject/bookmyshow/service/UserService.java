package com.springproject.bookmyshow.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springproject.bookmyshow.dao.UserDao;
import com.springproject.bookmyshow.dto.UserDto;
import com.springproject.bookmyshow.entity.UserEntity;
import com.springproject.bookmyshow.exception.UserNotFound;
import com.springproject.bookmyshow.repo.UserRepo;
import com.springproject.bookmyshow.util.ResponseStructure;

@Service
public class UserService 
{
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserRepo userRepo;
	
	public ResponseEntity<ResponseStructure<UserDto>> saveUser(UserEntity user)
	{
		UserDto dto = new UserDto();
		ModelMapper mapper = new ModelMapper();
		mapper.map(userDao.saveUser(user),dto);
		ResponseStructure<UserDto> structure = new ResponseStructure<UserDto>();
		structure.setMessage("User Details Are Saved");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dto);
		
		return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<ResponseStructure<UserDto>> findUser(int userId)
	{
		UserDto dto = new UserDto();
		ModelMapper mapper = new ModelMapper();
		Optional<UserEntity> user = userRepo.findById(userId);
		if(user != null)
		{
			mapper.map(userDao.findUser(userId), dto);
			
			ResponseStructure<UserDto> structure = new ResponseStructure<UserDto>();
			structure.setMessage("User Details");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			
			return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.FOUND);
		}
		 throw new UserNotFound("User Details Does Not Exist");
	}
	
	public ResponseEntity<ResponseStructure<UserEntity>> deleteUser(int userId)
	{
		UserEntity user = userDao.findUser(userId);
		if(user != null)
		{
			ResponseStructure<UserEntity> structure = new ResponseStructure<UserEntity>();
			UserEntity userOne = userDao.deleteUser(userId);
			
			structure.setMessage("User Details Are Deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(userOne);
			
			return new ResponseEntity<ResponseStructure<UserEntity>>(structure,HttpStatus.OK);
		}
		throw new UserNotFound("User details Not exist on this Id");
	}
	
	public ResponseEntity<ResponseStructure<UserEntity>> updateUser(UserEntity user,int userId)
	{
		UserEntity userOne = userDao.findUser(userId);
		if(user != null)
		{
			ResponseStructure<UserEntity> structure = new ResponseStructure<UserEntity>();
			UserEntity userTwo = userDao.updateUser(userOne, userId);
			
			structure.setMessage("User Details Updated");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(userTwo);
			
			return new ResponseEntity<ResponseStructure<UserEntity>>(structure,HttpStatus.OK);
		}
		throw new UserNotFound("User details Not exist In this Id");
	}
}
