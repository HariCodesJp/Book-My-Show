package com.springproject.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.bookmyshow.entity.SeatsEntity;

public interface SeatsRepo extends JpaRepository<SeatsEntity, Integer> 
{

}
