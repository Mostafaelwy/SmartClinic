package com.graduation.clinic.dto;

import java.util.List;

import com.graduation.clinic.entity.Gender;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class AuthenticationRequest {
	


	private String userName;

	private String password;

	public AuthenticationRequest( String userName, String password) {
		this.userName = userName;
		this.password = password;
	}


	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
	
	
	
	
}
