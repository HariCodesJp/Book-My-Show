package com.springproject.bookmyshow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springproject.bookmyshow.dao.SeatsDao;
import com.springproject.bookmyshow.entity.SeatsEntity;
import com.springproject.bookmyshow.exception.SeatNotFound;
import com.springproject.bookmyshow.util.ResponseStructure;

@Service
public class SeatsService 
{
	@Autowired
	SeatsDao sDao;
	
	//To Save Seat details
	public ResponseEntity<ResponseStructure<SeatsEntity>> saveSeat(SeatsEntity seat) 
	{
	ResponseStructure<SeatsEntity> structure=new ResponseStructure<SeatsEntity>();
	structure.setMessage("Seat details has been sucessfully saved");
	structure.setStatus(HttpStatus.CREATED.value());
	structure.setData(sDao.saveSeats(seat));
	return new ResponseEntity<ResponseStructure<SeatsEntity>>(structure,HttpStatus.CREATED);
	}
	
	//To Find seat details
	public ResponseEntity<ResponseStructure<SeatsEntity>> findSeat(int seatId) 
	{
	SeatsEntity seat=sDao.findSeats(seatId);
	if(seat != null) 
	{
	ResponseStructure<SeatsEntity> structure=new ResponseStructure<SeatsEntity>();
	structure.setMessage("seat find success");
	structure.setStatus(HttpStatus .FOUND.value());
	structure.setData(seat);
	return new ResponseEntity<ResponseStructure<SeatsEntity>>(structure,HttpStatus.FOUND);
	}
	throw new SeatNotFound("Seat details does not found for the given id");
	}
	
	//To delete seat details
	public ResponseEntity<ResponseStructure<SeatsEntity>> deleteSeat(int seatId) 
	{
		SeatsEntity seat=sDao.findSeats(seatId);
		if(seat != null) 
		{
		ResponseStructure<SeatsEntity> structure=new ResponseStructure<SeatsEntity>();
		structure.setMessage("seat delete success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(sDao.deleteSeat(seatId));
		return new ResponseEntity<ResponseStructure<SeatsEntity>>(structure,HttpStatus.OK);
		}
		throw new SeatNotFound("seat not deleted because,seat not found for the given id");
	}
	
	//To Update Seat Details
	public ResponseEntity<ResponseStructure<SeatsEntity>> updateSeat(SeatsEntity seat,int seatId) 
	{
		SeatsEntity exSeat=sDao.updateSeat(seat,seatId);
		if(exSeat != null) 
		{
		ResponseStructure<SeatsEntity> structure=new ResponseStructure<SeatsEntity>();
		structure.setMessage("seat update success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(exSeat);
		return new ResponseEntity<ResponseStructure<SeatsEntity>>(structure,HttpStatus.OK);
		}
		throw new SeatNotFound("Seat details dosen't updated because,seat not found for the given id");
	}
	
	//To Find All Seats
	public ResponseEntity<ResponseStructure<List<SeatsEntity>>> findAllSeat() 
	{
		ResponseStructure<List<SeatsEntity>> structure=new ResponseStructure<List<SeatsEntity>>();
		List<SeatsEntity> seatList=sDao.findAllSeat();
		structure.setMessage(" find all seat success");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(seatList);
		return new ResponseEntity<ResponseStructure<List<SeatsEntity>>>(structure,HttpStatus.FOUND);
	}
}
