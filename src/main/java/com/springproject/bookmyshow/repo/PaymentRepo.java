package com.springproject.bookmyshow.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.bookmyshow.entity.PaymentEntity;

public interface PaymentRepo extends JpaRepository<PaymentEntity, Integer>
{

}
