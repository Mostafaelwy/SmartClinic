package com.graduation.clinic.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.graduation.clinic.Specifications.ReviewSpecification;
import com.graduation.clinic.dto.ReviewDto;
import com.graduation.clinic.dto.TimeInterval;
import com.graduation.clinic.dto.WriteReviewRequest;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.entity.Review;
import com.graduation.clinic.repos.ReviewRepo;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

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
	
	
	@Transactional(value = TxType.REQUIRES_NEW)
	public ReviewDto writeReview(WriteReviewRequest request) {
		Doctor doctor=doctorService.findById(request.getDoctorId());
		
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Patient patient=(Patient)auth.getPrincipal();
		
		patient =patientService.findById(patient.getId());
		Review review=new Review();
		review.setMessage(request.getMessage());
		review.setReviewedDoctor(doctor);
		review.setReviewer(patient);
		review.setCreationDate(LocalDate.now());
		return convertReviewToDto(reviewRepo.save(review));
		
	}
	public Page<ReviewDto> readReviews(Long doctorId,int pageNum,TimeInterval interval ){
		
		Pageable page=PageRequest.of(pageNum, 2);
		
		Page<Review> reviews= reviewRepo.findAll(
				Specification.where(ReviewSpecification.hasDoctorId(doctorId))
							.and(ReviewSpecification.hasTimeInterval(interval.getStartDate(), interval.getEndDate())),
				page);	
		return paginateReviewDto(reviews);
	
	}
	
	
}
