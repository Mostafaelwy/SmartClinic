package com.graduation.clinic.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.entity.Education;

@Repository
public interface EducationRepo extends JpaRepository<Education, Long> {

	Optional<Education> findByIdAndDoctorId(Long id,Long doctorID);
	List<Education> findAllByDoctorId(Long doctorId);
}
