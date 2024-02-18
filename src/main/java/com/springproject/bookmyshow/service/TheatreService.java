package com.springproject.bookmyshow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springproject.bookmyshow.dao.MovieDao;
import com.springproject.bookmyshow.dao.TheatreDao;
import com.springproject.bookmyshow.entity.MovieEntity;
import com.springproject.bookmyshow.entity.TheatreEntity;
import com.springproject.bookmyshow.exception.TheatreNotFound;
import com.springproject.bookmyshow.repo.MovieRepo;
import com.springproject.bookmyshow.repo.TheatreRepo;
import com.springproject.bookmyshow.util.ResponseStructure;

@Service
public class TheatreService 
{
	@Autowired
	TheatreDao tDao;
	@Autowired
	TheatreRepo tRepo;
	@Autowired
	MovieRepo mRepo;
	@Autowired
	MovieDao mDao;
	
	//To Save Theatre Details
	public ResponseEntity<ResponseStructure<TheatreEntity>> saveTheatre(TheatreEntity theatre) 
	{
		ResponseStructure<TheatreEntity> structure=new ResponseStructure<TheatreEntity>();
		structure.setMessage("Theatre Details saved Sucessfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(tDao.saveTheatre(theatre));
		return new ResponseEntity<ResponseStructure<TheatreEntity>>(structure,HttpStatus.CREATED);
	}

	//To Find Theatre Details
	public ResponseEntity<ResponseStructure<TheatreEntity>> findTheatre(int theatreId) 
	{
		TheatreEntity theatre=tDao.findTheatre(theatreId);
		if(theatre != null) 
		{
		ResponseStructure<TheatreEntity> structure=new ResponseStructure<TheatreEntity>();
		structure.setMessage("Theatre details found Sucessfully");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(theatre);
		return new ResponseEntity<ResponseStructure<TheatreEntity>>(structure,HttpStatus.FOUND);
	}
		throw new TheatreNotFound("Theatre details are not found for the given Id");
	}
	
	//To Delete Theatre Details
	public ResponseEntity<ResponseStructure<TheatreEntity>> deleteTheatre(int theatreId) 
	{
		TheatreEntity theatre=tDao.findTheatre(theatreId);
		if(theatre != null) 
		{
		ResponseStructure<TheatreEntity> structure=new ResponseStructure<TheatreEntity>();
		structure.setMessage("Theatre details deleted Sucessfully");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(tDao.deleteTheatre(theatreId));
		return new ResponseEntity<ResponseStructure<TheatreEntity>>(structure,HttpStatus.OK);
	}
		throw new TheatreNotFound("Theatre details not deleted because,Theatre not found for the given id");
	}
	
	//To Update Theatre Details
	public ResponseEntity<ResponseStructure<TheatreEntity>> updateTheatre(TheatreEntity theatre,int theatreId) 
	{
		TheatreEntity exTheatre = tDao.findTheatre(theatreId);
		if(exTheatre != null) 
		{
		List<MovieEntity> movie = mRepo.findAll();
		exTheatre.setMovieList(movie);
		ResponseStructure<TheatreEntity> structure=new ResponseStructure<TheatreEntity>();
		TheatreEntity theatreOne= tDao.updateTheatre(exTheatre, theatreId);
		structure.setMessage("Theatre details updated Sucessfully");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(theatreOne);
		return new ResponseEntity<ResponseStructure<TheatreEntity>>(structure,HttpStatus.OK);
	}
		throw new TheatreNotFound("Theatre not updated because,Theatre not found for the given id");
	}
	
	//To Assign Movies to Theatre
	public ResponseEntity<ResponseStructure<TheatreEntity>> assignMoviesToTheatre(int theatreId,List<Integer> movieIds) 
		{
		TheatreEntity theatre=tDao.findTheatre(theatreId);
		if(theatre != null) 
		{
		List<MovieEntity> exmovies=mRepo.findAllById(movieIds);
		theatre.setMovieList(exmovies);
		ResponseStructure<TheatreEntity> structure=new ResponseStructure<TheatreEntity>();
		structure.setMessage("Movies are asigned to Theatre");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(tDao.updateTheatre(theatre, theatreId));
		return new ResponseEntity<ResponseStructure<TheatreEntity>>(structure,HttpStatus.OK);
		}
		throw new TheatreNotFound("we can't assign movie to the theatre because, theatre not found for the given id");
	}
	
	public ResponseEntity<ResponseStructure<List<TheatreEntity>>> findAllTheatre() 
	{
		List<TheatreEntity> theatre = tDao.findAllTheatre();
		if(theatre != null)
		{
		ResponseStructure<List<TheatreEntity>> structure=new ResponseStructure<List<TheatreEntity>>();
		structure.setMessage("find all theatre success");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(theatre);
		return new ResponseEntity<ResponseStructure<List<TheatreEntity>>>(structure,HttpStatus.FOUND);
	}
		throw new TheatreNotFound("Theatre Details does not exist");
	}
}
