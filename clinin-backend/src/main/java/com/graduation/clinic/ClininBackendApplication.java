package com.graduation.clinic;

import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.repos.BaseUserRepo;
import com.graduation.clinic.repos.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode;



@SpringBootApplication
//@EnableSpringDataWebSupport(pageSerializationMode =PageSerializationMode.VIA_DTO) 
public class ClininBackendApplication implements CommandLineRunner{

	@Autowired
	BaseUserRepo userRepo;

	@Autowired
	DoctorRepo doctorRepo;

	public static void main(String[] args)  {
		SpringApplication.run(ClininBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
//		Doctor doctor = new Doctor();
//
//		doctor.setUserName("mos@mos.com");
//		doctor.setPassword("12345678");
//		userRepo.save(doctor);
//		System.out.println(userRepo.findAll());
	}

}
