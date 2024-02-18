package com.springproject.bookmyshow.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Component
@Getter
@Setter
public class AdminEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	@NotNull(message = "Admin Name Cannot be null")
	@NotBlank(message = "Admin Name Cannot be Blank")
	private String adminName;
	@Email(message = "Admin Email Cannot Be Null")
	private String adminEmail;
	@Positive
	@Min(value = 6000000000l)
	@Max(value = 9999999999l)
	private long adminContact;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,20}$",message = "password must be min 4 and max 20 length containing atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	private String adminPassword;
	@OneToMany(cascade = CascadeType.ALL)
	List<TheatreEntity> theatre;
	
}
