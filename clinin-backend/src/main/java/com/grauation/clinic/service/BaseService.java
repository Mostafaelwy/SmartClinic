package com.grauation.clinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.grauation.clinic.entity.UsersBaseEntity;
import com.grauation.clinic.repos.BaseRepo;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseService<T extends UsersBaseEntity<ID>,ID extends Number> {

	@Autowired
	private BaseRepo<T,ID> baseRepo;
	
	public ResponseEntity<?> findById(ID id){
		return ResponseEntity.ok(baseRepo.findById(id));
	}
}

