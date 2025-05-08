package com.graduation.clinic.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.entity.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Long> ,JpaSpecificationExecutor<Review> {
	//@Query(value = "SELECT reviewer_id , message FROM review where reviewed_doctor_id = ?", nativeQuery = true)
	
	Page<Review> findByReviewedDoctorId(Long DoctorId,Specification<Review> spec,Pageable page);
	
	
	//@Query(value = "SELECT rate FROM Review where reviewedDoctor.Id= ?1 and rate>=1")
	//List<Integer> findRates(Long DoctorId);
	
}
