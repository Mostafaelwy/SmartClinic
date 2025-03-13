package com.graduation.clinic.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.clinic.dto.ClinicDto;
import com.graduation.clinic.dto.DoctorDto;
import com.graduation.clinic.entity.Clinic;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	private final DoctorService doctorService;

	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}
	
	
	
	@PostMapping("/insert")
	public DoctorDto insertDoctor(@RequestBody Doctor doctor) {
		return doctorService.insertDoctor(doctor);
	}
	@GetMapping("/get-all")
	public List<DoctorDto> findAllDoctors(){
		return doctorService.findAllDoctors();
	}
	@PostMapping("/add-clinic")
	public ClinicDto createClinic(@RequestBody Clinic clinic) {
		return doctorService.createClinic(clinic);
	}
	@GetMapping("/get-all-clinics")
	public List<ClinicDto> findAll(){
		return doctorService.findAllClinics();
	}
}
