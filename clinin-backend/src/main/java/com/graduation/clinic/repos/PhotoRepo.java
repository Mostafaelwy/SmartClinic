package com.graduation.clinic.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.entity.Photo;

@Repository
public interface PhotoRepo extends JpaRepository<Photo,Long>{

}
