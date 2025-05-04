package com.graduation.clinic.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.AddVisitorResponse;
import com.graduation.clinic.dto.ClinicData;
import com.graduation.clinic.dto.ClinicDto;
import com.graduation.clinic.dto.CreateClinicRequest;
import com.graduation.clinic.dto.CreateClinicResponse;
import com.graduation.clinic.dto.SlotDto;
import com.graduation.clinic.dto.UpdateClinicDetailesRequest;

import com.graduation.clinic.entity.Clinic;
import com.graduation.clinic.entity.ClinicsVistors;
import com.graduation.clinic.entity.Days;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.entity.Slot;
import com.graduation.clinic.exceptions.DuplicateException;
import com.graduation.clinic.exceptions.GenericException;
import com.graduation.clinic.exceptions.NotFoundException;
import com.graduation.clinic.repos.ClinicRepo;
import com.graduation.clinic.repos.ClinicsVisitorsRepo;

@Service
public class ClinicService {

	private final ClinicRepo clinicRepo ;
	private final DoctorService doctorService;
	private final PatientService patientService;
	private final ClinicsVisitorsRepo clinicsVisitorsRepo;

	
	public ClinicService(
			ClinicRepo clinicRepo,
			DoctorService doctorService,
			PatientService patientService,
			ClinicsVisitorsRepo clinicsVisitorsRepo
			) {
		
		this.clinicRepo = clinicRepo;
		this.doctorService = doctorService;
		this.patientService = patientService;
		this.clinicsVisitorsRepo = clinicsVisitorsRepo;
	}

	public CreateClinicResponse createClinic(Long doctorId,CreateClinicRequest request) {
		Doctor doc=doctorService.findById(doctorId);
		Clinic clinic =new Clinic(request,doc);
		return new CreateClinicResponse(clinicRepo.save(clinic));
	}
	
	public List<ClinicDto> getAllClinics(){
		
		List <Clinic> clinics=clinicRepo.findAll();
		List <ClinicDto> clinicDtoList=new ArrayList<>();
		if(!clinics.isEmpty()) {
			for(int i=0 ; i < clinics.size() ;i++) {
				clinicDtoList.add(new ClinicDto(clinics.get(i)));
			}
			return clinicDtoList;
		}
		return clinicDtoList;
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
	
	public ClinicData updateClinicDetailesRequest(UpdateClinicDetailesRequest detailes) {
		Clinic clinic =clinicRepo.findById(detailes.getId()).orElseThrow(()->new NotFoundException("clinic not found"));
	

		clinic.setClinicName(detailes.getClinicName());	
		clinic.setOpeningTime(detailes.getOpeningTime());
		clinic.setClosingTime(detailes.getClosingTime());
		clinic.setPhoneNumbers(detailes.getPhoneNumbers());
		clinic.setWorkingDays(detailes.getWorkingDays());
		clinic.setLocation(detailes.getLocation());
		clinic.setLogo(detailes.getLogo());
		
		return  new ClinicData(clinicRepo.save(clinic));
	}
	
	public Clinic findById(Long id) {
		return clinicRepo.findById(id).orElseThrow(()->new NotFoundException("Clinic not found"));
	}
	
	public List<ClinicData> findDoctorClinics(Long id){
		List<Clinic> clinics=clinicRepo.findByDoctorId(id);
		List<ClinicData> dtos=new ArrayList<>();
		for(int i=0;i<clinics.size();i++) {
			dtos.add(new ClinicData(clinics.get(i)));
		}
		return dtos;
	}
	public Map<Days, List<LocalTime>> addWorkingDay(Long clinicId,Days workingDay,SlotDto request) {
		Clinic clinic=clinicRepo.findById(clinicId).orElseThrow(()->new NotFoundException("clinic not found"));
		 Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		 Doctor doc=(Doctor)auth.getPrincipal();
		 if(doc.getId()==clinic.getDoctor().getId()) {
			Slot slot=new Slot();
			slot.setClinic(clinic);
			slot.setDuration(request.getDuration());
			slot.setEndTime(request.getEndTime());
			slot.setStartTime(request.getStartTime());
			slot.setAppointmentInterval(request.getInterval());
			slot.setDay(workingDay);
			Map<Days,Slot> newWorkingtimes=clinic.getWorkingDays();
			newWorkingtimes.put(workingDay, slot);
			clinic.setWorkingDays(newWorkingtimes);
			clinicRepo.save(clinic);
			 
		 }else {
			 throw new GenericException("you are not allowed to perform this action.");
		 }
		 return showSlotsPerDay(clinicId);
		 

	}
	public Map<Days, List<LocalTime>> showSlotsPerDay(Long clinicId) {

		Clinic clinic=clinicRepo.findById(clinicId).orElseThrow(()->new NotFoundException("clinic not found"));

		Map<Days, List<LocalTime>> SlotsPerDay=new HashMap<>();
		for(Map.Entry<Days,Slot> entry : clinic.getWorkingDays().entrySet()) {
			
			Days day=entry.getKey();
		
			Slot slot =entry.getValue();
			
			
			Duration DoctotalTimeToday = Duration.between(slot.getStartTime(),slot.getEndTime());

			if (DoctotalTimeToday.isNegative()) {
			    DoctotalTimeToday = DoctotalTimeToday.plusHours(24); // Adjust for crossing midnight
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
			SlotsPerDay.put(day, appointmentsTimes);
		}
		
		return SlotsPerDay;
	}
	
	
	
}

