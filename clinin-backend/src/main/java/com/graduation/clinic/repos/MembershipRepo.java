package com.graduation.clinic.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.entity.Memberships;

@Repository
public interface MembershipRepo extends JpaRepository<Memberships, Long> {

	List<Memberships> findAllByDoctorId(Long doctorId);
	Optional<Memberships> findByIdAndDoctorId(Long id,Long doctorId);
}
