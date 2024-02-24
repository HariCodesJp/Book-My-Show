package com.springproject.bookmyshow.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springproject.bookmyshow.dao.UserDao;
import com.springproject.bookmyshow.dto.UserDto;
import com.springproject.bookmyshow.entity.UserEntity;
import com.springproject.bookmyshow.exception.EmailMismatch;
import com.springproject.bookmyshow.exception.PasswordMismatch;
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
	
	//To Save User Details
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
	
	//To Find User Details
	public ResponseEntity<ResponseStructure<UserDto>> findUser(int userId)
	{
		
		UserEntity user = userDao.findUser(userId);
		if(user != null)
		{
			UserDto dto = new UserDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(userDao.findUser(userId), dto);
			ResponseStructure<UserDto> structure = new ResponseStructure<UserDto>();
			structure.setMessage("User Details");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			
			return new ResponseEntity<ResponseStructure<UserDto>>(structure,HttpStatus.FOUND);
		}
		 throw new UserNotFound("User Details Does Not Exist");
	}
	
	//To Delete User Details
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
	
	//To Update User Details
	public ResponseEntity<ResponseStructure<UserEntity>> updateUser(UserEntity user,int userId)
	{
		UserEntity userOne = userDao.findUser(userId);
		if(userOne != null)
		{
			ResponseStructure<UserEntity> structure = new ResponseStructure<UserEntity>();
			UserEntity userTwo = userDao.updateUser(user, userId);
			
			structure.setMessage("User Details Updated");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(userTwo);
			
			return new ResponseEntity<ResponseStructure<UserEntity>>(structure,HttpStatus.OK);
		}
		throw new UserNotFound("User details Not exist In this Id");
	}
	
	//To Find All User Details
	public ResponseEntity<ResponseStructure<List<UserDto>>> findAllUser()
	{
		List<UserEntity> dtoList = userRepo.findAll();
		if(!dtoList.isEmpty())
		{
		List<UserDto> userDtoList=new ArrayList<>();
		ModelMapper mapper=new ModelMapper();
		for (UserEntity entity : dtoList) 
		{
	     userDtoList.add(mapper.map(entity, UserDto.class));
	    }
		ResponseStructure<List<UserDto>> structure=new ResponseStructure<List<UserDto>>();
		structure.setMessage("User Details");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(userDtoList);;
		return new ResponseEntity<ResponseStructure<List<UserDto>>>(structure,HttpStatus.FOUND);
		}
		throw new UserNotFound("User Details Not Present");
	}
	
	public ResponseEntity<ResponseStructure<UserDto>> findByEmail(String userEmail,String userPassword){
		
		UserEntity user=userDao.findByUserEmail(userEmail);
		if(user != null) 
		{
			if(user.getUserPassword().equals(userPassword))
			{
				UserDto uDto=new UserDto();
				ModelMapper mapper=new ModelMapper();
				mapper.map(user, uDto);
				ResponseStructure<UserDto> structure=new ResponseStructure<UserDto>();
				structure.setMessage("User login success");
				structure.setStatus(HttpStatus .FOUND.value());
				structure.setData(uDto);
				return new ResponseEntity<ResponseStructure<UserDto>> (structure,HttpStatus.FOUND);
			}
		
			throw new PasswordMismatch("The Password you have entered is Mismatched");
		}
		throw new EmailMismatch("The Email you have entered is mismatched");
	}
	
}
