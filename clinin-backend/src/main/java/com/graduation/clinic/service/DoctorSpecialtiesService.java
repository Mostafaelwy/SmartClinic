package com.graduation.clinic.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.graduation.clinic.dto.AddSpecialityRequest;
import com.graduation.clinic.dto.AddSpecialityServiceRequest;
import com.graduation.clinic.dto.SpecialityServiceDto;
import com.graduation.clinic.dto.SpecialtiesAndServicesDto;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.SpecialityServices;
import com.graduation.clinic.entity.SpecialtiesAndServices;
import com.graduation.clinic.exceptions.DuplicateException;
import com.graduation.clinic.exceptions.GenericException;
import com.graduation.clinic.exceptions.NotFoundException;
import com.graduation.clinic.repos.DoctorRepo;
import com.graduation.clinic.repos.SpecialityServiceRepo;
import com.graduation.clinic.repos.SpecialtiesAndServicesRepo;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
@Service
public class DoctorSpecialtiesService {

	private final SpecialtiesAndServicesRepo specialtiesAndServicesRepo;
	private final SpecialityServiceRepo specialityServiceRepo;

	private final DoctorRepo doctorRepo;
	public DoctorSpecialtiesService(
			SpecialtiesAndServicesRepo specialtiesAndServicesRepo,
			DoctorRepo doctorRepo,
			SpecialityServiceRepo specialityServiceRepo
			) {
		this.specialtiesAndServicesRepo = specialtiesAndServicesRepo;
		this.doctorRepo=doctorRepo;
		this.specialityServiceRepo=specialityServiceRepo;
	}
	public boolean compareSpecialityServicesWithoutId(SpecialityServices obj1, SpecialityServices obj2) {
	    return new EqualsBuilder()
	            .append(obj1.getServiceType().name(), obj2.getServiceType().name())
	            .append(obj1.getPrice(), obj2.getPrice())
	            .append(obj1.getSpeciality(), obj2.getSpeciality())
	            
	            // Add all fields except ID
	            .isEquals();
	}

	@Transactional(value = TxType.REQUIRES_NEW)
	 public SpecialtiesAndServicesDto addSpeciality(AddSpecialityRequest request) {
		 
		 Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		 Doctor doc=doctorRepo.findById(((Doctor) auth.getPrincipal()).getId()).orElseThrow();
		   // entityManager.getEntityManagerFactory().getCache().evict(Speciality.class);

		 for(int i=0;i<doc.getDoctorSpecilization().size();i++) {
			 if(request.getSpeciality()==doc.getDoctorSpecilization().get(i).getSpeciality()) {
				 throw  new DuplicateException("duplicate specilization");
			 }
		 }
		 
		 SpecialtiesAndServices speciality=new SpecialtiesAndServices();
		 if (request.getId()!=null){
			 speciality = specialtiesAndServicesRepo.findById(request.getId()).orElseThrow();
		 }
		 speciality.setDoctor(doc);
		 speciality.setSpeciality(request.getSpeciality());
		 
		 List <SpecialityServices> SpecServices=new ArrayList<>();
		 for(int i=0 ;i<(request.getServices()).size();i++) {
			 SpecialityServices service=new SpecialityServices();
			 if (request.getServices().get(i).getId() != null) {
		 		service = specialityServiceRepo.findById(request.getServices().get(i).getId()).orElseThrow(()-> new NotFoundException("service not found"));
			 }
			 service.setId(request.getServices().get(i).getId());
			 service.setSpeciality(speciality);
			 service.setServiceType(request.getServices().get(i).getServiceType());
			 service.setPrice(request.getServices().get(i).getPrice());
			 service.setHint(request.getServices().get(i).getHint());
			 SpecServices.add(service);
		 }
		 speciality.setServices(SpecServices);
		 
		 return new SpecialtiesAndServicesDto(specialtiesAndServicesRepo.save(speciality));
	 }
	 
	 
	 public List<SpecialtiesAndServicesDto> findSpecialties(Long doctorId){
		 
		 List<SpecialtiesAndServices>  list=specialtiesAndServicesRepo.findByDoctorId(doctorId);
		 List<SpecialtiesAndServicesDto> listDto=new ArrayList<>();
		 for(int i=0;i<list.size();i++) {
			 listDto.add(new SpecialtiesAndServicesDto(list.get(i)));
		 }
		 return listDto;
	 }
	public List<SpecialtiesAndServicesDto> findSpecialties(){
		Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		Doctor doc=doctorRepo.findById(((Doctor) auth.getPrincipal()).getId()).orElseThrow();

		return findSpecialties(doc.getId());
	}
	 
	 
	 
	 @Transactional(value = TxType.REQUIRES_NEW)
	 public SpecialityServiceDto addServicetoSpeciality(Long specialityId,AddSpecialityServiceRequest request) {
		 SpecialtiesAndServices speciality=specialtiesAndServicesRepo.findById(specialityId).orElseThrow(()-> new NotFoundException("speciality not found"));
		 
		 Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		 Doctor doc=(Doctor) auth.getPrincipal();
		 
		 if(speciality.getDoctor().getId()==doc.getId()) {
			 
			 SpecialityServices service=new SpecialityServices();
			 service.setServiceType(request.getServiceType());
			 service.setPrice(request.getPrice());
			 service.setSpeciality(speciality);
			 service.setHint(request.getHint());
			 
			 for(int i=0;i<speciality.getServices().size();i++) {
				 if(compareSpecialityServicesWithoutId(service, speciality.getServices().get(i))) {
					 throw new DuplicateException("duplicate service");
				 }
			 }
			 
			 List <SpecialityServices> SpecServices=speciality.getServices();
			 SpecServices.add(service);
			 speciality.setServices(SpecServices);
			 
			 return new SpecialityServiceDto(specialtiesAndServicesRepo.save(speciality).getServices().getLast());
			  
		 }else {
			 throw new GenericException("this speciality is not belongs to you");
		 }
		 
	 }
	 
	 @Transactional(value = TxType.REQUIRES_NEW)
	 public void deleteSpeciality(Long specialityId) {
		 Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		 Doctor doc=(Doctor) auth.getPrincipal();
		 
		 SpecialtiesAndServices sas = specialtiesAndServicesRepo.findById(specialityId).orElseThrow(()-> new NotFoundException("speciality not found"));
		 if(sas.getDoctor().getId()==doc.getId()) {
			 specialtiesAndServicesRepo.delete(sas); 
		 }else {
			 throw new GenericException("you are not allowed to delete this speciality");
		 }
	 
	 }
	 @Transactional(value = TxType.REQUIRES_NEW)
	 public void deleteSpecialityService(Long ServiceId) {
		 Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		 Doctor doc=(Doctor) auth.getPrincipal();
		 SpecialityServices service=specialityServiceRepo.findById(ServiceId).orElseThrow(()-> new NotFoundException("service not found"));
		 
		 if(service.getSpeciality().getDoctor().getId()==doc.getId()) {
			 specialityServiceRepo.delete(service);
		 }else {
			 throw new GenericException("you are not allowed to delete this");
		 }
	 }
	 
}
