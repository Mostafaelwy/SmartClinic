package com.graduation.clinic.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ReceptionistRegisterRequest {

	@NotBlank
	private String firstName;
	@NotBlank
	private String secondName;
	@Email
	@NotBlank
	private String email;
	@NotBlank
	private String Password;
	
	public ReceptionistRegisterRequest(@NotBlank String firstName, @NotBlank String secondName,
			@Email @NotBlank String email, @NotBlank String password) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.email = email;
		Password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return Password;
	}
	
	
	
	
}
