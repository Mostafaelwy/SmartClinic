package com.graduation.clinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.PatientDto;
import com.graduation.clinic.dto.ReviewDto;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.entity.Review;
import com.graduation.clinic.exceptions.DuplicateException;
import com.graduation.clinic.exceptions.NotFoundException;
import com.graduation.clinic.repos.PatientRepo;

@Service
public class PatientService  {

	private final PatientRepo patientRepo;
	
	private final ReviewService reviewService;
	
	public PatientService(PatientRepo patientRepo ,@Lazy ReviewService reviewService) {
		
		this.patientRepo = patientRepo;
		this.reviewService=reviewService;
		
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


	
}
