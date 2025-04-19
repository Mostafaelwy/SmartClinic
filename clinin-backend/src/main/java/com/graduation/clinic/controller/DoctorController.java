package com.graduation.clinic.controller;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.graduation.clinic.dto.CreateClinicResponse;
import com.graduation.clinic.dto.DoctorBasicDetailes;
import com.graduation.clinic.dto.DoctorBasicDetailesRequest;
import com.graduation.clinic.dto.ReservationDto;
import com.graduation.clinic.dto.ReviewDto;
import com.graduation.clinic.dto.UpdateClinicDetailesRequest;
import com.graduation.clinic.dto.ClinicData;
import com.graduation.clinic.dto.ClinicDto;
import com.graduation.clinic.dto.CreateClinicRequest;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Reservation;
import com.graduation.clinic.entity.ReservationStatus;
import com.graduation.clinic.service.ClinicService;
import com.graduation.clinic.service.DoctorService;
import com.graduation.clinic.service.ReservationService;
import com.graduation.clinic.service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/smart/doctor")
public class DoctorController {

	private final DoctorService doctorService;
	private final ClinicService clinicService;
	private final ReservationService reservationService;
	private final ReviewService reviewService;

	public DoctorController(DoctorService doctorService,
			ClinicService clinicService,
			ReservationService reservationService,
			ReviewService reviewService) {
		
		
		this.doctorService = doctorService;
		this.clinicService=clinicService;
		this.reservationService=reservationService;
		this.reviewService=reviewService;
	}
	
	
	/*
	@GetMapping("reservations/{clinicId}")
	public Page<ReservationDto> findAllReservation(@PathVariable Long clinicId,@RequestParam(defaultValue = "") int pageNum){
		return reservationService.findAllReservation(clinicId,pageNum);
	}
	@GetMapping("/clinic/{id}")
	public Clinic findClinic(@PathVariable Long id){
		return clinicService.findById(id);
	}*/
	
	@PostMapping("/{doctorId}/clinic")
	public CreateClinicResponse createClinic(@RequestBody @Valid CreateClinicRequest request,@PathVariable Long doctorId) {
		return clinicService.createClinic(doctorId,request);
	}
	@PatchMapping("/detailes")
	public DoctorBasicDetailes updateDetailes(@RequestBody @Valid DoctorBasicDetailesRequest newData) {
		return doctorService.updateDetailes(newData);
	}
	@GetMapping("/doctor/{username}")
	public Doctor findDoctor(@PathVariable String username){
		return doctorService.getDoctor(username);
	}
	@PatchMapping("/clinic-detailes")
	public ClinicData updateClinicDetailesRequest(@RequestBody @Valid UpdateClinicDetailesRequest detailes) {
		return clinicService.updateClinicDetailesRequest(detailes);
	}
	@PatchMapping("/reservation")
	public ReservationDto alterReservationStatus(@RequestBody @Valid Reservation reservation) {
		return reservationService.alterReservationStatus(reservation);
	}
	@GetMapping("/{doctorId}/clinics")
	public List<ClinicData> getDoctorClinics(@PathVariable Long doctorId){
		return clinicService.findDoctorClinics(doctorId);
	}
	@GetMapping("/clinic/{clinicId}/reservations")
	public Page<ReservationDto> filterByStatus(
			@PathVariable Long clinicId,
			@RequestParam(defaultValue = "") ReservationStatus status,
			@RequestParam(name = "pageNum",required = false, defaultValue = "0") int pageNum){
		
		
		return reservationService.filterReservationByStatus(clinicId, status, pageNum);
	}
	@PatchMapping("/visitor/{patientId}/clinic/{cLinicId}")
	public ClinicDto addVisitor(@PathVariable Long patientId,@PathVariable Long cLinicId) {
		return clinicService.addVisitor(patientId,cLinicId);
	}
	@GetMapping("/{doctorId}/reviews")
	public Page<ReviewDto> readAllReviews(@PathVariable Long doctorId, @RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum){
		return reviewService.readReview(doctorId,pageNum);
	}
		
}
