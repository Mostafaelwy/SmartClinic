package com.graduation.clinic.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.entity.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Long> {
	//@Query(value = "SELECT reviewer_id , message FROM review where reviewed_doctor_id = ?", nativeQuery = true)
	Page<Review> findByReviewedDoctorId(Long id,Pageable page);
	
}
