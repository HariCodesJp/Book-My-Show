package com.springproject.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springproject.bookmyshow.entity.TheatreAdminEntity;

public interface TheatreAdminRepo extends JpaRepository<TheatreAdminEntity, Integer>
{
	@Query("select a from TheatreAdminEntity a where a.theatreAdminEmail=?1")
	public TheatreAdminEntity findTheatreAdminByEmail(String theatreAdminEmail);
}
