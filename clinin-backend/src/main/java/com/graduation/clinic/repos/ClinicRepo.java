package com.graduation.clinic.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.entity.Clinic;




@Repository
public interface ClinicRepo  extends JpaRepository<Clinic, Integer>{

	Optional <Clinic> findById(long id);
	Optional <Clinic> findByClinicName(String name);
	List<Clinic> findByDoctorId(Long id);
	

	
	
	
}
