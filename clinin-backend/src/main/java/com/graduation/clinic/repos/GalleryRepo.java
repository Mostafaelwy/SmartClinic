package com.graduation.clinic.repos;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.entity.Gallery;

@Repository
public interface GalleryRepo extends JpaRepository<Gallery, Long> {

	Set<Gallery> findAllByClinicId(Long clinicId);
}
