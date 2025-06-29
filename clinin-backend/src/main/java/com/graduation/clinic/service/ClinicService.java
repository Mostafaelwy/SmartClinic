package com.graduation.clinic.service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.AddVisitorResponse;
import com.graduation.clinic.dto.GetClinic;
import com.graduation.clinic.dto.ReserveTime;
import com.graduation.clinic.dto.SetClinic;
import com.graduation.clinic.dto.SlotDto;


import com.graduation.clinic.entity.Clinic;
import com.graduation.clinic.entity.ClinicsVistors;
import com.graduation.clinic.entity.Days;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.entity.Photo;
import com.graduation.clinic.entity.ReservedTime;
import com.graduation.clinic.entity.Slot;
import com.graduation.clinic.exceptions.DuplicateException;
import com.graduation.clinic.exceptions.GenericException;
import com.graduation.clinic.exceptions.NotFoundException;
import com.graduation.clinic.repos.ClinicRepo;
import com.graduation.clinic.repos.ClinicsVisitorsRepo;

import com.graduation.clinic.repos.PhotoRepo;
import com.graduation.clinic.repos.ReservedTimesRepo;
import com.graduation.clinic.repos.SlotRepo;

import jakarta.persistence.criteria.Order;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class ClinicService {

	private final ClinicRepo clinicRepo ;
	private final DoctorService doctorService;
	private final PatientService patientService;
	private final ClinicsVisitorsRepo clinicsVisitorsRepo;
	private final SlotRepo slotRepo;
	private final PhotoRepo photoRepo;
	private final ReservedTimesRepo reservedTimesRepo;

	
	public ClinicService(
			ClinicRepo clinicRepo,
			DoctorService doctorService,
			PatientService patientService,
			ClinicsVisitorsRepo clinicsVisitorsRepo,
			SlotRepo slotRepo,
			PhotoRepo photoRepo,
			ReservedTimesRepo reservedTimesRepo) {
		
		this.clinicRepo = clinicRepo;
		this.doctorService = doctorService;
		this.patientService = patientService;
		this.clinicsVisitorsRepo = clinicsVisitorsRepo;
		this.slotRepo=slotRepo;
		this.photoRepo=photoRepo;
		this.reservedTimesRepo=reservedTimesRepo;
	}


	

	@Transactional(value = TxType.REQUIRES_NEW)
	public List<GetClinic> setClinics(List<SetClinic> request){
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		Doctor doc=(Doctor)auth.getPrincipal();
		for(int i=0;i<request.size();i++) {
			List<Long> photosIds=new ArrayList<>();
			for(int j=0;j<request.get(i).getGellery().size();j++) {
				Long id =photoRepo.save(new Photo( request.get(i).getGellery().get(j))).getId();
				photosIds.add(id);
			}
			Clinic c=new Clinic(request.get(i), photosIds);
			c.setDoctor(doc);
			clinicRepo.save(c);
		}

		return getClinics(doc.getId());
	}
	
	public List<GetClinic> getClinics(Long doctorId){
		List<Clinic> clinicList=clinicRepo.findAllByDoctorId(doctorId);

		List<GetClinic> getClinics=new ArrayList<>();
		for(int i=0;i<clinicList.size();i++) {

			List<Photo> photos=photoRepo.findAllById(clinicList.get(i).getGallery());
			getClinics.add(new GetClinic(clinicList.get(i),photos));

		}
		return getClinics;
	}



	public List<GetClinic> getCurrntDoctorClinics(){
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		Doctor doc=(Doctor)auth.getPrincipal();

		return getClinics(doc.getId());
	}

	@Transactional(value = TxType.REQUIRES_NEW)
	public void deleteClinic(Long clinicId) {
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		Doctor doc=(Doctor)auth.getPrincipal();
		Clinic c= clinicRepo.findByIdAndDoctorId(clinicId, doc.getId()).orElseThrow(()->new NotFoundException("there is no clinic to delete"));
		photoRepo.deleteAllById(c.getGallery());
		clinicRepo.delete(c);
	}
	
	public AddVisitorResponse addVisitor(Long patientId,Long ClinicId) {
		Patient visitor=patientService.findById(patientId);
		Clinic clinic=clinicRepo.findById(ClinicId).orElseThrow(()-> new NotFoundException(" Clinic not found "));
		Long doctorId=clinic.getDoctor().getId();
		ClinicsVistors clinicsVistors=new ClinicsVistors();
	
		clinicsVistors.setClinic(clinic);
		clinicsVistors.setVisitor(visitor);
		clinicsVistors.setVisitationDate(LocalDate.now());
		clinicsVistors.setDoctorId(doctorId);
		return new AddVisitorResponse(clinicsVisitorsRepo.save(clinicsVistors));
	
	}
	
	public Clinic findById(Long id) {
		return clinicRepo.findById(id).orElseThrow(()->new NotFoundException("Clinic not found"));
	}
	
	public Map<DayOfWeek, List<LocalTime>> addWorkingDay(Long clinicId,DayOfWeek workingDay,SlotDto request) {
		Clinic clinic=clinicRepo.findById(clinicId).orElseThrow(()->new NotFoundException("clinic not found"));
		 Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		 Doctor doc=(Doctor)auth.getPrincipal();
		 
		 if(doc.getId()==clinic.getDoctor().getId()) {
			 
			Slot slot=new Slot();
			
			if(slotRepo.findByClinicIdAndDay(clinicId, workingDay).isPresent()) {
			slot.setId(slotRepo.findByClinicIdAndDay(clinicId, workingDay).orElseThrow().getId());
			}
			slot.setDuration(request.getDuration());
			slot.setEndTime(request.getEndTime());
			slot.setStartTime(request.getStartTime());
			slot.setAppointmentInterval(request.getInterval());
			slot.setClinic(clinic);
			slot.setDay(workingDay);
			

			clinic.getWorkingDays().put(workingDay, slot);
			clinicRepo.save(clinic);
			 
		 }else {
			 throw new GenericException("you are not allowed to perform this action.");
		 }
		 return showSlotsPerDay(clinicId,workingDay);
		 

	}
	public Map<DayOfWeek, List<LocalTime>> showSlotsPerDay(Long clinicId,DayOfWeek workingDay) {
		try {

			Clinic clinic=clinicRepo.findById(clinicId).orElseThrow(()->new NotFoundException("clinic not found"));

				Map<DayOfWeek, List<LocalTime>> SlotsPerDay=new HashMap<>();
	
			if(workingDay==null||workingDay.name()=="")
			{
				workingDay=clinic.getWorkingDays().firstKey(); 
			}
			
		
			
				Slot slot =clinic.getWorkingDays().get(workingDay);			

			
			
				Duration DoctotalTimeToday = Duration.between(slot.getStartTime(),slot.getEndTime());

				if (DoctotalTimeToday.isNegative()) {
					DoctotalTimeToday = DoctotalTimeToday.plusHours(24); 
				}
			
				DoctotalTimeToday.toMinutes();
				Duration timeForAppointment =slot.getDuration().plus(slot.getAppointmentInterval());
				timeForAppointment.toMinutes();
				long appointmentsPerDay=DoctotalTimeToday.dividedBy(timeForAppointment);
				List<LocalTime> appointmentsTimes=new ArrayList<>();
				LocalTime start =slot.getStartTime();
			
				for(int i=0;i<appointmentsPerDay;i++) {
					appointmentsTimes.add(start);
					start=start.plus(timeForAppointment);
				}
				SlotsPerDay.put(workingDay, appointmentsTimes);
		
		
				return SlotsPerDay;
		}catch(RuntimeException e) {
			throw new NotFoundException("no slots for :"+workingDay);
			
		}
	}
	
	public Map<LocalTime,Boolean> getAvilableTimes(LocalDate date ,Long ClinicId){
		DayOfWeek day=date.getDayOfWeek();
		
		List<LocalTime> times= showSlotsPerDay(ClinicId, day).get(day);
		List<ReservedTime> r= reservedTimesRepo.findByClinicIdAndDate(ClinicId, date);
		
		
		Map<LocalTime,Boolean> timeAvilability= new HashMap<>();
	
		if(r.isEmpty()) {
			for(int i=0;i<times.size();i++) {
				timeAvilability.put(times.get(i), true);
			}
		}
		else {
			for(int i=0;i<times.size();i++) {
				for(int j=0;j<r.size();j++) {
					if(times.get(i).equals(r.get(j).getTime())) {
						timeAvilability.put(times.get(i),false);
					}
					else {
						timeAvilability.put(times.get(i),true);
					}
				}
			}
		}

	return timeAvilability;	
	}
	
	public LocalDateTime reserveTime(ReserveTime time , Long ClinicId ) {
		Clinic clinic=clinicRepo.findById(ClinicId).orElseThrow(()->new NotFoundException("clinic not Found"));
		ReservedTime reserved= new ReservedTime(time,clinic);
		ReservedTime Rtime= reservedTimesRepo.save(reserved);
		return Rtime.getDate().atTime(Rtime.getTime());
	}
	
	
	
}

