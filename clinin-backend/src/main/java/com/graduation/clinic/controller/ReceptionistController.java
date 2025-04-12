package com.graduation.clinic.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduation.clinic.dto.ReceptionistDto;
import com.graduation.clinic.entity.Receptionist;
import com.graduation.clinic.service.ReceptionistService;

@RestController
@RequestMapping("/smart/receptionist")
public class ReceptionistController {
	private final ReceptionistService recepService;

	public ReceptionistController(ReceptionistService recepService) {
		this.recepService = recepService;
	}
	
	@PostMapping("/insert")
	public ReceptionistDto insertReceptionist(@RequestBody Receptionist recep) {
		return recepService.insertReceptionist(recep);
	}
	@PutMapping("/add-visitor")
	public int addVisitor(@RequestParam String email,@RequestParam Long id) {
		recepService.addVisitor(email, id);
		return 0;
	}
}
