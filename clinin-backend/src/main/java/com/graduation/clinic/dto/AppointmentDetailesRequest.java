package com.graduation.clinic.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.graduation.clinic.entity.Medications;

public class AppointmentDetailesRequest {

	private String PreviousMedicalHistory;
	private String clinicalNotes;
	private List<String> laboratoryTests;
	private List<String> complaints;
	private List<MedicationCreation> medications;
	private String advice;
	private String followUp;
	
	public AppointmentDetailesRequest(String previousMedicalHistory, String clinicalNotes, List<String> laboratoryTests,
			List<String> complaints, List<MedicationCreation> medicationrequest, String advice, String followUp) {
		PreviousMedicalHistory = previousMedicalHistory;
		this.clinicalNotes = clinicalNotes;
		this.laboratoryTests = laboratoryTests;
		this.complaints = complaints;
		this.medications = medications;
		this.advice = advice;
		this.followUp = followUp;
	}

	public String getPreviousMedicalHistory() {
		return PreviousMedicalHistory;
	}

	public String getClinicalNotes() {
		return clinicalNotes;
	}

	public List<String> getLaboratoryTests() {
		return laboratoryTests;
	}

	public List<String> getComplaints() {
		return complaints;
	}





	public List<MedicationCreation> getMedications() {
		return medications;
	}

	public String getAdvice() {
		return advice;
	}

	public String getFollowUp() {
		return followUp;
	}
	
	
}
