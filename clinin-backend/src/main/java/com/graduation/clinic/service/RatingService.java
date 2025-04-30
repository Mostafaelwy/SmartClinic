package com.graduation.clinic.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.RatingDto;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.entity.Rating;
import com.graduation.clinic.exceptions.DuplicateException;
import com.graduation.clinic.repos.RatingRepo;

@Service
public class RatingService {

	private final RatingRepo ratingRepo;
	private final PatientService patientService;
	private final DoctorService doctorService;


	
	public RatingService(RatingRepo ratingRepo, PatientService patientService, DoctorService doctorService) {
		this.ratingRepo = ratingRepo;
		this.patientService = patientService;
		this.doctorService = doctorService;
	}



	public Rating rate(RatingDto request) {
		Optional< Rating> isPatientRateBefore=ratingRepo.findByRatedDoctorIdAndRaterId(request.getDoctorId(), request.getRaterId());
		if(isPatientRateBefore.isPresent()) {
		 throw new DuplicateException("you have rate before 'you are not allowed to rate more then once'.");
		}else {
			Doctor doctor =doctorService.findById(request.getDoctorId());
			Patient patient=patientService.findById(request.getRaterId());
			
			Rating rate=new Rating();
			rate.setRatedDoctor(doctor);
			rate.setRater(patient);
			rate.setRate(request.getRate());
			return ratingRepo.save(rate);
		}	
	}
}
