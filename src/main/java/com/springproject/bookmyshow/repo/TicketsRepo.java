package com.springproject.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.bookmyshow.entity.TicketsEntity;

public interface TicketsRepo extends JpaRepository<TicketsEntity, Integer> 
{

}
