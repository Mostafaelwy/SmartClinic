package com.graduation.clinic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.DoctorDto;
import com.graduation.clinic.dto.ReviewDto;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.entity.Review;
import com.graduation.clinic.repos.ReviewRepo;

@Service
public class ReviewService {

	private final ReviewRepo reviewRepo;
	private final DoctorService doctorService;
	private final PatientService patientService;
	
	public ReviewService(ReviewRepo reviewRepo,DoctorService doctorService ,PatientService patientService) {
		this.reviewRepo = reviewRepo;
		this.doctorService=doctorService;
		this.patientService=patientService;
	}
	
	public ReviewDto writeReview(Long DoctorId,String reviewerEmail,String Message) {
		Doctor doctor=doctorService.findById(DoctorId);
		Patient patient = patientService.findPatient(reviewerEmail);
		
		Review review= new Review();
		review.setMessage(Message);
		review.setReviewer(patient);
		review.setReviewedDoctor(doctor);
		return new ReviewDto(reviewRepo.save(review));
	}
	public List<ReviewDto> readReview(Long id){
		List<Review> review= reviewRepo.findByReviewedDoctorId(id);
		List<ReviewDto> reviewDto=new ArrayList<>();
		for(int i=0;i<review.size();i++) {
			reviewDto.add(new ReviewDto(review.get(i)));
		}
		return reviewDto;
	}
	
}
