package com.springproject.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.bookmyshow.entity.MovieEntity;

public interface MovieRepo extends JpaRepository<MovieEntity, Integer>{

}
