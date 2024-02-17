package com.springproject.bookmyshow.entity;

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
public class SeatsEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seatId;
	@NotNull(message = "Seat Number Cannot be null")
	@NotBlank(message = "Seat Number Cannot be Blank")
	private String seatNumber;
	@NotNull(message = "Seat Availability Cannot be null")
	@NotBlank(message = "Seat Availability Cannot be Blank")
	private boolean seatAvailability;
	private SeatTypes type;
}
