package com.springproject.bookmyshow.controller;

import java.time.LocalDate;
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

import com.springproject.bookmyshow.entity.PaymentEntity;
import com.springproject.bookmyshow.entity.SeatTypes;
import com.springproject.bookmyshow.entity.TicketsEntity;
import com.springproject.bookmyshow.service.TicketsService;
import com.springproject.bookmyshow.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("ticket")
public class TicketsController 
{
	@Autowired
	TicketsService tService;
	
	@PostMapping("saveticket")
	public ResponseEntity<ResponseStructure<TicketsEntity>> saveTicket(@Valid @RequestBody TicketsEntity ticket,BindingResult result)
	{
		return tService.saveTicket(ticket);
	}
	
	@GetMapping("findticket")
	public ResponseEntity<ResponseStructure<TicketsEntity>> findTicket(@RequestParam int ticketId)
	{
		return tService.findTicket(ticketId);
	}
	
	@DeleteMapping("cancelBooking")
	public ResponseEntity<ResponseStructure<PaymentEntity>> cancelBooking(int ticketId,@RequestParam String userEmail,@RequestParam String userPassword)
	{
		return tService.cancelBooking(ticketId, userEmail, userPassword);
	}
	
	@DeleteMapping("deleteticket")
	public ResponseEntity<ResponseStructure<TicketsEntity>> deleteTicket(@RequestParam int ticketId)
	{
		return tService.deleteTicket(ticketId);
	}
	
	@PutMapping("updateticket")
	public ResponseEntity<ResponseStructure<TicketsEntity>> updateTicket(@Valid @RequestBody TicketsEntity ticket,@RequestParam int ticketId,BindingResult result)
	{
		return tService.updateTicket(ticket,ticketId);
	}
	
	@PostMapping("bookTicket")
	public ResponseEntity<ResponseStructure<TicketsEntity>> ticketBooking(@RequestParam String userEmail,@RequestParam String userPassword,@RequestParam int movieId,@RequestParam SeatTypes seatType,@RequestBody List<Integer> seatIds,@RequestParam LocalDate bookingDate,@RequestParam String paymentMethod)
	{
		return tService.ticketBooking(userEmail, userPassword, movieId, seatType, seatIds, bookingDate, paymentMethod);
	}
	
	@GetMapping("findAllTicket")
	public ResponseEntity<ResponseStructure<List<TicketsEntity>>> findAllTicket(){
		return tService.findAllTicket();
	}
}
