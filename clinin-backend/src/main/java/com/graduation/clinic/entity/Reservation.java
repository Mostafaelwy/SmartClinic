package com.graduation.clinic.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="reservations")
public class Reservation {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private Long doctorId;
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@Enumerated(EnumType.STRING)
	private ReservationStatus status;
	
	@ManyToOne
	@JoinColumn(name = "reserved_clinic")
	private Clinic reservedClinic;
	
	@NotNull
	private LocalDate reservationDate;
	
	@NotNull
	private LocalDate creationDate;
	@NotNull
	private VisitType visitType;
	
	private AppointmentDetailes appointmentDetailes;

	
	public AppointmentDetailes getAppointmentDetailes() {
		return appointmentDetailes;
	}

	public void setAppointmentDetailes(AppointmentDetailes appointmentDetailes) {
		this.appointmentDetailes = appointmentDetailes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public Clinic getReservedClinic() {
		return reservedClinic;
	}

	public void setReservedClinic(Clinic reservedClinic) {
		this.reservedClinic = reservedClinic;
	}

	public LocalDate getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public VisitType getVisitType() {
		return visitType;
	}

	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
	}
	
	
	
	
	
	
}

