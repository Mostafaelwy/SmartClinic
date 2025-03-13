package com.graduation.clinic.service;

import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.PatientDto;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.repos.PatientRepo;

@Service
public class PatientService  {

	private final PatientRepo patientRepo;
	
	public PatientService(PatientRepo patientRepo) {
		
		this.patientRepo = patientRepo;
		
	}
	public PatientDto insertPatient(Patient patient) {
		return new PatientDto(patientRepo.save(patient));
	}
	
	public Patient findPatient(String Email) {
		return  patientRepo.findByEmail(Email).orElseThrow() ;
	}

	
}
