package com.springproject.bookmyshow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.bookmyshow.dto.AdminDto;
import com.springproject.bookmyshow.dto.UserDto;
import com.springproject.bookmyshow.entity.AdminEntity;
import com.springproject.bookmyshow.service.AdminService;
import com.springproject.bookmyshow.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("admin")
public class AdminController 
{
	
	@Autowired
	AdminService adminService;
	
	@PostMapping("saveadmin")
	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(@Valid @RequestBody AdminEntity admin,BindingResult result)
	{
		return adminService.saveAdmin(admin);
	}
	
	@GetMapping("findadmin")
	public ResponseEntity<ResponseStructure<AdminDto>> findAdmin(@RequestParam int adminId)
	{
		return adminService.findAdmin(adminId);
	}
	
	@DeleteMapping("deleteadmin")
	public ResponseEntity<ResponseStructure<AdminEntity>> deleteAdmin(@RequestParam int adminId)
	{
		return adminService.deleteAdmin(adminId);
	}
	
	@PutMapping("updateadmin")
	ResponseEntity<ResponseStructure<AdminEntity>> updateAdmin(@Valid @RequestBody AdminEntity admin,@RequestParam int adminId,BindingResult result)
	{
		return adminService.updateAdmin(admin, adminId);
	}
	
	@GetMapping("alluserdetails")
	public ResponseEntity<ResponseStructure<List<UserDto>>> findAllUser()
	{
		return adminService.findAllUser();
	}
	
	@PutMapping("assigntheatrestoadmin")
	public ResponseEntity<ResponseStructure<AdminDto>> assignTheatresToAdmin(@RequestParam int adminId,@RequestBody List<Integer> theatreId){
		return adminService.assignTheatreToAdmin(adminId, theatreId);
	}
	
	@GetMapping("adminlogin")
	public ResponseEntity<ResponseStructure<AdminDto>> adminLogin(@RequestParam String adminEmail,@RequestParam String adminPassword)
	{
		return adminService.findByEmail(adminEmail, adminPassword);
	}
	
	
}
