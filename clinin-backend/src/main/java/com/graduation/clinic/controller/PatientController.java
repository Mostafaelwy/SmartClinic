package com.graduation.clinic.controller;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

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
import com.graduation.clinic.dto.FilterReservations;
import com.graduation.clinic.dto.GetAppointmentsForOnePatient;
import com.graduation.clinic.dto.GetAwards;
import com.graduation.clinic.dto.GetClinic;
import com.graduation.clinic.dto.GetEducation;
import com.graduation.clinic.dto.GetExperienceDto;
import com.graduation.clinic.dto.GetPatientProfileData;
import com.graduation.clinic.dto.PageProperties;
import com.graduation.clinic.dto.PatientData;
import com.graduation.clinic.dto.PatientDto;
import com.graduation.clinic.dto.RatingDto;
import com.graduation.clinic.dto.ReservationDto;
import com.graduation.clinic.dto.ReservationRequest;
import com.graduation.clinic.dto.ReviewDto;
import com.graduation.clinic.dto.SetDate;
import com.graduation.clinic.dto.SetPatientProfileData;
import com.graduation.clinic.dto.SpecialityServiceDto;
import com.graduation.clinic.dto.SpecialtiesAndServicesDto;
import com.graduation.clinic.dto.ChangePasswordRequest;
import com.graduation.clinic.dto.DateInterval;
import com.graduation.clinic.dto.WriteReviewRequest;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.entity.Rating;
import com.graduation.clinic.entity.ReservationStatus;
import com.graduation.clinic.service.ClinicService;
import com.graduation.clinic.service.DoctorService;
import com.graduation.clinic.service.DoctorSpecialtiesService;
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
	private final DoctorSpecialtiesService doctorSpecialtiesService;
	private final ClinicService clinicService;


	public PatientController(
			ClinicService clinicService,
			PatientService patientService,
			ReservationService reservationService,
			DoctorService doctorService,
			RatingService ratingService,
			DoctorSpecialtiesService doctorSpecialtiesService
			) {
		this.clinicService=clinicService;
		this.patientService = patientService;
		this.reservationService = reservationService;
		this.doctorService=doctorService;
		this.ratingService = ratingService;
		this.doctorSpecialtiesService=doctorSpecialtiesService;
	}
	
	/*
	@PostMapping("/insert")
	public PatientDto insertPatient(@RequestBody Patient patient){
		return patientService.insertPatient(patient);
	}*/

	@GetMapping("/me/profile-data")
	public PatientData getprofileData() {
		return patientService.getPatientData();
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
	@GetMapping("/{patientId}/profile-data")
	public GetPatientProfileData getData(@PathVariable Long patientId ) {
		return patientService.getPatientData(patientId);
	}
	@PostMapping("/me/profile-data")
	public GetPatientProfileData setData(@RequestBody SetPatientProfileData request) {
		return patientService.setPatientData(request);
	}
	@GetMapping("/me/appointments")
	public Page<GetAppointmentsForOnePatient> getAppoinments(@ModelAttribute FilterReservations filter,@ModelAttribute PageProperties p) {
		return reservationService.getPatientAppointments(filter, p);
	}
	@GetMapping("/doctor/{id}/specialties")
	public List<SpecialtiesAndServicesDto> getSpecialtiesAndServices(@PathVariable Long id){
		return doctorSpecialtiesService.findSpecialties(id);
	}
	@GetMapping("/doctor/{id}/clinics")
	public List<GetClinic> getDoctorClinics(@PathVariable Long id){
		return clinicService.getClinics(id);
	}
	@GetMapping("/clinic/{id}/time-avilabilty")
	public Map<LocalTime,Boolean> getTimeAvilabilty(@RequestBody SetDate date,@PathVariable Long id){
		return clinicService.getAvilableTimes(date.getDate(), id);
	}
	@PostMapping("/me/change-password")
	public void changePassword(@RequestBody ChangePasswordRequest request) {
		patientService.ChangePassword(request);
	}
	@GetMapping("/doctor/{doctorId}/experience")
	public List<GetExperienceDto> getExperience(@PathVariable Long doctorId){
		return doctorService.getExperience(doctorId);
	}
	@GetMapping("/doctor/me/experience")
	public List<GetExperienceDto> getExperience(){
		return doctorService.getExperience();
	}
	@GetMapping("/doctor/{DoctorId}/education")
	public List<GetEducation> getEducation(@PathVariable Long DoctorId){
		return doctorService.getEducation(DoctorId);
	}
	@GetMapping("/doctor/{doctorId}/awards")
	public List<GetAwards> getAwards(@PathVariable Long doctorId){
		return doctorService.getAwards(doctorId);
	}
	@GetMapping("/doctor/{doctorId}/reviews")
	public Page<ReviewDto> readAllReviews(
			@PathVariable Long doctorId,
			@ModelAttribute PageProperties p,
			@ModelAttribute DateInterval interval
			){
		return ratingService.readDoctorReviews(doctorId,p,interval);
	}
	
	
}
