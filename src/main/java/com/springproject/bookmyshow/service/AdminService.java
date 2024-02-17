package com.springproject.bookmyshow.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springproject.bookmyshow.dao.AdminDao;
import com.springproject.bookmyshow.dao.UserDao;
import com.springproject.bookmyshow.dto.AdminDto;
import com.springproject.bookmyshow.dto.UserDto;
import com.springproject.bookmyshow.entity.AdminEntity;
import com.springproject.bookmyshow.entity.TheatreEntity;
import com.springproject.bookmyshow.entity.UserEntity;
import com.springproject.bookmyshow.exception.AdminNotFound;
import com.springproject.bookmyshow.exception.TheatreNotFound;
import com.springproject.bookmyshow.exception.UserNotFound;
import com.springproject.bookmyshow.repo.TheatreRepo;
import com.springproject.bookmyshow.repo.UserRepo;
import com.springproject.bookmyshow.util.ResponseStructure;

@Service
public class AdminService 
{
	@Autowired
	AdminDao adminDao;
	
	@Autowired
	TheatreRepo theatreRepo;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserRepo userRepo;
	
	
	
	
	//To Save Admin Details
	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(AdminEntity admin)
	{
		AdminDto adminDto = new AdminDto();
		ModelMapper mapper = new ModelMapper();
		mapper.map(adminDao.saveAdmin(admin), adminDto);
		ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
		structure.setMessage("Admin Details Saved");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(adminDto);
		
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.CREATED);
	}
	
	//To Delete Admin Details
	public ResponseEntity<ResponseStructure<AdminEntity>> deleteAdmin(int adminId)
	{
		AdminEntity admin = adminDao.findAdmin(adminId);
		if(admin != null)
		{
			ResponseStructure<AdminEntity> structure = new ResponseStructure<AdminEntity>();
			AdminEntity adminOne = adminDao.deleteAdmin(adminId);
			
			structure.setMessage("Admin Details Are Deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(adminOne);
			
			return new ResponseEntity<ResponseStructure<AdminEntity>>(structure,HttpStatus.OK);
		}
		throw new AdminNotFound("Admin Details Does Not Exist In This Id");
	}
	
	//To FindAll Users
	public ResponseEntity<ResponseStructure<List<UserDto>>> findAllUser()
	{
		
		List<UserEntity> dto = userRepo.findAll();
		if(dto.contains(dto))
		{
			List<UserDto> userDto = new ArrayList<UserDto>();
			ModelMapper mapper = new ModelMapper();
			mapper.map(dto, userDto);
			ResponseStructure<List<UserDto>> structure = new ResponseStructure<List<UserDto>>();
			structure.setMessage("All User Deatils");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(userDto);
			
			return new ResponseEntity<ResponseStructure<List<UserDto>>>(structure,HttpStatus.FOUND);
		}
		
		throw new UserNotFound("User Details Does not Exist");
		
	}
	
	//To Find Admin
	public ResponseEntity<ResponseStructure<AdminDto>> findAdmin(int adminId)
	{
		
		AdminEntity admin = adminDao.findAdmin(adminId);
		
		if(admin != null)
		{
			AdminDto dto = new AdminDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(admin, dto);
			ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
			structure.setMessage("Admin Details For This Id");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.FOUND);
		}
		
		throw new AdminNotFound("Admin Details does not exist on this Id");
	}
	
	//To Assign User To Admin
//	public ResponseEntity<ResponseStructure<AdminDto>> assignUserToAdmin(int adminId,int userId)
//	{
//		AdminEntity admin = adminDao.findAdmin(adminId);
//		UserEntity user = userDao.findUser(userId);
//		if(admin != null)
//		{
//			if(user != null)
//			{
//				admin.setEntity(user);
//				AdminDto dto = new AdminDto();
//				ModelMapper mapper = new ModelMapper();
//				mapper.map(admin, dto);
//				ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
//				structure.setMessage("User Added to Admin");
//				structure.setStatus(HttpStatus.OK.value());
//				structure.setData(dto);
//				adminDao.updateAdmin(admin, adminId);
//				
//				return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);	
//			}
//			throw new UserNotFound("User Details Does Not Exist In This Id");
//		}
//		throw new AdminNotFound("Admin Details Does Not Exist In This Id");
//	}
	
	//Update Admin Details
	public ResponseEntity<ResponseStructure<AdminEntity>> updateAdmin(AdminEntity admin,int adminId)
	{
		AdminEntity adminOne = adminDao.findAdmin(adminId);
		if(adminOne != null)
		{
			ResponseStructure<AdminEntity> structure = new ResponseStructure<AdminEntity>();
			AdminEntity adminDetails = adminDao.updateAdmin(adminOne, adminId);
			structure.setMessage("Admin Details Updated");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(adminDetails);
			
			return new ResponseEntity<ResponseStructure<AdminEntity>>(structure,HttpStatus.OK);
		}
		
		throw new AdminNotFound("Admin Details Does Not Exist In This Id");
	}
	
	//Assign Theatre details to Admin
	public ResponseEntity<ResponseStructure<AdminDto>> assignTheatreToAdmin(int adminId,List<Integer> theatre)
	{
		AdminEntity admin = adminDao.findAdmin(adminId);
		List<TheatreEntity> theatreAll = theatreRepo.findAllById(theatre);
		if(admin != null)
		{
			if(theatreAll != null)
			{
			admin.setTheatre(theatreAll);
			AdminDto dto = new AdminDto();
			ModelMapper mapper = new ModelMapper();
			mapper.map(adminDao.updateAdmin(admin, adminId), dto);
			
			ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
			structure.setMessage("Theatre is Assigned To Admin");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dto);
			
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
			}		
			throw new TheatreNotFound("Theatre Details Does Not Exist In This Id");
		}
		throw new AdminNotFound("Admin Details Does Not Exist In This Id");
		}
	
}
