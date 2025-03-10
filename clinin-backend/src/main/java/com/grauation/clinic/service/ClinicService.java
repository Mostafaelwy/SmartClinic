package com.grauation.clinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grauation.clinic.repos.ClinicRepo;
@Service
public class ClinicService {

	@Autowired
	private ClinicRepo clinicRepo;
}
