package com.springproject.bookmyshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.bookmyshow.entity.PaymentEntity;
import com.springproject.bookmyshow.service.PaymentService;
import com.springproject.bookmyshow.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class PaymentController 
{
	@Autowired
	PaymentService pService;
	
	@PostMapping("savepayment")
	public ResponseEntity<ResponseStructure<PaymentEntity>> savePayment(@Valid @RequestBody PaymentEntity payment,BindingResult result)
	{
		return pService.savePayment(payment); 
	}
	
	@GetMapping("findpayment")
	public ResponseEntity<ResponseStructure<PaymentEntity>> findPayment(@RequestParam int paymentId){
		return pService.findPayment(paymentId);
	}
	
	@DeleteMapping("deletepayment")
	public ResponseEntity<ResponseStructure<PaymentEntity>> deletePayment(@RequestParam int paymentId){
		return pService.deletePayment(paymentId);
	}
	
	@PutMapping("updatepayment")
	public ResponseEntity<ResponseStructure<PaymentEntity>> updatePayment(@Valid @RequestBody PaymentEntity payment,@RequestParam int paymentId,BindingResult result)
	{
		return pService.updatePayment(payment, paymentId);
	}
}
