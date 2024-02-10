package com.springproject.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.bookmyshow.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> 
{

}
