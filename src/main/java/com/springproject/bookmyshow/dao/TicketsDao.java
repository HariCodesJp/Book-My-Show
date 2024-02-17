package com.springproject.bookmyshow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springproject.bookmyshow.entity.TicketsEntity;
import com.springproject.bookmyshow.repo.TicketsRepo;

@Repository
public class TicketsDao 
{
	@Autowired
	 TicketsRepo ticketRepo;
	
	public TicketsEntity saveTickets(TicketsEntity ticket)
	{
		return ticketRepo.save(ticket);
	}
	
	public TicketsEntity findTickets(int ticketId)
	{
		Optional<TicketsEntity> tickets = ticketRepo.findById(ticketId);
		if(tickets.isPresent())
		{
			return tickets.get();
		}
		return null;
	}
	
	public TicketsEntity deleteTickets(int ticketId)
	{
		TicketsEntity ticket = findTickets(ticketId);
		if(ticket != null)
		{
			ticketRepo.delete(ticket);
			return ticket;
		}
		return null;
	}
	
	public TicketsEntity updateTicket(TicketsEntity ticket,int ticketId)
	{
		TicketsEntity ticketOne = findTickets(ticketId);
		if(ticketOne != null)
		{
			ticketOne.setTicketId(ticketId);
			return ticketRepo.save(ticket);
		}
		
		return null;
	}
	
	public List<TicketsEntity> findAllTicket() {
		return ticketRepo.findAll();
	}
}
