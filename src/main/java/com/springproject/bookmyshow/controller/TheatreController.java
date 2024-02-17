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

import com.springproject.bookmyshow.entity.TheatreEntity;
import com.springproject.bookmyshow.service.TheatreService;
import com.springproject.bookmyshow.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("theatre")
public class TheatreController 
{
	@Autowired
	TheatreService tService;
	
	@PostMapping("savetheatre")
	public ResponseEntity<ResponseStructure<TheatreEntity>> saveTheatre(@Valid @RequestBody TheatreEntity theatre,BindingResult result)
	{
		return tService.saveTheatre(theatre);
	}
	
	@PutMapping("assignMoviesToTheatre")
	public ResponseEntity<ResponseStructure<TheatreEntity>> assignMoviesToTheatre(@RequestParam int theatreId,@RequestBody List<Integer> movieIds) 
	{
		return tService.assignMoviesToTheatre(theatreId, movieIds);
	}

	@GetMapping("findtheatre")
	public ResponseEntity<ResponseStructure<TheatreEntity>> findTheatre(@RequestParam int theatreId)
	{
		return tService.findTheatre(theatreId);
	}
	
	@PutMapping("updatetheatre")
	public ResponseEntity<ResponseStructure<TheatreEntity>> updateTheatre(@Valid @RequestBody TheatreEntity theatre,@RequestParam int theatreId,BindingResult result)
	{
		return tService.updateTheatre(theatre, theatreId);
	}
	
	@DeleteMapping("deletetheatre")
	public ResponseEntity<ResponseStructure<TheatreEntity>> deleteTheatre(@RequestParam int theatreId)
	{
		return tService.findTheatre( theatreId);
	}
	
	@GetMapping("findAllTheatre")
	public ResponseEntity<ResponseStructure<List<TheatreEntity>>> findAllTheatre(){
		return tService.findAllTheatre();
	}
}
