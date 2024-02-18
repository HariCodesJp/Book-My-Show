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

import com.springproject.bookmyshow.entity.SeatsEntity;
import com.springproject.bookmyshow.service.SeatsService;
import com.springproject.bookmyshow.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("seats")
public class SeatsController 
{
	@Autowired
	SeatsService sService;
	
	@PostMapping("saveseat")
	public ResponseEntity<ResponseStructure<SeatsEntity>> saveSeat(@Valid @RequestBody SeatsEntity seat,BindingResult result)
	{
		return sService.saveSeat(seat);
	}
	
	@GetMapping("findSeat")
	public ResponseEntity<ResponseStructure<SeatsEntity>> findSeat(@RequestParam int seatId){
		return sService.findSeat(seatId);
	}
	
	@DeleteMapping("deleteSeat")
	public ResponseEntity<ResponseStructure<SeatsEntity>> deleteSeat(@RequestParam int seatId){
		return sService.deleteSeat(seatId);
	}
	
	@PutMapping("updateseat")
	public ResponseEntity<ResponseStructure<SeatsEntity>> updateSeat(@Valid @RequestBody SeatsEntity seat,@RequestParam int seatId,BindingResult result)
	{
		return sService.updateSeat(seat, seatId);
	}
	
	@GetMapping("findallseat")
	public ResponseEntity<ResponseStructure<List<SeatsEntity>>> findAllSeat(){
		return sService.findAllSeat();
	}
}
