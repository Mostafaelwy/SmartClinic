package com.graduation.clinic.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.AppointmentDetailesRequest;
import com.graduation.clinic.entity.AppointmentDetailes;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Reservation;
import com.graduation.clinic.entity.ReservationStatus;
import com.graduation.clinic.exceptions.GenericException;
import com.graduation.clinic.exceptions.NotFoundException;
import com.graduation.clinic.repos.AppointmentDetailesRepo;
import com.graduation.clinic.repos.ReservationRepo;
@Service
public class AppointmentDetailesService {

	private final AppointmentDetailesRepo appointmentDetailesRepo;

	private final ReservationRepo reservationRepo;
	
	public AppointmentDetailesService(
			AppointmentDetailesRepo appointmentDetailesRepo,
			ReservationRepo reservationRepo
			) {
		this.appointmentDetailesRepo = appointmentDetailesRepo;
		this.reservationRepo = reservationRepo;
	}

	public void addAppointmentDetailes(Long reservationId , AppointmentDetailesRequest request) {
		Reservation reservation=reservationRepo.findById(reservationId).orElseThrow(()-> new NotFoundException("reservation not found"));
		if(reservation.getStatus().equals(ReservationStatus.ACCEPTED)) {
			
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			Doctor doc=(Doctor) auth.getPrincipal();
			
			AppointmentDetailes detailes=new AppointmentDetailes(request);
			detailes.setReservation(reservation);
			detailes.setPatient(reservation.getPatient());
			detailes.setDoctor(doc);
			
			appointmentDetailesRepo.save(detailes);

		}else {
			throw new GenericException("can not add appointment detailes to this reservation. 'Status should be ACCEPTED'");
		}
		
	}
	
}
