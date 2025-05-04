package com.graduation.clinic.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.graduation.clinic.entity.Address;
import com.graduation.clinic.entity.Clinic;
import com.graduation.clinic.entity.Days;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.entity.Slot;

public class ClinicDto {

	private  final Long id;
	
	private final String clinicName;
	
	private final Address address;
	
	private final List <String> phoneNumbers;
	
	private final String openingTime;
	
	private final String closingTime;
	
	private final Map<Days ,Slot> workingDays;
	
	//private  List<PatientDto> visitors;
	

	public ClinicDto(Clinic clinic) {
		this.id = clinic.getId();
		this.clinicName = clinic.getClinicName();
		this.address = clinic.getAddress();
		this.phoneNumbers = clinic.getPhoneNumbers();
		this.openingTime = clinic.getOpeningTime();
		this.closingTime = clinic.getClosingTime();
		this.workingDays = clinic.getWorkingDays();
		
		/*
		// return list of patientDto(visitors)
		//List<Patient> patients=clinic.getMyVistors()
		List<PatientDto> visitorsDto=new ArrayList<>();
		for(int i=0;i<patients.size();i++) {
			visitorsDto.add(new PatientDto(patients.get(i)));
		}
		this.visitors=visitorsDto;*/
	}

	public long getId() {
		return id;
	}

	public String getClinicName() {
		return clinicName;
	}

	public Address getAddress() {
		return address;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public String getOpeningTime() {
		return openingTime;
	}

	public String getClosingTime() {
		return closingTime;
	}

	public Map<Days, Slot> getWorkingDays() {
		return workingDays;
	}


/*
	public List<PatientDto> getVisitors() {
		return visitors;
	}
	*/
	
}

