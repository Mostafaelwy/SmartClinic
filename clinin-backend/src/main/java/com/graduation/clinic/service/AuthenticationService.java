package com.graduation.clinic.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.AuthenticationRequest;
import com.graduation.clinic.dto.AuthenticationResponse;
import com.graduation.clinic.dto.RegisterRequest;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.entity.Receptionist;
import com.graduation.clinic.entity.Role;
import com.graduation.clinic.entity.UsersBaseEntity;
import com.graduation.clinic.repos.BaseUserRepo;
import com.graduation.clinic.repos.DoctorRepo;
import com.graduation.clinic.repos.PatientRepo;
import com.graduation.clinic.repos.ReceptionistRepo;


@Service
public class AuthenticationService {

	private final DoctorRepo doctorRepo;
	private final PatientRepo patientRepo;
	private final ReceptionistRepo receptionistRepo;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final BaseUserRepo baseUserRepo ;
	
	
	
	public AuthenticationService(DoctorRepo doctorRepo ,JwtService jwtService, PatientRepo patientRepo,ReceptionistRepo receptionistRepo,PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager
			,BaseUserRepo baseUserRepo) {
		this.doctorRepo = doctorRepo;
		this.jwtService=jwtService;
		this.patientRepo=patientRepo;
		this.receptionistRepo=receptionistRepo;
		this.passwordEncoder=passwordEncoder;
		this.authenticationManager=authenticationManager;
		this.baseUserRepo=baseUserRepo;
	}
	public AuthenticationResponse registerAsDoctor(RegisterRequest request) {
		Doctor doctor=new Doctor();
		doctor.setFirstName(request.getFirstName());
		doctor.setSecondName(request.getSecondName());
		doctor.setUserName(request.getUserName());
		doctor.setPassword(passwordEncoder.encode(request.getPassword()));
		doctor.setRoles(Role.DOCTOR);
		doctorRepo.save(doctor);
		var JwtToken =jwtService.GenerateToken(doctor);
		AuthenticationResponse authresponse =new AuthenticationResponse(JwtToken);
		return authresponse;
		
	}
	public AuthenticationResponse registerAsPatient(RegisterRequest request) {
		Patient patient=new Patient();
		patient.setFirstName(request.getFirstName());
		patient.setSecondName(request.getSecondName());
		patient.setUserName(request.getUserName());
		patient.setPassword(passwordEncoder.encode(request.getPassword()));
		patient.setRoles(Role.PATIENT);
		patientRepo.save(patient);
		var JwtToken =jwtService.GenerateToken(patient);
		AuthenticationResponse authresponse =new AuthenticationResponse(JwtToken);
		return authresponse;
		
	}
	public AuthenticationResponse registerAsReceptionist(RegisterRequest request) {
		Receptionist recep=new Receptionist();
		recep.setFirstName(request.getFirstName());
		recep.setSecondName(request.getSecondName());
		recep.setUserName(request.getUserName());
		recep.setPassword(passwordEncoder.encode(request.getPassword()));
		recep.setRoles(Role.RECEPTIONIST);
		receptionistRepo.save(recep);
		var JwtToken =jwtService.GenerateToken(recep);
		AuthenticationResponse authresponse =new AuthenticationResponse(JwtToken);
		return authresponse;
	}
			
	
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));
		UsersBaseEntity user =baseUserRepo.findByUserName(request.getUserName()).orElseThrow();
		var jwtToken =jwtService.GenerateToken(user);
		AuthenticationResponse authresponse=new AuthenticationResponse(jwtToken);
		return authresponse;
	}
}
