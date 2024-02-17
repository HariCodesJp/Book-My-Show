package com.springproject.bookmyshow.entity;

import java.time.LocalDate;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class PaymentEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	@NotNull(message = "Payment Date Cannot be null")
	@NotBlank(message = "Payment Date Cannot be Blank")
	private LocalDate paymentDate;
	@NotNull(message = "Ticket Price Cannot be null")
	@NotBlank(message = "Ticket Price Cannot be Blank")
	private double ticketPrice;
	@NotNull(message = "Payment Method Cannot be null")
	@NotBlank(message = "Payment Method Cannot be Blank")
	private String paymentMethod;
	
}
