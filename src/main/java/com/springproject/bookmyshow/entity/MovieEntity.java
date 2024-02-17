package com.springproject.bookmyshow.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Getter
@Setter
public class MovieEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieId;
	@NotNull(message = "Movie Name Cannot be null")
	@NotBlank(message = "Movie Name Cannot be Blank")
	private String movieName;
	@NotNull(message = "Movie Language Cannot be null")
	@NotBlank(message = "Movie Language Cannot be Blank")
	private String movieLanguage;
	@NotNull(message = "Movie Genre Cannot be null")
	@NotBlank(message = "Movie Genre Cannot be Blank")
	private String movieGenre;
	@NotNull(message = "Movie Release Date Cannot be null")
	@NotBlank(message = "Movie Release Date Cannot be Blank")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate movieReleaseDate;
	@DateTimeFormat(pattern = "HH:mm:ss")
	@NotNull(message = "Movie Start Time Cannot be null")
	@NotBlank(message = "Movie Start Time Cannot be Blank")
	private LocalTime movieStartTime;
	@DateTimeFormat(pattern = "HH:mm:ss")
	@NotNull(message = "Movie End Time Cannot be null")
	@NotBlank(message = "Movie End Time Cannot be Blank")
	private LocalTime movieEndTime;
	@Positive
	private int totalNoOfSeats;
	@OneToMany(cascade = CascadeType.ALL )
	List<SeatsEntity> seatEntity;
	
}
