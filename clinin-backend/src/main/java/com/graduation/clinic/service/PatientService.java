package com.graduation.clinic.service;


import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.GetPatientProfileData;
import com.graduation.clinic.dto.PatientData;
import com.graduation.clinic.dto.PatientDto;
import com.graduation.clinic.dto.SetPatientProfileData;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.entity.Photo;
import com.graduation.clinic.entity.UsersBaseEntity;
import com.graduation.clinic.exceptions.DuplicateException;
import com.graduation.clinic.exceptions.NotFoundException;
import com.graduation.clinic.repos.BaseUserRepo;
import com.graduation.clinic.repos.PatientRepo;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class PatientService  {

	private final PatientRepo patientRepo;
	private final BaseUserRepo baseUserRepo;
	
	
	
	public PatientService(PatientRepo patientRepo ,BaseUserRepo baseUserRepo ) {
		
		this.patientRepo = patientRepo;
		this.baseUserRepo=baseUserRepo;
		
		
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
	
	public GetPatientProfileData getPatientData(Long id) {
		Patient p=patientRepo.findById(id).orElseThrow(()-> new NotFoundException("patient not found"));
		return new GetPatientProfileData(p);
	}
	@Transactional(value = TxType.REQUIRES_NEW)
	public GetPatientProfileData setPatientData(SetPatientProfileData request) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Patient p=(Patient)auth.getPrincipal();
		Optional<UsersBaseEntity> user=baseUserRepo.findByUserName(request.getEmailAddress());
		if(user.isPresent()) {
			throw new DuplicateException("you are not allowed to use this user name, it is used before");
		}
		else {
			p.setFirstName(request.getFirstName());
			p.setSecondName(request.getLastName());
			p.setDateOfBirth(request.getDateOfBirth());
			p.setPhoneNumbers(request.getPhoneNumbers());
			p.setUserName(request.getEmailAddress());
			p.setAddress(request.getAddress());
			p.setProfilePhoto(new Photo(request.getPhoto()));
			p.setBlood(request.getGroub());
		}
		return getPatientData(p.getId());
	}
	
	public PatientData getPatientData() {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Patient p=(Patient)auth.getPrincipal();
		return new PatientData(p);
	}


	
}
