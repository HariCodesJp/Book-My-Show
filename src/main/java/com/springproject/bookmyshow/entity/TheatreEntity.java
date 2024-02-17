package com.springproject.bookmyshow.entity;

import java.util.List;

import org.springframework.stereotype.Component;

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

@Entity
@Component
@Getter
@Setter
public class TheatreEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int theatreId;
	@NotNull(message = "Theatre Name Cannot be null")
	@NotBlank(message = "Theatre Name Cannot be Blank")
	private String theaterName;
	@NotNull(message = "Theatre Location Cannot be null")
	@NotBlank(message = "Theatre Location Cannot be Blank")
	private String theatreLocation;
	@Positive
	private int totalNoOfSeats;
	@OneToMany
	List<MovieEntity> movieList;
}
