package com.graduation.clinic.service;

import java.util.ArrayList;
import java.util.List;
import com.graduation.clinic.dto.UserDto;
import com.graduation.clinic.entity.UsersBaseEntity;
import com.graduation.clinic.repos.BaseUserRepo;



public class BaseUserService {
	
	
	
	private final BaseUserRepo userRepo;
	
	public BaseUserService(BaseUserRepo userRepo) {
		this.userRepo = userRepo;
	}


	//return type should be a dto(proxy object to the entity [patient, doctor, .. and user])
	public UserDto findById(Long id){
		UsersBaseEntity user = userRepo.findById(id).orElseThrow(RuntimeException::new);
		return new UserDto(user);
	}

}
