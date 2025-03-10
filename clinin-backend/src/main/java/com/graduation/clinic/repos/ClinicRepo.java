package com.graduation.clinic.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.entity.Clinic;
@Repository
public interface ClinicRepo  extends JpaRepository<Clinic, Integer>{

}
