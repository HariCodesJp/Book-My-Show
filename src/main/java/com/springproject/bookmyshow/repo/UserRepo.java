package com.springproject.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springproject.bookmyshow.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> 
{
	@Query("select u from UserEntity u where u.userEmail=?1")
	public UserEntity findByEmail(String userEmail);
}
