package com.graduation.clinic.controller;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.graduation.clinic.dto.DoctorStatistics;
import com.graduation.clinic.dto.FilterReservations;
import com.graduation.clinic.dto.ReservationDto;
import com.graduation.clinic.dto.ReviewDto;
import com.graduation.clinic.dto.SlotDto;
import com.graduation.clinic.dto.SpecialityServiceDto;
import com.graduation.clinic.dto.SpecialtiesAndServicesDto;
import com.graduation.clinic.dto.StartAppointment;
import com.graduation.clinic.dto.UpdateClinicDetailesRequest;
import com.graduation.clinic.dto.AddSpecialityRequest;
import com.graduation.clinic.dto.AddSpecialityServiceRequest;
import com.graduation.clinic.dto.AddVisitorResponse;
import com.graduation.clinic.dto.AppointmentDetailesRequest;
import com.graduation.clinic.dto.ChangePasswordRequest;
import com.graduation.clinic.dto.ClinicData;
import com.graduation.clinic.dto.ClinicDto;
import com.graduation.clinic.dto.CreateClinicRequest;
import com.graduation.clinic.entity.Days;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Reservation;
import com.graduation.clinic.entity.ReservationStatus;
import com.graduation.clinic.entity.Slot;
import com.graduation.clinic.entity.SpecialtiesAndServices;
import com.graduation.clinic.repos.ReviewRepo;
import com.graduation.clinic.service.AppointmentDetailesService;
import com.graduation.clinic.service.ClinicService;
import com.graduation.clinic.service.DoctorService;
import com.graduation.clinic.service.DoctorSpecialtiesService;
import com.graduation.clinic.service.ReservationService;
import com.graduation.clinic.service.ReviewService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/smart/doctor")
public class DoctorController {

	private final DoctorService doctorService;
	private final ClinicService clinicService;
	private final ReservationService reservationService;
	private final ReviewService reviewService;
	private final ReviewRepo reviewRepo;
	private final DoctorSpecialtiesService doctorSpecialtiesService;
	private final AppointmentDetailesService appointmentDetailesService;

	public DoctorController(
			DoctorService doctorService,
			ClinicService clinicService,
			ReservationService reservationService,
			ReviewService reviewService,
			ReviewRepo reviewRepo ,
			DoctorSpecialtiesService doctorSpecialtiesService,
			AppointmentDetailesService appointmentDetailesService
			) {
		
		
		this.doctorService = doctorService;
		this.clinicService=clinicService;
		this.reservationService=reservationService;
		this.reviewService=reviewService;
		this.reviewRepo=reviewRepo;
		this.doctorSpecialtiesService=doctorSpecialtiesService;
		this.appointmentDetailesService=appointmentDetailesService;
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
	
	@PostMapping("/{doctorId}/clinic")// shoud be only for the doctor who is auth(me)
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
	@PatchMapping("/reservation/{reservationId}")
	public ReservationDto alterReservationStatus(@PathVariable Long reservationId,@RequestParam(defaultValue = "") ReservationStatus status) {
		return reservationService.alterReservationStatus(reservationId,status);
	}
	@GetMapping("/{doctorId}/clinics")
	public List<ClinicData> getDoctorClinics(@PathVariable Long doctorId){
		return clinicService.findDoctorClinics(doctorId);
	}

	@PatchMapping("/visitor/{patientId}/clinic/{cLinicId}")
	public AddVisitorResponse addVisitor(@PathVariable Long patientId,@PathVariable Long cLinicId) {
		return clinicService.addVisitor(patientId,cLinicId);
	}
	@GetMapping("/{doctorId}/reviews")
	public Page<ReviewDto> readAllReviews(@PathVariable Long doctorId, @RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum){
		return reviewService.readReview(doctorId,pageNum);
	}
	@GetMapping("/{doctorId}/rates")
	public List<Integer>  findRates(@PathVariable Long doctorId) {
		return reviewRepo.findRates(doctorId);
	}
	
	@GetMapping("/me/statistics")
	public DoctorStatistics calculateStatistics() {
		return doctorService.calculateStatistics();
	}

	@PostMapping("/me/reservations")
	public Page<ReservationDto> reservationSearch(@ModelAttribute FilterReservations filter,
			@RequestParam(name ="pageNum" , required = false , defaultValue = "0") int pageNum,
			@RequestParam(name ="pageSize" , required = false , defaultValue = "5") int pageSize){
		 return reservationService.reservationSearch(filter, pageNum,pageSize);
	}

	@PostMapping("/me/speciality")
	public SpecialtiesAndServicesDto addSpeciality(@RequestBody AddSpecialityRequest request) {
		return doctorSpecialtiesService.addSpeciality(request);
	}
	@GetMapping("/{doctorId}/specialties")
	public  List <SpecialtiesAndServicesDto> findSpecialties (@PathVariable Long doctorId){
		return doctorSpecialtiesService.findSpecialties(doctorId);
	}
	@PostMapping("/me/speciality/{specialityId}/service")
	public SpecialityServiceDto addSpecialityService(@RequestBody AddSpecialityServiceRequest request,@PathVariable Long specialityId) {
		return doctorSpecialtiesService.addServicetoSpeciality(specialityId, request);
	}
	@DeleteMapping("me/speciality/{specialityId}")
	public void deleteSpeciality(@PathVariable long specialityId) {
		doctorSpecialtiesService.deleteSpeciality(specialityId);
	}
	@DeleteMapping("me/service/{serviceId}")
	public void deleteSpecialityService(@PathVariable Long serviceId) {
		doctorSpecialtiesService.deleteSpecialityService(serviceId);
	}
	@PatchMapping("/me/password")
	public void changePassword(@RequestBody ChangePasswordRequest request) {
		doctorService.ChangePassword(request);
	}
	@GetMapping("/me/appointment/reservation/{reservationId}")
	public StartAppointment startAppointment(@PathVariable Long reservationId) {
		 return appointmentDetailesService.startAppointment(reservationId);
	}
	
	@PostMapping("/me/appointment/reservation/{reservationID}")
	public ReservationStatus endAppointment(@RequestBody AppointmentDetailesRequest request,@PathVariable Long reservationID) {
		
		return appointmentDetailesService.endAppointment(reservationID, request);
	}
	@PatchMapping("/me/workingdays/{workingDay}/clinic/{clinicId}")
	public Map<Days, List<LocalTime>> addWorkingDay(@PathVariable Long clinicId,@PathVariable Days workingDay,@RequestBody SlotDto slot){
		 return clinicService.addWorkingDay(clinicId, workingDay, slot);
	}
	@GetMapping("/avilable-timings/clinic/{clinicId}")
	public Map<Days, List<LocalTime>> showSlotsPerDay(@PathVariable Long clinicId,@RequestParam(name = "workingDay",required = false,defaultValue = "") Days workingDay) {
		return clinicService.showSlotsPerDay(clinicId,workingDay);
	}
			
}
