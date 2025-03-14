package com.graduation.clinic.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.clinic.dto.PatientDto;
import com.graduation.clinic.dto.ReviewDto;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {


	private final PatientService patientService;

	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	@PostMapping("/insert")
	public PatientDto insertPatient(@RequestBody Patient patient){
		return patientService.insertPatient(patient);
	}
	@PostMapping("/write-review")
	public ReviewDto writeReview(@RequestParam Long DoctorId,@RequestParam String reviewerEmail,@RequestParam String Message) {
		return patientService.writeReview(DoctorId, reviewerEmail, Message);
	}
}
