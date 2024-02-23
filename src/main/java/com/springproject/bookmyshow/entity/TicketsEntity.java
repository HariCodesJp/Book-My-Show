package com.springproject.bookmyshow.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Entity
public class TicketsEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketId;
	private int movieId;
	private String movieName;
	private LocalTime movieStartTime;
	private LocalTime movieEndTime;
	private String movieLanguage;
	private LocalDate bookingDate;
	private double totalTicketPrice;
	@ManyToMany(cascade = CascadeType.ALL)
	private  List<SeatsEntity> ticketSeats;
	@OneToOne(cascade = CascadeType.ALL)
	private PaymentEntity ticketPayment;
	
}
