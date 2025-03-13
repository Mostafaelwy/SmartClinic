package com.graduation.clinic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.ClinicDto;
import com.graduation.clinic.dto.PatientDto;
import com.graduation.clinic.entity.Clinic;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.repos.ClinicRepo;

@Service
public class ClinicService {

	private final ClinicRepo clinicRepo ;
	private final PatientService patientService;
	public ClinicService(ClinicRepo clinicRepo ,PatientService patientService) {
		this.clinicRepo = clinicRepo;
		this.patientService=patientService;
	}
	
	public ClinicDto createClinic(Clinic clinic) {
		return new ClinicDto(clinicRepo.save(clinic));
	}
	
	public List<ClinicDto> getAllClinics(){
		
		List <Clinic> clinics=clinicRepo.findAll();
		List <ClinicDto> clinicDtoList=new ArrayList<>();
		if(!clinics.isEmpty()) {
			for(int i=0 ; i < clinics.size() ;i++) {
				clinicDtoList.add(new ClinicDto(clinics.get(i)));
			}
			return clinicDtoList;
		}
		return clinicDtoList;
	}
	
	public int addVisitor(String visitorEmail,long ClinicId) {
		Patient visitor=patientService.findPatient(visitorEmail);
		Clinic clinic=clinicRepo.findById(ClinicId).orElseThrow();
		clinic.addVisitor(visitor);
		clinicRepo.save(clinic);
		return 0 ;
	}
	
	
	
}
