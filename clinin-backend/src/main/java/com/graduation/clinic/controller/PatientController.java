package com.graduation.clinic.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.clinic.dto.PatientDto;
import com.graduation.clinic.dto.ReservationRequest;
import com.graduation.clinic.dto.ReviewDto;
import com.graduation.clinic.dto.WriteReviewRequest;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.entity.ReservationStatus;
import com.graduation.clinic.entity.Review;
import com.graduation.clinic.service.PatientService;
import com.graduation.clinic.service.ReservationService;
import com.graduation.clinic.service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/smart/patient")
public class PatientController {


	private final PatientService patientService;
	private final ReservationService reservationService;
	private final ReviewService reviewService;

	public PatientController(PatientService patientService,ReservationService reservationService,ReviewService reviewService) {
		this.patientService = patientService;
		this.reservationService=reservationService;
		this.reviewService=reviewService;
	}
	@PostMapping("/insert")
	public PatientDto insertPatient(@RequestBody Patient patient){
		return patientService.insertPatient(patient);
	}
	@PostMapping("/write-review")
	public ReviewDto writeReview(@RequestBody @Valid WriteReviewRequest request) {
		return reviewService.writeReview(request);
	}

	@PostMapping("/make-reservation")
	public ReservationStatus makeReservation(@RequestBody @Valid ReservationRequest request) {
		return reservationService.makeReservation(request);
	}
}
