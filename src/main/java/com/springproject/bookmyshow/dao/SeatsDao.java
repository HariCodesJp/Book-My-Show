package com.springproject.bookmyshow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springproject.bookmyshow.entity.SeatsEntity;
import com.springproject.bookmyshow.repo.SeatsRepo;

@Repository
public class SeatsDao 
{
	@Autowired
	SeatsRepo seatsRepo;
	
	public SeatsEntity saveSeats(SeatsEntity seats)
	{
		return seatsRepo.save(seats);
	}
	
	public SeatsEntity findSeats(int seatId)
	{
		Optional<SeatsEntity>  seats = seatsRepo.findById(seatId);
		if(seats.isPresent())
		{
			return seats.get();
		}
		
		return null;
	}
	
	public List<SeatsEntity> findAllSeat() {
		return seatsRepo.findAll();
	}
	
	public SeatsEntity updateSeat(SeatsEntity seat,int seatId){
		SeatsEntity newSeat=findSeats(seatId);
		if(newSeat != null) {
			seat.setSeatId(seatId);
			return seatsRepo.save(seat);
		}
		return null;
	}
	
	public SeatsEntity deleteSeat(int seatId)
	{
		SeatsEntity seats = findSeats(seatId);
		if(seats != null)
		{
			 seatsRepo.delete(seats);
			return seats;
		}
		return null;
	}
}