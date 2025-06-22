package com.graduation.clinic.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.entity.Awards;

@Repository
public interface AwardsRepo extends JpaRepository<Awards, Long> {

	List<Awards> findAllByDoctorId(Long doctorId);
	Optional<Awards> findByIdAndDoctorId(Long id,Long doctorId);
}
