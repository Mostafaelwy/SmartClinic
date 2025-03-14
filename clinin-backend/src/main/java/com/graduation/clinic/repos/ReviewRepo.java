package com.graduation.clinic.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.entity.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Long> {

	
	
}
