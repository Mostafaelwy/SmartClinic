package com.graduation.clinic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.ReservationDto;
import com.graduation.clinic.dto.ReservationRequest;
import com.graduation.clinic.entity.Clinic;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.entity.Reservation;
import com.graduation.clinic.entity.ReservationStatus;
import com.graduation.clinic.exceptions.NotFoundException;
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
	
	public Page<ReservationDto> findAllReservation(Long Clinicid,int pageNum){
		
		Pageable page=PageRequest.of(pageNum,4,Sort.by(Direction.ASC, "reservationDay"));
		
		Page<Reservation> reservation=reservationRepo.findByReservedClinicId(Clinicid,page);
	
		return paginateReservationDto(reservation);
		
	}
	public ReservationStatus makeReservation(ReservationRequest request) {
		Clinic clinic=clinicService.findById(request.getClinicId());
		Patient patient =patientService.findById(request.getPatientId());
		
		Reservation reservation=new Reservation();
		reservation.setPatient(patient);
		reservation.setReservedClinic(clinic);
		reservation.setStatus(ReservationStatus.PENDING);
		reservation.setReservationDay(request.getReservationDay());
		
		return (reservationRepo.save(reservation)).getStatus();
		
	}
	
	public ReservationDto alterReservationStatus(Reservation request) {
		Reservation reservation= reservationRepo.findById(request.getId()).orElseThrow(()->new NotFoundException("reservation not found"));
		reservation.setStatus(request.getStatus());
		return new ReservationDto(reservationRepo.save(reservation));
	}
	public Page<ReservationDto> filterReservationByStatus(Long id,ReservationStatus status,int pageNum) {

		Pageable page =PageRequest.of(pageNum,2, Direction.ASC, "reservationDay");
		
		if (status == null || status.name() == "") {
			Page<Reservation> reservationPage = reservationRepo.findByReservedClinicId(id, page);
			return paginateReservationDto(reservationPage);
		}else {
			Page<Reservation> reservationPage=reservationRepo.findByReservedClinicIdAndStatus(id, status, page);
			return paginateReservationDto(reservationPage);
		}
	
	}
}
