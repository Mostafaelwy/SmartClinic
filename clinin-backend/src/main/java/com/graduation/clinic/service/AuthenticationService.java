package com.graduation.clinic.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.AuthenticationRequest;
import com.graduation.clinic.dto.AuthenticationResponse;
import com.graduation.clinic.dto.DoctorRegisterRequest;
import com.graduation.clinic.dto.PatientRegisterRequest;
import com.graduation.clinic.dto.ReceptionistRegisterRequest;

import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.entity.Receptionist;
import com.graduation.clinic.entity.Role;
import com.graduation.clinic.entity.UsersBaseEntity;
import com.graduation.clinic.exceptions.DuplicateException;

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
	//private final UserDetailsService userDetailsService;
	
	
	public AuthenticationService(DoctorRepo doctorRepo ,JwtService jwtService, PatientRepo patientRepo,ReceptionistRepo receptionistRepo,PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager
			,BaseUserRepo baseUserRepo) {
		this.doctorRepo = doctorRepo;
		this.jwtService=jwtService;
		this.patientRepo=patientRepo;
		this.receptionistRepo=receptionistRepo;
		this.passwordEncoder=passwordEncoder;
		this.authenticationManager=authenticationManager;
		this.baseUserRepo=baseUserRepo;
		//this.userDetailsService=userDetailsService;
		
	}
	public AuthenticationResponse registerAsDoctor(DoctorRegisterRequest request) {
		Optional <UsersBaseEntity> user= baseUserRepo.findByUserName(request.getEmail());
		if(!user.isPresent()) {
			Doctor doctor=new Doctor();
			doctor.setFirstName(request.getFirstName());
			doctor.setSecondName(request.getSecondName());
			doctor.setUserName(request.getEmail());
			doctor.setPassword(passwordEncoder.encode(request.getPassword()));
			doctor.setRoles(Role.DOCTOR);
			doctorRepo.save(doctor);
			Map<String, Object> newCalims = new HashMap();
			newCalims.put("roles", doctor.getAuthorities());
			var JwtToken =jwtService.GenerateToken(newCalims ,doctor);
			AuthenticationResponse authresponse =new AuthenticationResponse(JwtToken);
			return authresponse;
		}
		else {	
			throw new DuplicateException("email is already used ,use unused email");
		}
		
	}
		
	
	public AuthenticationResponse registerAsPatient(PatientRegisterRequest request) {
		Optional <UsersBaseEntity> user= baseUserRepo.findByUserName(request.getEmail());
		if(!user.isPresent()) {
			Patient patient=new Patient();
			patient.setFirstName(request.getFirstName());
			patient.setSecondName(request.getSecondName());
			patient.setUserName(request.getEmail());
			patient.setPassword(passwordEncoder.encode(request.getPassword()));
			patient.setRoles(Role.PATIENT);
			patientRepo.save(patient);
			Map<String, Object> newCalims = new HashMap();
			newCalims.put("roles", patient.getAuthorities());
			var JwtToken =jwtService.GenerateToken(newCalims, patient);
			AuthenticationResponse authresponse =new AuthenticationResponse(JwtToken);
			return authresponse;
		}
		else {
			throw new DuplicateException("email is already used ,use unused email");
		}

		
	}
	public AuthenticationResponse registerAsReceptionist(ReceptionistRegisterRequest request) {
		Optional <UsersBaseEntity> user= baseUserRepo.findByUserName(request.getEmail());
		if(!user.isPresent()) {
			Receptionist recep=new Receptionist();
			recep.setFirstName(request.getFirstName());
			recep.setSecondName(request.getSecondName());
			recep.setUserName(request.getEmail());
			recep.setPassword(passwordEncoder.encode(request.getPassword()));
			recep.setRoles(Role.RECEPTIONIST);
			receptionistRepo.save(recep);
			Map<String, Object> newCalims = new HashMap();
			newCalims.put("roles", recep.getAuthorities());
			var JwtToken =jwtService.GenerateToken(newCalims, recep);
			AuthenticationResponse authresponse =new AuthenticationResponse(JwtToken);
			return authresponse;
		}
		else {
			throw new DuplicateException("email is already used ,use unused email");
		}

	}
			
	
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
		UsersBaseEntity user =baseUserRepo.findByUserName(request.getEmail()).orElseThrow();
		Map<String, Object> newCalims = new HashMap();
		newCalims.put("roles", user.getAuthorities());
		var jwtToken =jwtService.GenerateToken(newCalims, user);
		AuthenticationResponse authresponse=new AuthenticationResponse(jwtToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		return authresponse;
	}
}
