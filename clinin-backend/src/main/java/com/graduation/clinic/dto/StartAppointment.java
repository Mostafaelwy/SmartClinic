package com.graduation.clinic.dto;

import java.time.LocalDate;

import com.graduation.clinic.entity.Address;
import com.graduation.clinic.entity.VisitType;

public class StartAppointment {

	private PatientDto patient;
	private int patientVisitsNum;
	private LocalDate appointmentDate;
	private String clinicName;
	private Address clinicLocation;
	private VisitType visitType;
	
	public StartAppointment(PatientDto patient, int patientVisitsNum, LocalDate appointmentDate, String clinicName,
			Address clinicLocation, VisitType visitType) {
		this.patient = patient;
		this.patientVisitsNum = patientVisitsNum;
		this.appointmentDate = appointmentDate;
		this.clinicName = clinicName;
		this.clinicLocation = clinicLocation;
		this.visitType = visitType;
	}

	public PatientDto getPatient() {
		return patient;
	}

	public int getPatientVisitsNum() {
		return patientVisitsNum;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
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
