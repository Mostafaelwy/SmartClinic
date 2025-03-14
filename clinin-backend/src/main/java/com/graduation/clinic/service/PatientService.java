package com.graduation.clinic.service;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.PatientDto;
import com.graduation.clinic.dto.ReviewDto;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.entity.Review;
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
		return new PatientDto(patientRepo.save(patient));
	}
	
	public Patient findPatient(String Email) {
		return  patientRepo.findByEmail(Email).orElseThrow() ;
	}
	
	public ReviewDto writeReview(Long DoctorId,String reviewerEmail,String Message) {
		return reviewService.writeReview(DoctorId, reviewerEmail, Message);
	}
	public List<ReviewDto> readReview(Long id) {
		return reviewService.readReview(id);
	}

	
}
