package com.springproject.bookmyshow.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springproject.bookmyshow.dao.MovieDao;
import com.springproject.bookmyshow.dao.PaymentDao;
import com.springproject.bookmyshow.dao.SeatsDao;
import com.springproject.bookmyshow.dao.TicketsDao;
import com.springproject.bookmyshow.dao.UserDao;
import com.springproject.bookmyshow.entity.MovieEntity;
import com.springproject.bookmyshow.entity.PaymentEntity;
import com.springproject.bookmyshow.entity.SeatTypes;
import com.springproject.bookmyshow.entity.SeatsEntity;
import com.springproject.bookmyshow.entity.TicketsEntity;
import com.springproject.bookmyshow.entity.UserEntity;
import com.springproject.bookmyshow.exception.SeatNotFound;
import com.springproject.bookmyshow.exception.TicketNotFound;
import com.springproject.bookmyshow.exception.UserNotFound;
import com.springproject.bookmyshow.repo.SeatsRepo;
import com.springproject.bookmyshow.repo.UserRepo;
import com.springproject.bookmyshow.util.ResponseStructure;

@Service
public class TicketsService 
{
	@Autowired
	UserDao uDao;
	
	@Autowired
	TicketsDao tDao;
	
	@Autowired
	SeatsDao sDao;
	
	MovieService mService;
	@Autowired
	SeatsRepo sRepo;
	
	PaymentDao pDao;
	@Autowired
	MovieDao mDao;
	
	//To Save Ticket Details
	public ResponseEntity<ResponseStructure<TicketsEntity>> saveTicket(TicketsEntity ticket) 
	{
		ResponseStructure<TicketsEntity> structure=new ResponseStructure<TicketsEntity>();
		structure.setMessage("Ticket save success");
		structure.setStatus(HttpStatus .CREATED.value());
		structure.setData(tDao.saveTickets(ticket));
		return new ResponseEntity<ResponseStructure<TicketsEntity>>(structure,HttpStatus.CREATED);
	}
	
	//To Find Ticket Details
	public ResponseEntity<ResponseStructure<TicketsEntity>> findTicket(int ticketId) 
	{
		ResponseStructure<TicketsEntity> structure=new ResponseStructure<TicketsEntity>();
		TicketsEntity ticket=tDao.findTickets(ticketId);
		if(ticket != null) 
		{
		structure.setMessage("Ticket found success");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(ticket);
		return new ResponseEntity<ResponseStructure<TicketsEntity>>(structure,HttpStatus.FOUND);
		}
		throw new TicketNotFound("ticket not found for the given id");
	}
	
	//To Delete Ticket Details
	public ResponseEntity<ResponseStructure<TicketsEntity>> deleteTicket(int ticketId) {
		ResponseStructure<TicketsEntity> structure=new ResponseStructure<TicketsEntity>();
		TicketsEntity ticket=tDao.findTickets(ticketId);
		if(ticket != null) {
		structure.setMessage("Ticket Delete success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(tDao.deleteTickets(ticketId));
		return new ResponseEntity<ResponseStructure<TicketsEntity>>(structure,HttpStatus.OK);
		}
		throw new TicketNotFound("ticket not deleted because,ticket not found for the given id");
	}
  	
	//To Update Ticket Details
	public ResponseEntity<ResponseStructure<TicketsEntity>> updateTicket(TicketsEntity ticket,int ticketId) 
	{
		ResponseStructure<TicketsEntity> structure=new ResponseStructure<TicketsEntity>();
		TicketsEntity newTicket=tDao.updateTicket(ticket,ticketId);
		if(newTicket != null) 
		{
		structure.setMessage("Ticket update success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(newTicket);
		return new ResponseEntity<ResponseStructure<TicketsEntity>>(structure,HttpStatus.OK);
		}
		throw new TicketNotFound("ticket not updated because,ticket not found for the given id");
	}
	
	//To Check Available Seats
	public List<SeatsEntity> bookSeat(List<SeatsEntity> availableSeats, List<Integer> seatIds, int movieId)
	{
		List<SeatsEntity> seats=new ArrayList<SeatsEntity>();
		for (SeatsEntity seatAvailable : availableSeats) 
		{
			for (Integer integer : seatIds) 
			{
				if(seatAvailable.getSeatId()==integer) 
				{
					seats.add(seatAvailable);
					MovieEntity movie=mDao.findMovie(movieId);
					movie.setTotalNoOfSeats(movie.getTotalNoOfSeats()-1);
					mDao.updateMovie(movie, movieId);
					seatAvailable.setSeatAvailability(false);
					sDao.updateSeat(seatAvailable, seatAvailable.getSeatId());
				}
			}
		}
		return seats;
	}
	
	//To Book a Ticket
	public ResponseEntity<ResponseStructure<TicketsEntity>> ticketBooking(String userEmail,String userPassword,int movieId,SeatTypes seatType,List<Integer> seatIds,LocalDate bookingDate,String paymentMethod)
	{
		UserEntity user=userLogin(userEmail, userPassword);
		if(user != null) {
		TicketsEntity ticket=new TicketsEntity();
		List<SeatsEntity> availableSeat=mService.findSeatsAvailability(movieId, seatType);
		if(availableSeat != null) {
		List<SeatsEntity> bookedSeats=bookSeat(availableSeat, seatIds,movieId);
		if(!bookedSeats.isEmpty()) {
		PaymentEntity payment= processPayement(bookedSeats, bookingDate,paymentMethod);
		ticket.setBookingDate(bookingDate);
		MovieEntity movie=mDao.findMovie(movieId);
		ticket.setMovieId(movieId);
		ticket.setMovieLanguage(movie.getMovieLanguage());
		ticket.setMovieName(movie.getMovieName());
		ticket.setMovieStartTime(movie.getMovieStartTime());
		ticket.setMovieEndTime(movie.getMovieEndTime());
		ticket.setTicketPayment(payment);
		ticket.setTicketSeats(bookedSeats);
		ticket.setTotalTicketPrice(payment.getTicketPrice());
		TicketsEntity newTicket=tDao.saveTickets(ticket);
		user.setTicket(newTicket);
		uDao.updateUser(user, user.getUserId());
		ResponseStructure<TicketsEntity> structure=new ResponseStructure<TicketsEntity>();
		structure.setMessage("ticket booked successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(newTicket);
		return new ResponseEntity<ResponseStructure<TicketsEntity>>(structure,HttpStatus.CREATED);
		}
		throw new  SeatNotFound("user seats are not available please enter available seat Ids");
		}
		throw new  SeatNotFound("user seats are not available please enter available seat Ids");
		}
		throw new UserNotFound("user login required please provide correct credentials");
	}
	
	//To Cancel Ticket
	public ResponseEntity<ResponseStructure<PaymentEntity>> cancelBooking(int ticketId,String userEmail,String userPassword)
		{
		UserEntity user=userLogin(userEmail, userPassword);
		if(user != null) 
		{
		TicketsEntity ticket=tDao.findTickets(ticketId);
		if(ticket != null) 
		{
			List<SeatsEntity> lists = ticket.getTicketSeats();
			for (SeatsEntity seat : lists) {
			seat.setSeatAvailability(true);
			sDao.updateSeat(seat, seat.getSeatId());
			MovieEntity movie=mDao.findMovie(ticket.getMovieId());
			movie.setTotalNoOfSeats(movie.getTotalNoOfSeats()+1);
			mDao.updateMovie(movie, ticket.getMovieId());
		}
			ticket.setTicketSeats(null);
			user.setTicket(null);
			uDao.updateUser(user, user.getUserId());
			PaymentEntity payment=ticket.getTicketPayment();
			tDao.deleteTickets(ticketId);
			ResponseStructure<PaymentEntity> structure=new ResponseStructure<PaymentEntity>();
			structure.setMessage("cancel booking success.amount refunded");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(payment);
			return new ResponseEntity<ResponseStructure<PaymentEntity>> (structure,HttpStatus.OK);
		}
		else {
			throw new TicketNotFound("ticket not found for given id");
		}
		}
		throw new UserNotFound("loginrequired please provide correct details");
	}
	
	//For Login verification
	private UserEntity userLogin(String userEmail, String userPassword) 
	{
		UserEntity user = uDao.findByUserEmail(userEmail);
		if(user != null) 
		{
		  if(user.getUserPassword().equals(userPassword)) 
		  {
			  return user;
		  }
		  return null;
		}
		return null;
	}
	
	//To Make Payment
	private PaymentEntity processPayement(List<SeatsEntity> bookedSeats,LocalDate bookingDate,String paymentMethod) 
	{
		PaymentEntity payment=new PaymentEntity();
		long amount=0;
		for (SeatsEntity seat : bookedSeats) 
		{
			if(seat.getType() == SeatTypes.FirstClass) 
			{
				amount+=250;
			}
			else if(seat.getType()==SeatTypes.SecondClass) 
			{
				amount+=150;
			}
			else if(seat.getType() == SeatTypes.General)
			{
				amount+=110;
			}
		}
		payment.setPaymentDate(bookingDate);
		payment.setPaymentMethod(paymentMethod);
		payment.setTicketPrice(amount);
		PaymentEntity newPayment=pDao.savePayment(payment);
		return newPayment;
	}
	
	//To Find All Tickets
	public ResponseEntity<ResponseStructure<List<TicketsEntity>>> findAllTicket() 
	{
		ResponseStructure<List<TicketsEntity>> structure=new ResponseStructure<List<TicketsEntity>>();
		structure.setMessage("find all Ticket success");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(tDao.findAllTicket());
		return new ResponseEntity<ResponseStructure<List<TicketsEntity>>>(structure,HttpStatus.FOUND);
	}
}