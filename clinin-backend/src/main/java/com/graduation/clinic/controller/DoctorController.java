package com.graduation.clinic.controller;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;


import org.springframework.data.domain.Page;

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




import com.graduation.clinic.dto.DoctorBasicDetailesRequest;
import com.graduation.clinic.dto.DoctorProfileData;
import com.graduation.clinic.dto.DoctorStatistics;
import com.graduation.clinic.dto.FilterReservations;
import com.graduation.clinic.dto.GetAppointmentsForOnePatient;
import com.graduation.clinic.dto.GetAwards;
import com.graduation.clinic.dto.GetClinic;
import com.graduation.clinic.dto.GetEducation;
import com.graduation.clinic.dto.GetExperienceDto;
import com.graduation.clinic.dto.GetPatient;
import com.graduation.clinic.dto.GetPrescriptions;
import com.graduation.clinic.dto.PageProperties;
import com.graduation.clinic.dto.ReservationDto;
import com.graduation.clinic.dto.ReviewDto;
import com.graduation.clinic.dto.SetAwards;
import com.graduation.clinic.dto.SetClinic;
import com.graduation.clinic.dto.SetEducation;
import com.graduation.clinic.dto.SetExperienceRequest;
import com.graduation.clinic.dto.SetReply;
import com.graduation.clinic.dto.SlotDto;
import com.graduation.clinic.dto.SpecialityServiceDto;
import com.graduation.clinic.dto.SpecialtiesAndServicesDto;
import com.graduation.clinic.dto.StartAppointment;
import com.graduation.clinic.dto.DateInterval;

import com.graduation.clinic.dto.AddSpecialityRequest;
import com.graduation.clinic.dto.AddSpecialityServiceRequest;
import com.graduation.clinic.dto.AddVisitorResponse;
import com.graduation.clinic.dto.AppointmentDetailesRequest;
import com.graduation.clinic.dto.BasicDetailes;
import com.graduation.clinic.dto.ChangePasswordRequest;


import com.graduation.clinic.entity.Days;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.ReservationStatus;


import com.graduation.clinic.service.AppointmentDetailesService;
import com.graduation.clinic.service.ClinicService;
import com.graduation.clinic.service.DoctorService;
import com.graduation.clinic.service.DoctorSpecialtiesService;
import com.graduation.clinic.service.RatingService;
import com.graduation.clinic.service.ReservationService;



import jakarta.validation.Valid;

@RestController
@RequestMapping("/smart/doctor")
public class DoctorController {

	private final DoctorService doctorService;
	private final ClinicService clinicService;
	private final ReservationService reservationService;
	private final RatingService ratingService;
	private final DoctorSpecialtiesService doctorSpecialtiesService;
	private final AppointmentDetailesService appointmentDetailesService;
	

	public DoctorController(
			DoctorService doctorService,
			ClinicService clinicService,
			ReservationService reservationService,

			DoctorSpecialtiesService doctorSpecialtiesService,
			AppointmentDetailesService appointmentDetailesService,
			RatingService ratingService
			) {
		
		
		this.doctorService = doctorService;
		this.clinicService=clinicService;
		this.reservationService=reservationService;
		this.ratingService=ratingService;
		this.doctorSpecialtiesService=doctorSpecialtiesService;
		this.appointmentDetailesService=appointmentDetailesService;
	}
	

	

	

	@GetMapping("/doctor/{username}")
	public Doctor findDoctor(@PathVariable String username){
		return doctorService.getDoctor(username);
	}

	@PatchMapping("/reservation/{reservationId}")
	public ReservationDto alterReservationStatus(@PathVariable Long reservationId,@RequestParam(defaultValue = "") ReservationStatus status) {
		return reservationService.alterReservationStatus(reservationId,status);
	}

	@PatchMapping("/visitor/{patientId}/clinic/{cLinicId}")
	public AddVisitorResponse addVisitor(@PathVariable Long patientId,@PathVariable Long cLinicId) {
		return clinicService.addVisitor(patientId,cLinicId);
	}
	@GetMapping("/{doctorId}/reviews")
	public Page<ReviewDto> readAllReviews(
			@PathVariable Long doctorId,
			@ModelAttribute PageProperties p,
			@ModelAttribute DateInterval interval){
		return ratingService.readDoctorReviews(doctorId,p,interval);
	}

	@GetMapping("/me/reviews")
	public Page<ReviewDto> readAllReviews(
			@ModelAttribute PageProperties p,
			@ModelAttribute DateInterval interval){
		return ratingService.readCurrentDoctorReviews(p,interval);
	}
	@PostMapping("/me/reply")
	public ReviewDto replyOnReview(@RequestBody SetReply reply) {
		return ratingService.doctorReplyOnReview(reply);
	}
	
	@GetMapping("/me/statistics")
	public DoctorStatistics calculateStatistics() {
		return doctorService.calculateStatistics();
	}

	@GetMapping("/me/reservations")
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
	@GetMapping("/me/specialties")
	public  List <SpecialtiesAndServicesDto> findMySpecialties (){
		return doctorSpecialtiesService.findSpecialties();
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
	
	@PostMapping("/me/appointment/{reservationID}")
	public ReservationStatus endAppointment(@RequestBody AppointmentDetailesRequest request,@PathVariable Long reservationID) {
		
		return appointmentDetailesService.endAppointment(reservationID, request);
	}
	@PatchMapping("/me/workingdays/{workingDay}/clinic/{clinicId}")
	public Map<DayOfWeek, List<LocalTime>> addWorkingDay(@PathVariable Long clinicId,@PathVariable DayOfWeek workingDay,@RequestBody SlotDto slot){
		 return clinicService.addWorkingDay(clinicId, workingDay, slot);
	}
	@GetMapping("/available-timings/clinic/{clinicId}")
	public Map<DayOfWeek, List<LocalTime>> showSlotsPerDay(@PathVariable Long clinicId,@RequestParam(name = "workingDay",required = false,defaultValue = "") DayOfWeek workingDay) {
		return clinicService.showSlotsPerDay(clinicId,workingDay);
	}
	@GetMapping("/me/profile-data")
	public DoctorProfileData getProfileData() {
		return doctorService.getProfileData();
	}
	@GetMapping("/{doctorId}/basic-detailes")
	public BasicDetailes getBasicDetailes(@PathVariable Long doctorId) {
		return doctorService.getBasicDetailes(doctorId);
	}
	@PostMapping("/me/basic-detailes")
	public BasicDetailes editBasicDetailes(@RequestBody @Valid DoctorBasicDetailesRequest request) {
		return doctorService.editBasicDetailes(request);
	}
	@DeleteMapping("/me/memberships/{id}")
	public void deletememberships(@PathVariable Long id) {
		doctorService.DeleteMembership(id);
	}
	@GetMapping("/{doctorId}/experience")
	public List<GetExperienceDto> getExperience(@PathVariable Long doctorId){
		return doctorService.getExperience(doctorId);
	}
	@PostMapping("/me/experience")
	public List<GetExperienceDto> setExperience(@RequestBody List<SetExperienceRequest> requests){
		return doctorService.setExperience(requests);
	}
	@DeleteMapping("/me/experience/{id}")
	public void deleteExperience(@PathVariable Long id) {
		doctorService.deleteExperience(id);
	}
	@GetMapping("/{DoctorId}/education")
	public List<GetEducation> getEducation(@PathVariable Long DoctorId){
		return doctorService.getEducation(DoctorId);
	}
	
	@PostMapping("/me/education")
	public List<GetEducation> setEducation(@RequestBody List<SetEducation> requests){
		return doctorService.setEducation(requests);
	}
	@DeleteMapping("/me/education/{id}")
	public void deleteEducation(@PathVariable Long id) {
		doctorService.deleteEducation(id);
	}
	
	@GetMapping("/{doctorId}/award")
	public List<GetAwards> getAwards(@PathVariable Long doctorId){
		return doctorService.getAwards(doctorId);
	}
	
	@PostMapping("/me/award")
	public List<GetAwards> setawards(@RequestBody @Valid List<SetAwards> requests){
		return doctorService.setAwards(requests);
	}
	@DeleteMapping("/me/award/{id}")
	public void deleteAwards(@PathVariable Long id) {
		doctorService.deleteAward(id);
	}
	@GetMapping("/{doctorId}/clinics")
	public List<GetClinic> getClinics(@PathVariable Long doctorId){
		return clinicService.getClinics(doctorId);
	}
	@GetMapping("/me/clinics")
	public List<GetClinic> getClinics(){
		return clinicService.getCurrntDoctorClinics();
	}
	@PostMapping("/me/clinics")
	public List<GetClinic> setClinics(@RequestBody @Valid List<SetClinic> request){
		return clinicService.setClinics(request);
	}
	@DeleteMapping("/me/clinic/{id}")
	public void deleteClinic(@PathVariable Long id) {
		clinicService.deleteClinic(id);
	}
	@GetMapping("/me/patients")
	public Page<GetPatient> getPatient(@ModelAttribute FilterReservations filter,@ModelAttribute PageProperties page ){
		return reservationService.getDoctorPatient(filter, page);
	}
	@GetMapping("/patient/{id}/appointments")
	public Page<GetAppointmentsForOnePatient> getPatientAppointments(@PathVariable Long id,@ModelAttribute FilterReservations filter,@ModelAttribute PageProperties p) {
		return reservationService.getPatientAppointments(id, filter, p);
	}
	@GetMapping("/patient/{id}/prescriptions")
	public Page<GetPrescriptions> getPatientPrescriptions(@PathVariable Long id ,@ModelAttribute PageProperties page){
		return appointmentDetailesService.getPrescriptions(id, page);
	}
	@GetMapping("/me/upcomming-appointment")
	public Page<ReservationDto> getUpcommingAppointment(){
		return reservationService.getUpCommingAppointment();
	}
}
