package com.graduation.clinic.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.clinic.dto.AuthenticationRequest;
import com.graduation.clinic.dto.AuthenticationResponse;
import com.graduation.clinic.dto.DoctorRegisterRequest;
import com.graduation.clinic.dto.PatientRegisterRequest;
import com.graduation.clinic.dto.ReceptionistRegisterRequest;

import com.graduation.clinic.service.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	
	private final AuthenticationService authenticationService;
	
	
	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}


	@PostMapping("/patient")
	public AuthenticationResponse registerAsPatient(@RequestBody @Valid PatientRegisterRequest request) {
		return authenticationService.registerAsPatient(request);
	}
	@PostMapping("/Doctor")
	public AuthenticationResponse registerAsDoctor(@RequestBody @Valid DoctorRegisterRequest request) {
		return authenticationService.registerAsDoctor(request);
	}
	@PostMapping("/receptionist")
	public AuthenticationResponse registerAsReceptionist(@RequestBody @Valid ReceptionistRegisterRequest request) {
		return authenticationService.registerAsReceptionist(request);
	}
	
	@PostMapping("/login") 
	public AuthenticationResponse authenticate(@RequestBody @Valid AuthenticationRequest request) {
		return authenticationService.authenticate(request);
	}
	
}
