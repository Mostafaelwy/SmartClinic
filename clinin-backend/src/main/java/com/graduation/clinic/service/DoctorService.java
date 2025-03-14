package com.graduation.clinic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.ClinicDto;
import com.graduation.clinic.dto.DoctorDto;
import com.graduation.clinic.dto.UserDto;
import com.graduation.clinic.entity.Clinic;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.UsersBaseEntity;
import com.graduation.clinic.repos.BaseUserRepo;
import com.graduation.clinic.repos.DoctorRepo;

@Service  
public class DoctorService {

	private final DoctorRepo doctorRepo;

	private final ClinicService clinicService;
	//private final ReviewService reviewService;
	public DoctorService(DoctorRepo doctorRepo , ClinicService clinicService) {
		this.doctorRepo = doctorRepo;
		this.clinicService=clinicService;
		//this.reviewService=reviewService;
	}


	public DoctorDto insertDoctor(Doctor doctor) {
		return new DoctorDto(doctorRepo.save(doctor));
	}
		
	
	public List<DoctorDto> findAllDoctors(){
		List <DoctorDto> doctorDto=new ArrayList<>();
		List<Doctor> doctors= doctorRepo.findAll();
		if (! doctors.isEmpty()) {
			for (int i = 0; i < doctors.size(); i++) {
				doctorDto.add(new DoctorDto(doctors.get(i)));
			}
			return doctorDto;
		}
		return doctorDto;
	}
	
	
	public ClinicDto createClinic(Clinic clinic) {
		return clinicService.createClinic(clinic);
	}
	
	
	public List<ClinicDto> findAllClinics(){
		return clinicService.getAllClinics();
	}
	
	public Doctor findById(Long id) {
		return doctorRepo.findById(id).orElseThrow();
	}
	
	
	

	

	
	

	
}
