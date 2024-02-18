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

import com.springproject.bookmyshow.entity.MovieEntity;
import com.springproject.bookmyshow.entity.SeatTypes;
import com.springproject.bookmyshow.entity.SeatsEntity;
import com.springproject.bookmyshow.service.MovieService;
import com.springproject.bookmyshow.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("movie")
public class MovieController 
{
	@Autowired
	MovieService movieService;
	
	@PostMapping("savemovie")
	public ResponseEntity<ResponseStructure<MovieEntity>> saveMovie(@Valid @RequestBody MovieEntity movie,BindingResult result)
	{
		return movieService.saveMovie(movie);
	}

	@GetMapping("findmovie")
	public ResponseEntity<ResponseStructure<MovieEntity>> findMovie(@RequestParam int movieId)
	{
		return movieService.findMovie(movieId);
	}
	
	@PutMapping("assignseatstomovies")
	public  ResponseEntity<ResponseStructure<MovieEntity>> assignSeatsToMovies(@RequestParam int movieId,@RequestBody List<Integer> seatIds) 
	{
		return movieService.assignSeatsToMovies(movieId, seatIds);
	}

	@DeleteMapping("deletemovie")
	public ResponseEntity<ResponseStructure<MovieEntity>> deleteMovie(@RequestParam int movieId){
		return movieService.deleteMovie(movieId);
	}
	
	@PutMapping("updatemovie")
	public ResponseEntity<ResponseStructure<MovieEntity>> updateMovie(@Valid @RequestBody MovieEntity movie,@RequestParam int movieId,BindingResult result)
	{
		return movieService.updateMovie(movie, movieId);
	}
	
	@GetMapping("findallmovie")
	public ResponseEntity<ResponseStructure<List<MovieEntity>>> findAllMovie(){
		return movieService.findAllMovie();
	}
	
	@GetMapping("findseatavailability")
	public ResponseEntity<ResponseStructure<List<SeatsEntity>>> findSeatAvailability(@RequestParam int movieId,@RequestParam SeatTypes seatType) {
		return movieService.findSeatAvailability(movieId, seatType);
	}
}
