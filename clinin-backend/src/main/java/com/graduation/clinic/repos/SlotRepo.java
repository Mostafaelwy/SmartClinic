package com.graduation.clinic.repos;

import java.time.DayOfWeek;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graduation.clinic.entity.Days;
import com.graduation.clinic.entity.Slot;

public interface SlotRepo extends JpaRepository<Slot, Long> {

	Optional<Slot> findByClinicIdAndDay(Long clinicId,DayOfWeek workingDay);
}
