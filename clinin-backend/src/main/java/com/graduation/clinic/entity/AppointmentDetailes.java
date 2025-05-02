package com.graduation.clinic.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.graduation.clinic.dto.AppointmentDetailesRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
@Entity
public class AppointmentDetailes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Patient Patient;
	@ManyToOne
	private Doctor doctor;
	@OneToOne
	private Reservation reservation;
	private String PreviousMedicalHistory;
	private String clinicalNotes;
	private List<String> laboratoryTests;
	private List<String> complaints;
	@OneToMany(mappedBy = "appointmentDetailes",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Medications> medications;
	private String advice;
	private String followUp;
	
	
	
	public AppointmentDetailes(AppointmentDetailesRequest request) {
		this.advice= request.getAdvice();
		this.clinicalNotes= request.getClinicalNotes();
		this.complaints= request.getComplaints();
		this.followUp= request.getFollowUp();
		this.laboratoryTests= request.getLaboratoryTests();
		List<Medications> medications=new ArrayList<>();
		for(int i=0 ;i<request.getMedications().size();i++) {
			medications.add(new Medications(request.getMedications().get(i)));
		}
		this.medications=medications;
		this.PreviousMedicalHistory= request.getPreviousMedicalHistory();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Patient getPatient() {
		return Patient;
	}
	public void setPatient(Patient patient) {
		Patient = patient;
	}
	
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	public String getPreviousMedicalHistory() {
		return PreviousMedicalHistory;
	}
	public void setPreviousMedicalHistory(String previousMedicalHistory) {
		PreviousMedicalHistory = previousMedicalHistory;
	}
	public String getClinicalNotes() {
		return clinicalNotes;
	}
	public void setClinicalNotes(String clinicalNotes) {
		this.clinicalNotes = clinicalNotes;
	}
	public List<String> getLaboratoryTests() {
		return laboratoryTests;
	}
	public void setLaboratoryTests(List<String> laboratoryTests) {
		this.laboratoryTests = laboratoryTests;
	}
	public List<String> getComplaints() {
		return complaints;
	}
	public void setComplaints(List<String> complaints) {
		this.complaints = complaints;
	}

	public List<Medications> getMedications() {
		return medications;
	}
	public void setMedications(List<Medications> medications) {
		this.medications = medications;
	}
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public String getFollowUp() {
		return followUp;
	}
	public void setFollowUp(String followUp) {
		this.followUp = followUp;
	}
	
}
