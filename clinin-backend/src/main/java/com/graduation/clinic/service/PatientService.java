package com.graduation.clinic.service;


import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.BasicDetailes;
import com.graduation.clinic.dto.ChangePasswordRequest;
import com.graduation.clinic.dto.DoctorBasicDetailesRequest;
import com.graduation.clinic.dto.GetPatientProfileData;
import com.graduation.clinic.dto.PatientData;
import com.graduation.clinic.dto.PatientDto;
import com.graduation.clinic.dto.SetPatientProfileData;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Memberships;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.entity.Photo;
import com.graduation.clinic.entity.UsersBaseEntity;
import com.graduation.clinic.exceptions.DuplicateException;
import com.graduation.clinic.exceptions.GenericException;
import com.graduation.clinic.exceptions.NotFoundException;
import com.graduation.clinic.repos.BaseUserRepo;
import com.graduation.clinic.repos.PatientRepo;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class PatientService  {

	private final PatientRepo patientRepo;
	private final BaseUserRepo baseUserRepo;
	private final PasswordEncoder passwordEncoder;
	
	
	
	public PatientService(PatientRepo patientRepo ,BaseUserRepo baseUserRepo ,PasswordEncoder passwordEncoder ) {
		
		this.patientRepo = patientRepo;
		this.baseUserRepo=baseUserRepo;
		this.passwordEncoder=passwordEncoder;
		
		
	}
	public PatientDto insertPatient(Patient patient) {
		
		Optional<Patient> p= patientRepo.findByUserName(patient.getUsername());
		if(!p.isPresent()) {
			return new PatientDto(patientRepo.save(patient));
		}
		throw new DuplicateException("this email is already used.");
	
	}
	
	public Patient findPatient(String Email) {
		
		return patientRepo.findByUserName(Email).orElseThrow(()-> new NotFoundException("patient not found"));
	}	
	
	public Patient findById(Long id) {
		return patientRepo.findById(id).orElseThrow(()-> new NotFoundException("patient not found Exception"));
	}
	
	public GetPatientProfileData getPatientBasicData() {
		 Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		 Patient p=(Patient)auth.getPrincipal();
		 Patient patient= patientRepo.findById(p.getId()).orElseThrow();
		return new GetPatientProfileData(patient);
	}

	 @Transactional(value = TxType.REQUIRES_NEW)
	 public GetPatientProfileData setPatientData(SetPatientProfileData request) {
		 Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		 Patient p=(Patient)auth.getPrincipal();
		 
		 if(!p.getUsername().equals(request.getEmailAddress())) {
			 Optional <UsersBaseEntity> user =baseUserRepo.findByUserName(request.getEmailAddress());
			 if(user.isPresent()) {
				 throw new DuplicateException("you are not allowed to use this email 'it is already used'");
			 }
			 else {
				 p.setUserName(request.getEmailAddress()); 
			 }
		 }


			p.setFirstName(request.getFirstName());
			p.setSecondName(request.getLastName());
			p.setDateOfBirth(request.getDateOfBirth());
			p.setPhoneNumbers(request.getPhoneNumbers());
			p.setUserName(request.getEmailAddress());
			p.setAddress(request.getAddress());
			p.setProfilePhoto(new Photo(request.getPhoto()));
			p.setBlood(request.getGroub());
		 
		 patientRepo.save(p);
		 return getPatientBasicData();
	 }
	
	public PatientData getPatientData() {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Patient p=(Patient)auth.getPrincipal();
		return new PatientData(p);
	}
	 @Transactional(value = TxType.REQUIRES_NEW)
	 
	 public void ChangePassword(ChangePasswordRequest request) {
		 Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		 Patient patient=(Patient)auth.getPrincipal();
		
		 if(this.passwordEncoder.matches(request.getOldPassword(), patient.getPassword())){
			 patient.setPassword(passwordEncoder.encode(request.getNewPassword()));
			 patientRepo.save(patient);
		 }else {
			 throw new GenericException("old password is not correct");
		 }
		 
	 }
		 


	
}
