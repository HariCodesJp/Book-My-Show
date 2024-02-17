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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@NotNull(message = "Booking Date Cannot be null")
	@NotBlank(message = "Booking Date Cannot be Blank")
	private LocalDate bookingDate;
	@NotNull(message = "Total Ticket Price Cannot be null")
	@NotBlank(message = "Total Ticket Price Cannot be Blank")
	private double totalTicketPrice;
	@ManyToMany(cascade = CascadeType.ALL)
	private  List<SeatsEntity> ticketSeats;
	@OneToOne(cascade = CascadeType.ALL)
	private PaymentEntity ticketPayment;
	
}
