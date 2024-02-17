package com.springproject.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springproject.bookmyshow.entity.TheatreAdminEntity;

public interface TheatreAdminRepo extends JpaRepository<TheatreAdminEntity, Integer>
{
	
}
