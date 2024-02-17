package com.springproject.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.bookmyshow.entity.TheatreEntity;

public interface TheatreRepo extends JpaRepository<TheatreEntity, Integer>{

}
