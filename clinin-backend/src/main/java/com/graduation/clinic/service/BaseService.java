package com.graduation.clinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.graduation.clinic.entity.UsersBaseEntity;
import com.graduation.clinic.repos.BaseRepo;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseService <T extends UsersBaseEntity<ID> ,ID extends Number> {
	@Autowired
	private BaseRepo<T, ID> baseRepo;
	
	public ResponseEntity<?> findById(ID id){
		return ResponseEntity.ok(baseRepo.findById(id));
	}
	
}
