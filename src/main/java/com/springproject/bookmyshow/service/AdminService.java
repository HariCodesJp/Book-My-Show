package com.springproject.bookmyshow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springproject.bookmyshow.dao.AdminDao;
import com.springproject.bookmyshow.dto.AdminDto;
import com.springproject.bookmyshow.entity.AdminEntity;
import com.springproject.bookmyshow.util.ResponseStructure;

@Service
public class AdminService 
{
	@Autowired
	
	AdminDao adminDao;
	
	
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
}
