package com.graduation.clinic.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.entity.Experience;


@Repository
public interface ExperienceRepo extends JpaRepository<Experience, Long> {

	Optional<Experience> findByIdAndDoctorId(Long id,Long DoctorId);
	List<Experience> findAllByDoctorId(Long doctorId);
	
}
