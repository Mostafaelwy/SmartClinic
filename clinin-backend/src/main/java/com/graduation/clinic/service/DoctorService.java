package com.graduation.clinic.service;



import java.time.LocalDate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.graduation.clinic.dto.DoctorBasicDetailes;
import com.graduation.clinic.dto.DoctorBasicDetailesRequest;
import com.graduation.clinic.dto.DoctorStatistics;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.ReservationStatus;
import com.graduation.clinic.entity.SpecialtiesAndServices;
import com.graduation.clinic.exceptions.GenericException;
import com.graduation.clinic.exceptions.NotFoundException;
import com.graduation.clinic.repos.ClinicsVisitorsRepo;
import com.graduation.clinic.repos.DoctorRepo;
import com.graduation.clinic.repos.ReservationRepo;


@Service  
public class DoctorService {

	private final DoctorRepo doctorRepo;
	private final ClinicsVisitorsRepo clinicsVisitorsRepo;
	private final ReservationRepo reservationRepo;



	public DoctorService(DoctorRepo doctorRepo, ClinicsVisitorsRepo clinicsVisitorsRepo,
			ReservationRepo reservationRepo) {
		this.doctorRepo = doctorRepo;
		this.clinicsVisitorsRepo = clinicsVisitorsRepo;
		this.reservationRepo = reservationRepo;
	}
	
	public Doctor findById(Long id) {
		return doctorRepo.findById(id).orElseThrow(()->new NotFoundException("doctor not found."));
	}
	public DoctorBasicDetailes updateDetailes(DoctorBasicDetailesRequest newDoctorData) {
		Doctor oldDoctorData=doctorRepo.findById(newDoctorData.getId()).orElseThrow(()->new NotFoundException("doctor not found"));
		oldDoctorData.setFirstName(newDoctorData.getFirstName());
		oldDoctorData.setSecondName(newDoctorData.getSecondName());
		oldDoctorData.setAge(newDoctorData.getAge());
		oldDoctorData.setExperienceYears(newDoctorData.getExperienceYears());
		oldDoctorData.setPhoto(newDoctorData.getPhoto());
		oldDoctorData.setPhoneNumbers(newDoctorData.getPhoneNumbers());
		
		return new DoctorBasicDetailes(doctorRepo.save(oldDoctorData));
	}
	 public Doctor getDoctor(String username) {
		 return doctorRepo.findByUserName(username).orElseThrow(()->new NotFoundException("user not found"));
	 }
	 
	 public DoctorStatistics calculateStatistics() {
		
		 Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		 Doctor doc=(Doctor) auth.getPrincipal();
		 Long doctorId=doc.getId();
		 
		 
		 Long totalAppointmentsToday=reservationRepo.countByDoctorIdAndStatusAndReservationDate(doctorId, ReservationStatus.ACCEPTED, LocalDate.now());
		 Long totalPatient=clinicsVisitorsRepo.countByDoctorId(doctorId);
		 Long totalPatientToday=clinicsVisitorsRepo.countByDoctorIdAndVisitationDate(doctorId, LocalDate.now());
		 
		 DoctorStatistics doctorStatistics=new DoctorStatistics();
		 doctorStatistics.setTotalPatient(totalPatient);
		 doctorStatistics.setTotalPatientToday(totalPatientToday);
		 doctorStatistics.setTotalAppointmentsToday(totalAppointmentsToday);
		  
		 return doctorStatistics;
	 }

	 

	 

	

	

	
	

	
}
