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

import com.graduation.clinic.dto.DoctorData;
import com.graduation.clinic.dto.DoctorFilteration;
import com.graduation.clinic.dto.PageProperties;
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
import com.graduation.clinic.service.DoctorService;
import com.graduation.clinic.service.PatientService;
import com.graduation.clinic.service.RatingService;
import com.graduation.clinic.service.ReservationService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/smart/patient")
public class PatientController {


	private final PatientService patientService;
	private final ReservationService reservationService;
	private final RatingService ratingService;
	private final DoctorService doctorService;


	public PatientController(
			PatientService patientService,
			ReservationService reservationService,
			DoctorService doctorService,
			RatingService ratingService
			) {
		
		this.patientService = patientService;
		this.reservationService = reservationService;
		this.doctorService=doctorService;
		this.ratingService = ratingService;
	}
	@PostMapping("/insert")
	public PatientDto insertPatient(@RequestBody Patient patient){
		return patientService.insertPatient(patient);
	}

	
	@PostMapping("/me/reservation/clinic/{clinicId}")// fix this ############
	public ReservationDto makeReservation(@RequestBody @Valid ReservationRequest request,@PathVariable Long clinicId) {
		return reservationService.makeReservation(request,clinicId);
	}
	@GetMapping("/me/review/doctor/{doctorId}")
	public ReviewDto getMethodName(@PathVariable Long doctorId) {
		return ratingService.readMyRview(doctorId);
	}
	@PostMapping("/me/rating/doctor/{doctorId}")
	public ReviewDto rate(@RequestBody RatingDto request,@PathVariable Long doctorId) {
		 return ratingService.rate(request,doctorId);
	}
	@GetMapping("/{id}")
	public Patient findPatient(@PathVariable Long id) {
		return patientService.findById(id);
	}
	@GetMapping("/doctors")
	public Page<DoctorData> doctorSearch(@ModelAttribute DoctorFilteration filter,@ModelAttribute PageProperties p ) {
		return doctorService.doctorSearch(filter, p);
	}
	
	
}
