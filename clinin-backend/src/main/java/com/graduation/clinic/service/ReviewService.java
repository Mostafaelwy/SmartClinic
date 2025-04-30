package com.graduation.clinic.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.ReviewDto;
import com.graduation.clinic.dto.WriteReviewRequest;
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
	
	private ReviewDto convertReviewToDto(Review review) {
		return new ReviewDto(review);
	}
	private Page<ReviewDto> paginateReviewDto(Page <Review> reviews){
		Page<ReviewDto> dtos= reviews.map(this::convertReviewToDto);
		return dtos;
	}

	public ReviewDto writeReview(WriteReviewRequest request) {
		Doctor doctor=doctorService.findById(request.getDoctorId());
		Patient patient = patientService.findById(request.getReviewerId());
		List<Integer> DoctorRates=reviewRepo.findRates(request.getDoctorId());
		
		Double totalRate=0.0;
		
		for(int i=0;i<DoctorRates.size();i++) {
			totalRate+=DoctorRates.get(i);
		}
		totalRate/=DoctorRates.size();
		doctor.setTotalRating(totalRate);
		Review review= new Review();
		review.setMessage(request.getMessage());
		review.setRate(request.getRate());
		review.setReviewer(patient);
		review.setReviewedDoctor(doctor);
		return new ReviewDto(reviewRepo.save(review));
	}
	public Page<ReviewDto> readReview(Long id,int pageNum){
		
		Pageable page=PageRequest.of(pageNum, 2);
		Page<Review> review= reviewRepo.findByReviewedDoctorId(id,page);
		return paginateReviewDto(review);
	
	}
	
}
