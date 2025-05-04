package com.graduation.clinic.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.entity.Slot;
@Repository
public interface SlotsRepo extends JpaRepository<Slot, Long>{

}
