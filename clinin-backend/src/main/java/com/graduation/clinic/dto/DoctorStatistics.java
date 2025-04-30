package com.graduation.clinic.dto;

public class DoctorStatistics {

	private Long totalPatient;
	private Long totalPatientToday;
	private Long totalAppointmentsToday;
	
	public Long getTotalPatient() {
		return totalPatient;
	}
	public void setTotalPatient(Long totalPatient) {
		this.totalPatient = totalPatient;
	}
	public Long getTotalPatientToday() {
		return totalPatientToday;
	}
	public void setTotalPatientToday(Long totalPatientToday) {
		this.totalPatientToday = totalPatientToday;
	}
	public Long getTotalAppointmentsToday() {
		return totalAppointmentsToday;
	}
	public void setTotalAppointmentsToday(Long totalAppointmentsToday) {
		this.totalAppointmentsToday = totalAppointmentsToday;
	}
	
	
}
