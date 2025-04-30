package com.graduation.clinic.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.graduation.clinic.entity.ClinicsVistors;

public class AddVisitorResponse {

	private Long id;
	private Long doctorId;
	private LocalDate visitationDate;
	private String patientUserName;
	
	public AddVisitorResponse(ClinicsVistors clvisitors) {
		this.id = clvisitors.getId();
		this.visitationDate = clvisitors.getVisitationDate();
		this.patientUserName = clvisitors.getVisitor().getUsername();
		this.doctorId=clvisitors.getDoctorId();
	}

	public Long getId() {
		return id;
	}

	public LocalDate getVisitationDate() {
		return visitationDate;
	}

	public String getPatientUserName() {
		return patientUserName;
	}

	public Long getDoctorId() {
		return doctorId;
	}
	
	
	
	
}
