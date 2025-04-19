package com.graduation.clinic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.ClinicDto;
import com.graduation.clinic.dto.CreateClinicResponse;
import com.graduation.clinic.dto.DoctorBasicDetailes;
import com.graduation.clinic.dto.DoctorBasicDetailesRequest;
import com.graduation.clinic.dto.UserDto;
import com.graduation.clinic.entity.Clinic;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.UsersBaseEntity;
import com.graduation.clinic.exceptions.NotFoundException;
import com.graduation.clinic.repos.BaseUserRepo;
import com.graduation.clinic.repos.DoctorRepo;

@Service  
public class DoctorService {

	private final DoctorRepo doctorRepo;

	//private final ReviewService reviewService;
	public DoctorService(DoctorRepo doctorRepo ) {
		this.doctorRepo = doctorRepo;
		//this.reviewService=reviewService;
	}

		

	
	public Doctor findById(Long id) {
		return doctorRepo.findById(id).orElseThrow(()->new NotFoundException("doctor not found."));
	}
	public DoctorBasicDetailes updateDetailes(DoctorBasicDetailesRequest newDoctorData) {
		Doctor oldDoctorData=doctorRepo.findById(newDoctorData.getId()).orElseThrow(()->new NotFoundException("doctor not found"));
		oldDoctorData.setFirstName(newDoctorData.getFirstName());
		oldDoctorData.setSecondName(newDoctorData.getSecondName());
		oldDoctorData.setAge(newDoctorData.getAge());
		oldDoctorData.setExperienceYears(newDoctorData.getExperienceYears());
		oldDoctorData.setPhoto(newDoctorData.getPhoto());
		oldDoctorData.setPhoneNumbers(newDoctorData.getPhoneNumbers());
		oldDoctorData.setSpecilization(newDoctorData.getSpecilization());
		return new DoctorBasicDetailes(doctorRepo.save(oldDoctorData));
	}
	 public Doctor getDoctor(String username) {
		 return doctorRepo.findByUserName(username).orElseThrow(()->new NotFoundException("user not found"));
	 }
	 

	

	

	
	

	
}
