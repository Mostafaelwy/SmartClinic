package com.graduation.clinic.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "clinics_visitors")
public class ClinicsVistors {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	@NotNull
	private Long doctorId;
	@ManyToOne
	@JoinColumn(name = "clinic_id")
	@NotNull
	private Clinic clinic;
	
	@ManyToOne
	@JoinColumn(name = "visitor_id")
	@NotNull
	private Patient visitor;
	
	@Column(name = "visitation_Date")
	@NotNull
	private LocalDate visitationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public Patient getVisitor() {
		return visitor;
	}

	public void setVisitor(Patient visitor) {
		this.visitor = visitor;
	}

	public LocalDate getVisitationDate() {
		return visitationDate;
	}

	public void setVisitationDate(LocalDate localDate) {
		this.visitationDate = localDate;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	
	
	
}
