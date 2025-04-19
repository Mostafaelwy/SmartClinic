package com.graduation.clinic.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.ReceptionistDto;
import com.graduation.clinic.entity.Receptionist;
import com.graduation.clinic.repos.ReceptionistRepo;

@Service
public class ReceptionistService {
	
	private final ReceptionistRepo receptionistRepo;
	private final ClinicService clinicService;
	public ReceptionistService(ReceptionistRepo receptionistRepo,ClinicService clinicService) {
		this.receptionistRepo = receptionistRepo;
		this.clinicService= clinicService;
	}
	
	public ReceptionistDto insertReceptionist(Receptionist recep) {
		return new ReceptionistDto(receptionistRepo.save(recep));
	}
	
}
