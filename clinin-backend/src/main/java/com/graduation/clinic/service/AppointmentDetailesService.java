package com.graduation.clinic.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.AppointmentDetailesRequest;
import com.graduation.clinic.dto.GetPrescriptions;
import com.graduation.clinic.dto.PageProperties;
import com.graduation.clinic.dto.PatientDto;
import com.graduation.clinic.dto.StartAppointment;
import com.graduation.clinic.entity.AppointmentDetailes;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Reservation;
import com.graduation.clinic.entity.ReservationStatus;
import com.graduation.clinic.exceptions.GenericException;
import com.graduation.clinic.exceptions.NotFoundException;
import com.graduation.clinic.repos.AppointmentDetailesRepo;
import com.graduation.clinic.repos.ReservationRepo;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
@Service
public class AppointmentDetailesService {

	private final AppointmentDetailesRepo appointmentDetailesRepo;

	private final ReservationRepo reservationRepo;
	private final PatientService patientService;
	
	public AppointmentDetailesService(
			AppointmentDetailesRepo appointmentDetailesRepo,
			ReservationRepo reservationRepo,
			PatientService patientService
			) {
		this.appointmentDetailesRepo = appointmentDetailesRepo;
		this.reservationRepo = reservationRepo;
		this.patientService=patientService;
	}
	
	private GetPrescriptions getPrescriptionFromAppoinmentDetailes(AppointmentDetailes p) {
		return new GetPrescriptions(p);
		
	}
	private Page<GetPrescriptions> paginateGetPrescriptions(Page<AppointmentDetailes> det){
		return det.map(this::getPrescriptionFromAppoinmentDetailes);
	}

	@Transactional(value = TxType.REQUIRES_NEW)
	public ReservationStatus endAppointment(Long reservationId , AppointmentDetailesRequest request) {
		Reservation reservation=reservationRepo.findById(reservationId).orElseThrow(()-> new NotFoundException("reservation not found"));
		if(reservation.getStatus().equals(ReservationStatus.ACCEPTED)) {
		
			
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			Doctor doc=(Doctor) auth.getPrincipal();
			
			AppointmentDetailes detailes=new AppointmentDetailes(request);
			detailes.setReservation(reservation);
			detailes.setPatient(reservation.getPatient());
			detailes.setDoctor(doc);
			
			reservation.setStatus(ReservationStatus.COMPLETED);
			reservationRepo.save(reservation);
			return appointmentDetailesRepo.save(detailes).getReservation().getStatus();
		}else {
			throw new GenericException("Status should be ACCEPTED. the status of this reservation is : "+reservation.getStatus());
		}
		
	}
	//@Transactional(value = TxType.REQUIRES_NEW)
	public StartAppointment startAppointment(Long reservationId) {
		
		Reservation reservation=reservationRepo.findById(reservationId).orElseThrow(()-> new NotFoundException("reservation not found"));
		if(reservation.getStatus().equals(ReservationStatus.ACCEPTED)) {
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			Doctor doc=(Doctor) auth.getPrincipal();
			
			PatientDto patient=new PatientDto(reservation.getPatient());
			
			int numOfvisits=appointmentDetailesRepo.countByDoctorIdAndPatientId(doc.getId(),patient.getId());
			return new StartAppointment(reservation,numOfvisits);
		
		}else {
			throw new GenericException("Status should be ACCEPTED. the status of this reservation is : "+reservation.getStatus());
		}
	}
	
	public Page<GetPrescriptions> getPrescriptions(Long PatientId,PageProperties p){
		Pageable page=PageRequest.of(p.getPageNum(), p.getPageSize(),p.getDir(),p.getSortAttripute());
		Page<AppointmentDetailes>  detPage=appointmentDetailesRepo.findByPatientId(PatientId,page);
		return paginateGetPrescriptions(detPage);
	}
	
}
