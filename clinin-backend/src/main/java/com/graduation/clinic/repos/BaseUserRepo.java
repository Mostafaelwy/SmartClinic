package com.graduation.clinic.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.UsersBaseEntity;

@Repository
public interface BaseUserRepo extends JpaRepository<UsersBaseEntity, Long> {

	Optional <UsersBaseEntity> findById(Long id);
	
	
}
