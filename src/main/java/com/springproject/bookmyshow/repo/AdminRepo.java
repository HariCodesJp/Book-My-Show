package com.springproject.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.bookmyshow.entity.AdminEntity;

public interface AdminRepo extends JpaRepository<AdminEntity, Integer> {

}
