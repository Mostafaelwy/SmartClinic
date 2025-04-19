package com.graduation.clinic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.ClinicData;
import com.graduation.clinic.dto.ClinicDto;
import com.graduation.clinic.dto.CreateClinicRequest;
import com.graduation.clinic.dto.CreateClinicResponse;

import com.graduation.clinic.dto.UpdateClinicDetailesRequest;

import com.graduation.clinic.entity.Clinic;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.exceptions.DuplicateException;
import com.graduation.clinic.exceptions.NotFoundException;
import com.graduation.clinic.repos.ClinicRepo;

@Service
public class ClinicService {

	private final ClinicRepo clinicRepo ;
	private final DoctorService doctorService;
	private final PatientService patientService;
	public ClinicService(ClinicRepo clinicRepo ,PatientService patientService,DoctorService doctorService) {
		this.clinicRepo = clinicRepo;
		this.patientService=patientService;
		this.doctorService=doctorService;
	}
	
	public CreateClinicResponse createClinic(Long doctorId,CreateClinicRequest request) {
		Doctor doc=doctorService.findById(doctorId);
		Clinic clinic =new Clinic(request,doc);
		return new CreateClinicResponse(clinicRepo.save(clinic));
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
	
	public ClinicDto addVisitor(Long id,Long ClinicId) {
		Patient visitor=patientService.findById(id);
		Clinic clinic=clinicRepo.findById(ClinicId).orElseThrow(()-> new NotFoundException(" Clinic not found "));
		clinic.addVisitor(visitor);
		return new ClinicDto(clinicRepo.save(clinic));
	
	}
	
	public ClinicData updateClinicDetailesRequest(UpdateClinicDetailesRequest detailes) {
		Clinic clinic =clinicRepo.findById(detailes.getId()).orElseThrow(()->new NotFoundException("clinic not found"));
	

		clinic.setClinicName(detailes.getClinicName());	
		clinic.setOpeningTime(detailes.getOpeningTime());
		clinic.setClosingTime(detailes.getClosingTime());
		clinic.setPhoneNumbers(detailes.getPhoneNumbers());
		clinic.setWorkingDays(detailes.getWorkingDays());
		clinic.setLocation(detailes.getLocation());
		clinic.setLogo(detailes.getLogo());
		
		return  new ClinicData(clinicRepo.save(clinic));
	}
	
	public Clinic findById(Long id) {
		return clinicRepo.findById(id).orElseThrow(()->new NotFoundException("Clinic not found"));
	}
	
	public List<ClinicData> findDoctorClinics(Long id){
		List<Clinic> clinics=clinicRepo.findByDoctorId(id);
		List<ClinicData> dtos=new ArrayList<>();
		for(int i=0;i<clinics.size();i++) {
			dtos.add(new ClinicData(clinics.get(i)));
		}
		return dtos;
	}
	
	
	
}

