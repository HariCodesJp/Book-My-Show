package com.springproject.bookmyshow.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springproject.bookmyshow.dao.MovieDao;
import com.springproject.bookmyshow.dao.SeatsDao;
import com.springproject.bookmyshow.entity.MovieEntity;
import com.springproject.bookmyshow.entity.SeatTypes;
import com.springproject.bookmyshow.entity.SeatsEntity;
import com.springproject.bookmyshow.exception.MovieNotFound;
import com.springproject.bookmyshow.exception.SeatNotFound;
import com.springproject.bookmyshow.repo.MovieRepo;
import com.springproject.bookmyshow.repo.SeatsRepo;
import com.springproject.bookmyshow.util.ResponseStructure;

@Service
public class MovieService 
{
	@Autowired
	MovieDao mDao;
	@Autowired
	SeatsDao sDao;
	@Autowired
	SeatsRepo sRepo;
	@Autowired
	MovieRepo mRepo;
	
	//To Save Movie Deatils
	public ResponseEntity<ResponseStructure<MovieEntity>> saveMovie(MovieEntity movie) 
	{
		ResponseStructure<MovieEntity> structure=new ResponseStructure<MovieEntity>();
		structure.setMessage("Movie details saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(mDao.saveMovie(movie));
		return new ResponseEntity<ResponseStructure<MovieEntity>>(structure,HttpStatus.CREATED);
	}
	
	//To Find Movie Details
	public ResponseEntity<ResponseStructure<MovieEntity>> findMovie(int movieId) 
	{
		MovieEntity movie=mDao.findMovie(movieId);
		if(movie != null) 
		{
			ResponseStructure<MovieEntity> structure=new ResponseStructure<MovieEntity>();
			structure.setMessage("Movie found success");
			structure.setStatus(HttpStatus .FOUND.value());
			structure.setData(movie);
			return new ResponseEntity<ResponseStructure<MovieEntity>>(structure,HttpStatus.FOUND);
		}
		throw new MovieNotFound("Movie Does not present for the given Id");
	}
	
	//To delete Movie Details
	public ResponseEntity<ResponseStructure<MovieEntity>> deleteMovie(int movieId) 
	{
		MovieEntity movie=mDao.findMovie(movieId);
		if(movie != null) 
		{
			ResponseStructure<MovieEntity> structure=new ResponseStructure<MovieEntity>();
			structure.setMessage("Movie delete success");
			structure.setStatus(HttpStatus .OK.value());
			structure.setData(mDao.deleteMovie(movieId));
			return new ResponseEntity<ResponseStructure<MovieEntity>>(structure,HttpStatus.OK);
		}
		throw new MovieNotFound("movie not deleted because,movie not found for the given id");
	}
	
	//To Update Movie Details
	public ResponseEntity<ResponseStructure<MovieEntity>> updateMovie(MovieEntity movie,int movieId) 
	{
		MovieEntity movieOne = mDao.findMovie(movieId);
		List<SeatsEntity> movies = sRepo.findAll();
		if(movieOne != null) 
		{
			ResponseStructure<MovieEntity> structure=new ResponseStructure<MovieEntity>();
			movieOne.setSeatEntity(movies);
			MovieEntity exMovie= mDao.updateMovie(movieOne,movieId);
			structure.setMessage("Movie update success");
			structure.setStatus(HttpStatus .OK.value());
			structure.setData(exMovie);
			return new ResponseEntity<ResponseStructure<MovieEntity>>(structure,HttpStatus.OK);
		}
		throw new MovieNotFound("Movie details does not updated because,Movie not found for the given Id");
	}
	
	//To Find All Movie Details
	public ResponseEntity<ResponseStructure<List<MovieEntity>>> findAllMovie() 
	{
		
		List<MovieEntity> movies = mRepo.findAll();
		if(movies != null)
		{
			ResponseStructure<List<MovieEntity>> structure=new ResponseStructure<List<MovieEntity>>();
			structure.setMessage("find all Movie success");
			structure.setStatus(HttpStatus .FOUND.value());
			structure.setData(movies);
			return new ResponseEntity<ResponseStructure<List<MovieEntity>>>(structure,HttpStatus.FOUND);
		}
		throw new MovieNotFound("Movie Deatils does not present");
	}
	
	//To find Seat Availability
	public List<SeatsEntity> findSeatsAvailability(int movieId,SeatTypes seatType) 
	{
		MovieEntity movie=mDao.findMovie(movieId);
		if(movie != null)
		{
		List<SeatsEntity> seatList=movie.getSeatEntity();
		List<SeatsEntity> seatsList=new ArrayList<SeatsEntity>();
		for (SeatsEntity seat : seatList) 
		{
			if(seat.isSeatAvailability()==true && seat.getType()==seatType) 
			{
				seatsList.add(seat);
			}
		}
		return seatsList;
		}
		throw new MovieNotFound("Seat Doesn't Available for this movie");
	}
	
	//Find Seat Availabilty
	public  ResponseEntity<ResponseStructure<List<SeatsEntity>>> findSeatAvailability(int movieId,SeatTypes seatType) 
	{
		MovieEntity  movie=mDao.findMovie(movieId);
		if(movie != null)
		{
		List<SeatsEntity> seatList=movie.getSeatEntity();
		List<SeatsEntity> seatsList=new ArrayList<SeatsEntity>();
		for (SeatsEntity seat : seatList) 
		{
			if(seat.isSeatAvailability()==true && seat.getType()==seatType) 
			{
				seatsList.add(seat);
			}
		}
		
		ResponseStructure<List<SeatsEntity>> structure=new ResponseStructure<List<SeatsEntity>>();
		structure.setMessage("find seat availability found success");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(seatsList);
		return new ResponseEntity<ResponseStructure<List<SeatsEntity>>>(structure,HttpStatus.FOUND);
	}
		throw new MovieNotFound("Seat Doesn't Available for this movie");
	}
	
	//To Assign theatre to movies
	public  ResponseEntity<ResponseStructure<MovieEntity>> assignSeatsToMovies(int movieId,List<Integer> seatIds) 
	{
		MovieEntity movie=mDao.findMovie(movieId);
		List<SeatsEntity> seats=sRepo.findAllById(seatIds);
		if(movie != null) 
		{
			if(seats != null) 
			{
				movie.setSeatEntity(seats);
				MovieEntity movies = mDao.updateMovie(movie, movieId);
				ResponseStructure<MovieEntity> structure=new ResponseStructure<MovieEntity>();
				structure.setMessage("Seats Are Assigned To Movie");
				structure.setStatus(HttpStatus .OK.value());
				structure.setData(movies);
				return new ResponseEntity<ResponseStructure<MovieEntity>>(structure,HttpStatus.OK);
			}
			
			throw new SeatNotFound("we can't assign seats to movies because,seats not found for set of Seat Id");
		}
		
		 throw new MovieNotFound("we can't assign seats to movies because,movie not found for the given id");
	}
	
}
