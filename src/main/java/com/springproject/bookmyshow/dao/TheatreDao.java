package com.springproject.bookmyshow.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springproject.bookmyshow.entity.TheatreEntity;
import com.springproject.bookmyshow.repo.TheatreRepo;

@Repository
public class TheatreDao 
{
	@Autowired
	TheatreRepo theatreRepo;
	
	public TheatreEntity saveTheatre(TheatreEntity theatre)
	{
	    return	theatreRepo.save(theatre);
	}
	
	public TheatreEntity findTheatre(int theatreId)
	{
		Optional<TheatreEntity> theatre = theatreRepo.findById(theatreId);
		if(theatre.isPresent())
		{
			return theatre.get();
		}
		
		return null;
		
	}
	
	public TheatreEntity updateTheatre(TheatreEntity theatre,int theatreId)
	{
		TheatreEntity theatreOne  = findTheatre(theatreId);
		
		if(theatreOne!= null)
		{
			theatreOne.setTheatreId(theatreId);
			return theatreRepo.save(theatre);
			
		}
		return null;
	}
	
	public TheatreEntity deleteTheatre( int theatreId )
	{
		TheatreEntity theatre = findTheatre(theatreId);
		if(theatre != null)
		{
			theatreRepo.delete(theatre);
			
			return theatre;
		}
		return null;
	}
	
	public List<TheatreEntity> findAllTheatre() {
		return theatreRepo.findAll();
	}
	
}
