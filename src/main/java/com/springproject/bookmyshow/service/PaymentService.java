package com.springproject.bookmyshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springproject.bookmyshow.dao.PaymentDao;
import com.springproject.bookmyshow.entity.PaymentEntity;
import com.springproject.bookmyshow.exception.PaymentNotFound;
import com.springproject.bookmyshow.util.ResponseStructure;

@Service
public class PaymentService 
{
	
	PaymentDao pDao;
	
	//To Save Payment Details
	public ResponseEntity<ResponseStructure<PaymentEntity>> savePayment(PaymentEntity payment) 
	{
		ResponseStructure<PaymentEntity> structure=new ResponseStructure<PaymentEntity>();
		structure.setMessage("Payment details has been saved");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(pDao.savePayment(payment));
		return new ResponseEntity<ResponseStructure<PaymentEntity>>(structure,HttpStatus.CREATED);
	}
	
	//To Find Payment Details
	public ResponseEntity<ResponseStructure<PaymentEntity>> findPayment(int paymentId) 
	{
		PaymentEntity payment=pDao.findPayment(paymentId);
		if(payment != null) 
		{
		ResponseStructure<PaymentEntity> structure=new ResponseStructure<PaymentEntity>();
		structure.setMessage("Payment Details");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(payment);
		return new ResponseEntity<ResponseStructure<PaymentEntity>>(structure,HttpStatus.FOUND);
	}
		throw new PaymentNotFound("This Id Doesn't have a valid Payment");

	}
	public ResponseEntity<ResponseStructure<PaymentEntity>> deletePayment(int paymentId) 
	{
		PaymentEntity payment=pDao.findPayment(paymentId);
		if(payment != null) 
		{
		ResponseStructure<PaymentEntity> structure=new ResponseStructure<PaymentEntity>();
		structure.setMessage("Payment Delete success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(pDao.deletePayment(paymentId));
		return new ResponseEntity<ResponseStructure<PaymentEntity>>(structure,HttpStatus.OK);
	}
		throw new PaymentNotFound("payment details are not deleted because,Payment details are not found for this given id");
	}
	public ResponseEntity<ResponseStructure<PaymentEntity>> updatePayment(PaymentEntity payment,int paymentId) 
	{
		PaymentEntity exPayment=pDao.updatePayment(payment, paymentId);
		if(exPayment != null) 
		{
		ResponseStructure<PaymentEntity> structure=new ResponseStructure<PaymentEntity>();
		structure.setMessage("Payment update success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(exPayment);
		return new ResponseEntity<ResponseStructure<PaymentEntity>>(structure,HttpStatus.OK);
	}
		throw new PaymentNotFound("Payment details not updated because,Payment not found for the given id");
	}
}
