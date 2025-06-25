package com.graduation.clinic.repos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.entity.ReservedTime;

@Repository
public interface ReservedTimesRepo extends JpaRepository<ReservedTime, Long>{

	List<ReservedTime> findByClinicIdAndDate(Long Id,LocalDate date);
	Optional<ReservedTime> findByClinicIdAndDateAndTime(Long clinicId,LocalDate date,LocalTime time);
}
