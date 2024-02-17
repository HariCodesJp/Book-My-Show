package com.springproject.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springproject.bookmyshow.entity.MovieEntity;
import com.springproject.bookmyshow.repo.MovieRepo;

@Repository
public class MovieDao 
{
	@Autowired
	MovieRepo movieRepo;
	
	public MovieEntity saveMovie(MovieEntity movie)
	{
		return movieRepo.save(movie);
	}
	
	public MovieEntity findMovie(int movieId)
	{
		Optional<MovieEntity> movie = movieRepo.findById(movieId);
		if(movie.isPresent())
		{
			return movie.get();
		}
		return null;
	}
	
	public MovieEntity updateMovie(MovieEntity movie,int movieId)
	{
		MovieEntity movieOne = findMovie(movieId);
		if(movieOne != null)
		{
			movieOne.setMovieId(movieId);
			return movieOne;
		}
		return null;
	}
	
	public MovieEntity deleteMovie(int movieId)
	{
		MovieEntity movie = findMovie(movieId);
		if(movie != null)
		{
			movieRepo.delete(movie);
			return movie;
		}
		return null;
	}
}
