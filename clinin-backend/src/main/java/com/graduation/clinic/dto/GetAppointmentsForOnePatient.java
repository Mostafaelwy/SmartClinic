package com.graduation.clinic.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.graduation.clinic.entity.Address;
import com.graduation.clinic.entity.Reservation;
import com.graduation.clinic.entity.ReservationStatus;
import com.graduation.clinic.entity.VisitType;

public class GetAppointmentsForOnePatient {

	public Long appointmentId;
	public Long doctorId;
	public String doctorName;
	public GetPhoto doctorPhoto;
	public String doctorEmail;
	public List<String> doctorNumbers;
	public LocalDate appointmentDate;
	public LocalTime appointmentTime;
	public LocalDate bookingDate;
	public ReservationStatus status;
	public String cost;
	public String clinicName;
	public Address clinicLocation;
	public VisitType visitType;
	
	
	
	
	
	public GetAppointmentsForOnePatient(Reservation r) {
		this.appointmentId = r.getId();
		this.doctorId = r.getDoctorId();
		this.doctorName = r.getReservedClinic().getDoctor().getFirstName()+ r.getReservedClinic().getDoctor().getFirstName();
		this.appointmentDate = r.getReservationDate();
		this.status = r.getStatus();
		this.cost = r.getCost();
		this.doctorPhoto = new GetPhoto(r.getReservedClinic().getDoctor().getProfilePhoto());
		this.doctorEmail = r.getReservedClinic().getDoctor().getUsername();
		this.doctorNumbers = r.getReservedClinic().getDoctor().getPhoneNumbers();
		this.appointmentTime = r.getReservationTime();
		this.bookingDate = r.getCreationDate();
		this.clinicName = r.getReservedClinic().getClinicName();
		this.clinicLocation = r.getReservedClinic().getAddress();
		this.visitType = r.getVisitType();
	}

	public Long getAppointmentId() {
		return appointmentId;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public ReservationStatus getStatus() {
		return status;
	}
	public String getCost() {
		return cost;
	}
	public GetPhoto getdoctorPhoto() {
		return doctorPhoto;
	}
	public String getDoctorEmail() {
		return doctorEmail;
	}
	public List<String> getDoctorNumbers() {
		return doctorNumbers;
	}
	public LocalTime getAppointmentTime() {
		return appointmentTime;
	}
	public String getClinicName() {
		return clinicName;
	}
	public Address getClinicLocation() {
		return clinicLocation;
	}
	public VisitType getVisitType() {
		return visitType;
	}
	
	
}
