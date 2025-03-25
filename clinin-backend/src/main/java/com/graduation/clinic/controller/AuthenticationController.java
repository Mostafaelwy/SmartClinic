package com.graduation.clinic.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.clinic.dto.AuthenticationRequest;
import com.graduation.clinic.dto.AuthenticationResponse;
import com.graduation.clinic.dto.RegisterRequest;
import com.graduation.clinic.service.AuthenticationService;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

	
	private final AuthenticationService authenticationService;
	
	
	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}


	@PostMapping("/register-patient")
	public AuthenticationResponse registerAsPatient(@RequestBody RegisterRequest request) {
		return authenticationService.registerAsPatient(request);
	}
	@PostMapping("/signin") 
	public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) {
		return authenticationService.authenticate(request);
	}
	
}
