package com.graduation.clinic.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.graduation.clinic.entity.AppointmentDetailes;

public class GetPrescriptions {

	private Long prescriptionId;
	private LocalDate issuedDate;
	private GetPatientDetailes patient;
	private GetDoctorDetailes doctor;
	private List<GetMedications> medications;
	private String otherInformation;
	private String followUp;
	
	
	public GetPrescriptions(AppointmentDetailes det) {
		this.prescriptionId = det.getId();
		this.issuedDate = det.getIssuedDate();
		this.patient = new GetPatientDetailes(det.getPatient());
		this.doctor = new GetDoctorDetailes(det.getDoctor(),det.getReservation().getReservedClinic());
		List<GetMedications> getMedicationsList=new ArrayList<>();
		for(int i=0;i<det.getMedications().size();i++) {
			getMedicationsList.add(new GetMedications(det.getMedications().get(i)));
		}
		this.medications = getMedicationsList;
		this.otherInformation = det.getAdvice();
		this.followUp = det.getFollowUp();
	}
	public Long getPrescriptionId() {
		return prescriptionId;
	}
	public LocalDate getIssuedDate() {
		return issuedDate;
	}
	public GetPatientDetailes getPatient() {
		return patient;
	}
	public GetDoctorDetailes getDoctor() {
		return doctor;
	}
	public List<GetMedications> getMedications() {
		return medications;
	}
	public String getOtherInformation() {
		return otherInformation;
	}
	public String getFollowUp() {
		return followUp;
	}
	
	
}
