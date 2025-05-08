package com.graduation.clinic.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.clinic.dto.PatientDto;
import com.graduation.clinic.dto.RatingDto;
import com.graduation.clinic.dto.ReservationDto;
import com.graduation.clinic.dto.ReservationRequest;
import com.graduation.clinic.dto.ReviewDto;
import com.graduation.clinic.dto.TimeInterval;
import com.graduation.clinic.dto.WriteReviewRequest;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.entity.Rating;
import com.graduation.clinic.entity.ReservationStatus;
import com.graduation.clinic.entity.Review;
import com.graduation.clinic.service.PatientService;
import com.graduation.clinic.service.RatingService;
import com.graduation.clinic.service.ReservationService;
import com.graduation.clinic.service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/smart/patient")
public class PatientController {


	private final PatientService patientService;
	private final ReservationService reservationService;
	private final ReviewService reviewService;
	private final RatingService ratingService;


	public PatientController(
			PatientService patientService,
			ReservationService reservationService,
			ReviewService reviewService,
			RatingService ratingService
			) {
		
		this.patientService = patientService;
		this.reservationService = reservationService;
		this.reviewService = reviewService;
		this.ratingService = ratingService;
	}
	@PostMapping("/insert")
	public PatientDto insertPatient(@RequestBody Patient patient){
		return patientService.insertPatient(patient);
	}
	@PostMapping("/me/review")
	public ReviewDto writeReview(@RequestBody @Valid WriteReviewRequest request) {
		return reviewService.writeReview(request);
	}
	@GetMapping("/doctor/{doctorId}/reviews")
	public Page<ReviewDto> readAllReviews(
			@PathVariable Long doctorId,
			@RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum,
			@ModelAttribute TimeInterval interval){
		return reviewService.readReviews(doctorId,pageNum,interval);
	}
	
	@PostMapping("/me/reservation/clinic/{clinicId}")// fix this ############
	public ReservationDto makeReservation(@RequestBody @Valid ReservationRequest request,@PathVariable Long clinicId) {
		return reservationService.makeReservation(request,clinicId);
	}
	@PostMapping("/me/rating/doctor/{doctorId}")
	public void rate(@RequestBody RatingDto request,@PathVariable Long doctorId) {
		 ratingService.rate(request,doctorId);
	}
	@GetMapping("/{id}")
	public Patient findPatient(@PathVariable Long id) {
		return patientService.findById(id);
	}
	
}
