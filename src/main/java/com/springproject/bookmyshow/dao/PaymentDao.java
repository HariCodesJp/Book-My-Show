package com.springproject.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.springproject.bookmyshow.entity.PaymentEntity;
import com.springproject.bookmyshow.repo.PaymentRepo;

public class PaymentDao 
{
	@Autowired
	PaymentRepo paymentRepo;
	
	public PaymentEntity savePayment(PaymentEntity payment) {
		return paymentRepo.save(payment);
	}
	public PaymentEntity findPayment(int paymentId) {
		Optional<PaymentEntity> opPayment=paymentRepo.findById(paymentId);
		if(opPayment.isPresent()) {
			return opPayment.get();
		}
		return null;
	}
	public PaymentEntity deletePayment(int paymentId) {
		PaymentEntity payment=findPayment(paymentId);
		paymentRepo.delete(payment);
		return payment;
	}
	public PaymentEntity updatePayment(PaymentEntity payment,int paymentId) {
		PaymentEntity newPayment=findPayment(paymentId);
		if(newPayment != null) {
			payment.setPaymentId(paymentId);
			return paymentRepo.save(payment);
		}
		return null;
	}
	

}
