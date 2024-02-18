package com.springproject.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springproject.bookmyshow.entity.AdminEntity;

public interface AdminRepo extends JpaRepository<AdminEntity, Integer> 
{
	@Query("select a from AdminEntity a where a.adminEmail=?1")
	public AdminEntity findAdminEmail(String adminEmail);
}
