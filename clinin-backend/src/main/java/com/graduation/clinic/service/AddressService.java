package com.graduation.clinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduation.clinic.repos.AddressRepo;

@Service
public class AddressService {

	@Autowired
	private AddressRepo addressRepo;
	
}
