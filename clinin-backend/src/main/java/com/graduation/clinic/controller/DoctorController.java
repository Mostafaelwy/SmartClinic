package com.graduation.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.clinic.service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
	@Autowired
	private DoctorService doctorService;
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> findById(@PathVariable int id){
		return ResponseEntity.ok(doctorService.findById(null));
	}
}
