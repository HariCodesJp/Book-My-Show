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

import com.springproject.bookmyshow.dto.UserDto;
import com.springproject.bookmyshow.entity.UserEntity;
import com.springproject.bookmyshow.service.UserService;
import com.springproject.bookmyshow.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController 
{
	@Autowired
	UserService uService;
	
	@PostMapping("saveuser")
	public ResponseEntity<ResponseStructure<UserDto>> saveUser(@Valid @RequestBody UserEntity user,BindingResult result)
	{
		return uService.saveUser(user);
	}
	
	@GetMapping("finduser")
	public ResponseEntity<ResponseStructure<UserDto>> findUser(@RequestParam int userId)
	{
		return uService.findUser(userId);
	}
	
	@GetMapping("userlogin")
	public ResponseEntity<ResponseStructure<UserDto>> userLogin(@RequestParam String userEmail,@RequestParam String userPassword)
	{
		return uService.findByEmail(userEmail, userPassword);
	}
	
	@PutMapping("updateuser")
	public ResponseEntity<ResponseStructure<UserEntity>> updateUser(@Valid @RequestBody UserEntity user,@RequestParam int userId,BindingResult result)
	{
		return uService.updateUser(user, userId);
	}
	
	@DeleteMapping("deleteuser")
	public ResponseEntity<ResponseStructure<UserEntity>> deleteUser(@RequestParam int userId)
	{
		return uService.deleteUser(userId);
	}
	
	@GetMapping("findAllUser")
	public ResponseEntity<ResponseStructure<List<UserDto>>> findAllUser(){
		return uService.findAllUser();
	}
}
