package com.springproject.bookmyshow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springproject.bookmyshow.dao.TheatreAdminDao;
import com.springproject.bookmyshow.dao.TheatreDao;
import com.springproject.bookmyshow.dto.TheatreAdminDto;
import com.springproject.bookmyshow.entity.TheatreAdminEntity;
import com.springproject.bookmyshow.entity.TheatreEntity;
import com.springproject.bookmyshow.exception.EmailMismatch;
import com.springproject.bookmyshow.exception.PasswordMismatch;
import com.springproject.bookmyshow.exception.TheatreAdminNotFound;
import com.springproject.bookmyshow.exception.TheatreNotFound;
import com.springproject.bookmyshow.repo.TheatreAdminRepo;
import com.springproject.bookmyshow.util.ResponseStructure;

@Service
public class TheatreAdminService 
{
	@Autowired
	TheatreAdminDao theatreAdminDao;
	@Autowired
	TheatreAdminRepo theatreAdminRepo;
	@Autowired
	TheatreDao theatreDao;
	
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> saveTheatreAdmin(TheatreAdminEntity theatreAdmin)
	{
		TheatreAdminDto taDto=new TheatreAdminDto();
		ModelMapper mapper=new ModelMapper();
		mapper.map(theatreAdminDao.saveTheatreAdmin(theatreAdmin), taDto);
		ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
		structure.setMessage("Theatre Admin save success");
		structure.setStatus(HttpStatus .CREATED.value());
		structure.setData(taDto);
		return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> findTheatreAdmin(int adminId)
	{
		
		TheatreAdminEntity admin=theatreAdminDao.findTheatreAdmin(adminId);
		if(admin != null) 
		{
		TheatreAdminDto aDto = new TheatreAdminDto();
		ModelMapper mapper = new ModelMapper();
		mapper.map(admin, aDto);
		ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
		structure.setMessage(" Theatre Admin found success");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(aDto);
		return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.FOUND);
		}
		throw new TheatreAdminNotFound("theatre Admin not found for the given id");
	}
	
	//To Update Theatre Admin details
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> updateTheatreAdmin(TheatreAdminEntity theatreAdmin,int theatreAdminId)
	{
		
		TheatreAdminEntity exAdmin=theatreAdminDao.findTheatreAdmin(theatreAdminId);
		if(exAdmin != null) 
		{
		
		TheatreEntity theatre = theatreDao.findTheatre(theatreAdminId);
		exAdmin.setTheatre(theatre);
		TheatreAdminDto taDto=new TheatreAdminDto();
		ModelMapper mapper=new ModelMapper();
		mapper.map(theatreAdminDao.updateTheatreAdmin(exAdmin,theatreAdminId), taDto);
		ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
		structure.setMessage("Theatre Admin update success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(taDto);
		return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.OK);
		}
		throw new TheatreAdminNotFound("theatre Admin not updated because,theatre Admin not found for the given id");
	}
	
	//To delete theatre admin details
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> deleteTheatreAdmin(int theatreAdminId)
	{
		
		TheatreAdminEntity admin=theatreAdminDao.deleteTheatreAdmin(theatreAdminId);
		if(admin != null) 
		{
		TheatreAdminDto taDto=new TheatreAdminDto();
		ModelMapper mapper=new ModelMapper();
		mapper.map(admin, taDto);
		ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
		structure.setMessage("Theatre Admin delete success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(taDto);
		return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.OK);
		}
		throw new TheatreAdminNotFound("theatre Admin not deleted because,theatre Admin not found for the given id");
	}
	
	//To Assign theatre to theatre admin
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> assignTheatreToTheatreAdmin(int theatreAdminId,int theatreId)
	{
		
		TheatreAdminEntity tAdmin=theatreAdminDao.findTheatreAdmin(theatreAdminId);
		TheatreEntity theatre=theatreDao.findTheatre(theatreId);
		if(tAdmin != null) 
		{
			if(theatre != null) 
			{
				TheatreAdminDto taDto=new TheatreAdminDto();
				ModelMapper mapper=new ModelMapper();
				tAdmin.setTheatre(theatre);
				mapper.map(theatreAdminDao.updateTheatreAdmin(tAdmin, theatreAdminId), taDto);
				ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
				structure.setMessage("assign theatre to Theatre Admin success");
				structure.setStatus(HttpStatus .OK.value());
				structure.setData(taDto);
				return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.OK);
			}
			throw new TheatreNotFound("theatre not assigned to the theatre admin because,theatre not found for the given id");
		}
		throw new TheatreAdminNotFound("we can't assign theatre to theatre admin because,theatre Admin not found for the given id");
	}
	
	//Theater Admin Login
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> findByTheatreAdminEmail(String theatreAdminEmail,String theatreAdminPassword)
	{
		
		TheatreAdminEntity theatreAdmin = theatreAdminDao.findByTheatreAdminEmail(theatreAdminEmail);
		if(theatreAdmin != null) 
		{
			if(theatreAdmin.getTheatreAdminPassword().equals(theatreAdminPassword))
			{
				TheatreAdminDto theatreAdminDto = new TheatreAdminDto();
				ModelMapper mapper=new ModelMapper();
				mapper.map(theatreAdmin, theatreAdminDto);
				ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
				structure.setMessage("User login success");
				structure.setStatus(HttpStatus .FOUND.value());
				structure.setData(theatreAdminDto);
				return new ResponseEntity<ResponseStructure<TheatreAdminDto>> (structure,HttpStatus.FOUND);
			}
		
			throw new PasswordMismatch("The Password you have entered is Mismatched");
		}
		throw new EmailMismatch("The Email you have entered is mismatched");
	}
	
}
