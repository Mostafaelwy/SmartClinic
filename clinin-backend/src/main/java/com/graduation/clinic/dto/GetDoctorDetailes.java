package com.graduation.clinic.dto;

import com.graduation.clinic.entity.Address;
import com.graduation.clinic.entity.Clinic;
import com.graduation.clinic.entity.Doctor;

public class GetDoctorDetailes {

	private Long doctorId;
	private String doctorName;
	private Address address;
	private GetPhoto photo;
	
	public GetDoctorDetailes(Doctor d,Clinic c) {
		this.doctorId=d.getId();
		this.doctorName = d.getFirstName()+d.getSecondName();
		this.address = c.getAddress();
		this.photo=new GetPhoto(d.getProfilePhoto());
	}
	
	public Long getDoctorId() {
		return doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}
	public Address getAddress() {
		return address;
	}
	public GetPhoto getPhoto() {
		return photo;
	}
	
	

	
}
