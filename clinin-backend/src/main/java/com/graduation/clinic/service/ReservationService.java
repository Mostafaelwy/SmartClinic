package com.graduation.clinic.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.graduation.clinic.Specifications.ReservationSpecifications;
import com.graduation.clinic.dto.FilterReservations;
import com.graduation.clinic.dto.GetAppointmentsForOnePatient;
import com.graduation.clinic.dto.GetPatient;
import com.graduation.clinic.dto.PageProperties;
import com.graduation.clinic.dto.ReservationDto;
import com.graduation.clinic.dto.ReservationRequest;
import com.graduation.clinic.entity.Clinic;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.entity.Reservation;
import com.graduation.clinic.entity.ReservationStatus;
import com.graduation.clinic.exceptions.NotFoundException;
import com.graduation.clinic.exceptions.TimeException;
import com.graduation.clinic.repos.ReservationRepo;

@Service
public class ReservationService {

	private final ReservationRepo reservationRepo;
	private final ClinicService clinicService;
	private final PatientService patientService;

	public ReservationService(ReservationRepo reservationRepo,ClinicService clinicService,PatientService patientService) {
		this.reservationRepo = reservationRepo;
		this.clinicService=clinicService;
		this.patientService=patientService;
	}
	
	private ReservationDto convertReservationToDto(Reservation reservation) {
		return new ReservationDto(reservation);
	}
	private Page<ReservationDto> paginateReservationDto(Page<Reservation> reservations){
		Page<ReservationDto> dtos=reservations.map(this::convertReservationToDto);
		return dtos;
	}
	private GetPatient convertReservationToGetPatient(Reservation r) {
		return new GetPatient(r);
	}
	private Page<GetPatient> paginateGetPatient(Page<Reservation> reservationPage){
		return reservationPage.map(this::convertReservationToGetPatient);
	}
	private GetAppointmentsForOnePatient converReservationToGetAppoinment(Reservation r) {
		return new GetAppointmentsForOnePatient(r);
	}
	private Page<GetAppointmentsForOnePatient> paginateAppoinments(Page<Reservation> reservationPage){
		return reservationPage.map(this:: converReservationToGetAppoinment );
	}

	
	public ReservationDto makeReservation(ReservationRequest request,Long clinicId) {
		
		if(request.getReservationDate().isAfter(LocalDate.now())) {
			Clinic clinic=clinicService.findById(clinicId);
			
			Authentication auth =SecurityContextHolder.getContext().getAuthentication();
			Patient patient=(Patient) auth.getPrincipal();
			
			Long doctorId=clinic.getDoctor().getId();
			
			Reservation reservation=new Reservation();
			reservation.setPatient(patient);
			reservation.setReservedClinic(clinic);
			reservation.setStatus(ReservationStatus.PENDING);
			reservation.setReservationDate(request.getReservationDate());
			reservation.setDoctorId(doctorId);
			reservation.setCreationDate(LocalDate.now());
			reservation.setVisitType(request.getVisitType());
			return new ReservationDto(reservationRepo.save(reservation));
		}else {
			throw new TimeException("reservation date must be at least after one day from reservation Request time.");
		}

	}
	
	public ReservationDto alterReservationStatus(Long reservationId , ReservationStatus status) {
		
		Reservation reservation= reservationRepo.findById(reservationId).orElseThrow(()->new NotFoundException("reservation not found"));
		if(status==null|| status.name()=="") {
			
			return new ReservationDto(reservationRepo.save(reservation));
		}else {
			reservation.setStatus(status);
			return new ReservationDto(reservationRepo.save(reservation));
		}
	}
	
	public Page<ReservationDto> reservationSearch(FilterReservations filter, int pageNum,int pageSize){
		
		Pageable page=PageRequest.of(pageNum, pageSize);
		
		Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		Doctor doc=(Doctor) auth.getPrincipal();
		
		Page<Reservation> reservationPage=reservationRepo.findAll(
	
				Specification.where(ReservationSpecifications.hasDoctorId(doc.getId()))
							.and(ReservationSpecifications.hasStatus(filter.getStatus()))
							.and(ReservationSpecifications.hasCreationDate(filter.getStartTime(), filter.getEndTime()))
							.and(ReservationSpecifications.hasPatientName(filter.getPatientName()))
							.and(ReservationSpecifications.hasVisitType(filter.getVisitType()))
				,page);
		
		return paginateReservationDto(reservationPage);
	}
	
	public Page<GetPatient> getDoctorPatient(FilterReservations filter,PageProperties pageDetailes){
		Pageable p=PageRequest.of(pageDetailes.getPageNum(), pageDetailes.getPageSize(), pageDetailes.getDir(), pageDetailes.getSortAttripute());
		
		Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		Doctor doc=(Doctor) auth.getPrincipal();
		
		Page<Reservation> reservationPage=reservationRepo.findAll(
				
				Specification.where(ReservationSpecifications.hasDoctorId(doc.getId()))
							.and(ReservationSpecifications.hasStatus(ReservationStatus.COMPLETED))
							.and(ReservationSpecifications.hasCreationDate(filter.getStartTime(), filter.getEndTime()))
							.and(ReservationSpecifications.hasPatientName(filter.getPatientName()))
							.and(ReservationSpecifications.hasVisitType(filter.getVisitType()))
				,p);
		
		return paginateGetPatient(reservationPage);
	}

	public Page<GetAppointmentsForOnePatient> getPatientAppointments(Long patientId ,FilterReservations filter,PageProperties pageDetailes){
		
		Pageable p=PageRequest.of(pageDetailes.getPageNum(), pageDetailes.getPageSize(), pageDetailes.getDir(), pageDetailes.getSortAttripute());

		Page<Reservation> reservationPage=reservationRepo.findAll(
				
				Specification.where(ReservationSpecifications.hasPatientId(patientId))
							.and(ReservationSpecifications.hasStatus(filter.getStatus()))
							.and(ReservationSpecifications.hasCreationDate(filter.getStartTime(), filter.getEndTime()))
							.and(ReservationSpecifications.hasDoctorName(filter.getDoctorName()))
							.and(ReservationSpecifications.hasVisitType(filter.getVisitType()))
				,p);
		
		return paginateAppoinments(reservationPage);
		
	}
	
	

}
