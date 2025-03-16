package com.graduation.clinic.repos;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.dto.DoctorDto;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.UsersBaseEntity;


@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long>{

	List <Doctor> findAll();
	
}
