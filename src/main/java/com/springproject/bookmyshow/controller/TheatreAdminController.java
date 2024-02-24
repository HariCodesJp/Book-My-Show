package com.springproject.bookmyshow.controller;

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

import com.springproject.bookmyshow.dto.TheatreAdminDto;
import com.springproject.bookmyshow.entity.TheatreAdminEntity;
import com.springproject.bookmyshow.service.TheatreAdminService;
import com.springproject.bookmyshow.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("theatreadmin")
public class TheatreAdminController 
{
	@Autowired
	TheatreAdminService taService;
	
	@PostMapping("savetheatreadmin")
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> saveAdmin( @Valid @RequestBody TheatreAdminEntity theatreAdmin,BindingResult result)
	{
		return taService.saveTheatreAdmin(theatreAdmin);
	}
	
	@GetMapping("findtheatreadmin")
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> findAdmin(@RequestParam int  theatreAdminId){
		return taService.findTheatreAdmin(theatreAdminId);
	}
	
	
	@PutMapping("assigntheatretotheatreAdmin")
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> assignTheatreToTheatreAdmin(@RequestParam int  theatreAdminId,@RequestParam int  theatreId)
	{
		return taService.assignTheatreToTheatreAdmin(theatreAdminId, theatreId);
	}

	@PutMapping("updatetheatreadmin")
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> updateAdmin(@Valid @RequestBody TheatreAdminEntity theatreAdmin,@RequestParam int theatreAdminId,BindingResult result)
	{
		return taService.updateTheatreAdmin(theatreAdmin, theatreAdminId);
	}
	
	@DeleteMapping("deletetheatreadmin")
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> deleteAdmin(@RequestParam int theatreAdminId)
	{
		return taService.deleteTheatreAdmin(theatreAdminId);
	}
	
	@GetMapping("theatreadminlogin")
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> theatreAdminLogin(@RequestParam String theatreAdminEmail,@RequestParam String theatreAdminPassword)
	{
		return taService.findByTheatreAdminEmail(theatreAdminEmail, theatreAdminPassword);
	}
}
