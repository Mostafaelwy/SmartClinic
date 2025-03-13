package com.graduation.clinic.repos;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Patient;
@Repository
public interface PatientRepo extends JpaRepository<Patient, Long>{
	
	Optional<Patient> findByEmail(String email);
	
}
