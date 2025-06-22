package com.graduation.clinic.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.entity.Rating;
@Repository
public interface RatingRepo extends JpaRepository<Rating,Long>,JpaSpecificationExecutor<Rating> {

	Optional <Rating> findByRatedDoctorIdAndRaterId(Long doctorId,Long PatientId);
}
