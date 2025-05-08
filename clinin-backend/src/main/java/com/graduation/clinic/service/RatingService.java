package com.graduation.clinic.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.RatingDto;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.entity.Rating;
import com.graduation.clinic.exceptions.DuplicateException;
import com.graduation.clinic.repos.DoctorRepo;
import com.graduation.clinic.repos.RatingRepo;

@Service
public class RatingService {

	private final RatingRepo ratingRepo;
	private final PatientService patientService;
	private final DoctorService doctorService;
	private final DoctorRepo doctorRepo;


	
	public RatingService(RatingRepo ratingRepo, PatientService patientService, DoctorService doctorService,DoctorRepo doctorRepo) {
		this.ratingRepo = ratingRepo;
		this.patientService = patientService;
		this.doctorService = doctorService;
		this.doctorRepo=doctorRepo;
	}



	public void rate(RatingDto request,Long doctorId) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Patient patient=(Patient)auth.getPrincipal();
		
		Optional<Rating> isPatientRateBefore=ratingRepo.findByRatedDoctorIdAndRaterId(doctorId, patient.getId());
		Rating rate=new Rating();
		if(isPatientRateBefore.isPresent()) {
		 rate=ratingRepo.findByRatedDoctorIdAndRaterId(doctorId,patient.getId() ).orElseThrow();
		}
			Doctor doctor =doctorService.findById(doctorId);
			patient=patientService.findById(patient.getId());
			
			rate.setRatedDoctor(doctor);
			rate.setRater(patient);
			rate.setRate(request.getRate());
			rate.setCreationDate(LocalDate.now());
			ratingRepo.save(rate);
			int total=0;
			int value=0;
			for(int i=0;i<doctor.getRatings().size();i++) {
				value=doctor.getRatings().get(i).getRate();
				total+=value;
			}
			double totalRating=total/doctor.getRatings().size();
			doctor.setTotalRating(totalRating);
			doctorRepo.save(doctor);
			
			
		}	
}

