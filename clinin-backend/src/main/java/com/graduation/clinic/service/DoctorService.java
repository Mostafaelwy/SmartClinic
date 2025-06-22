package com.graduation.clinic.service;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.graduation.clinic.Specifications.DoctorSpecifications;
import com.graduation.clinic.dto.BasicDetailes;
import com.graduation.clinic.dto.ChangePasswordRequest;
import com.graduation.clinic.dto.DoctorBasicDetailesRequest;
import com.graduation.clinic.dto.DoctorData;
import com.graduation.clinic.dto.DoctorFilteration;
import com.graduation.clinic.dto.DoctorProfileData;
import com.graduation.clinic.dto.DoctorStatistics;
import com.graduation.clinic.dto.GetAwards;
import com.graduation.clinic.dto.GetEducation;
import com.graduation.clinic.dto.GetExperienceDto;
import com.graduation.clinic.dto.GetPhoto;
import com.graduation.clinic.dto.MembershipsDto;
import com.graduation.clinic.dto.PageProperties;
import com.graduation.clinic.dto.SetAwards;
import com.graduation.clinic.dto.SetEducation;
import com.graduation.clinic.dto.SetExperienceRequest;
import com.graduation.clinic.entity.Awards;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Education;
import com.graduation.clinic.entity.Experience;
import com.graduation.clinic.entity.Memberships;
import com.graduation.clinic.entity.Photo;
import com.graduation.clinic.entity.ReservationStatus;
import com.graduation.clinic.entity.Specialties;
import com.graduation.clinic.entity.UsersBaseEntity;
import com.graduation.clinic.exceptions.DuplicateException;
import com.graduation.clinic.exceptions.GenericException;
import com.graduation.clinic.exceptions.NotFoundException;
import com.graduation.clinic.repos.AwardsRepo;
import com.graduation.clinic.repos.BaseUserRepo;
import com.graduation.clinic.repos.ClinicsVisitorsRepo;
import com.graduation.clinic.repos.DoctorRepo;
import com.graduation.clinic.repos.EducationRepo;
import com.graduation.clinic.repos.ExperienceRepo;
import com.graduation.clinic.repos.MembershipRepo;
import com.graduation.clinic.repos.PhotoRepo;
import com.graduation.clinic.repos.ReservationRepo;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;


@Service  
public class DoctorService {

	private final DoctorRepo doctorRepo;
	private final ClinicsVisitorsRepo clinicsVisitorsRepo;
	private final ReservationRepo reservationRepo;
	private final PasswordEncoder passwordEncoder;
	private final BaseUserRepo baseUserRepo;
	private final ExperienceRepo experienceRepo;
	private final EducationRepo educationRepo;
	private final AwardsRepo awardsRepo;
	private final MembershipRepo membershipRepo;
	private final PhotoRepo photoRepo;
	



	public DoctorService(
			PhotoRepo photoRepo,
			DoctorRepo doctorRepo,
			ClinicsVisitorsRepo clinicsVisitorsRepo,
			ReservationRepo reservationRepo,
			PasswordEncoder passwordEncoder,
			BaseUserRepo  baseUserRepo,
			ExperienceRepo experienceRepo,
			EducationRepo educationRepo,
			AwardsRepo awardsRepo,
			MembershipRepo membershipRepo
			
			) {
		this.photoRepo=photoRepo;
		this.baseUserRepo=baseUserRepo;
		this.doctorRepo = doctorRepo;
		this.clinicsVisitorsRepo = clinicsVisitorsRepo;
		this.reservationRepo = reservationRepo;
		this.passwordEncoder=passwordEncoder;
		this.experienceRepo=experienceRepo;
		this.educationRepo=educationRepo;
		this.awardsRepo=awardsRepo;
		this.membershipRepo=membershipRepo;
	}
	
	public Doctor findById(Long id) {
		return doctorRepo.findById(id).orElseThrow(()->new NotFoundException("doctor not found."));
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
	 @Transactional(value = TxType.REQUIRES_NEW)
	 public void ChangePassword(ChangePasswordRequest request) {
		 Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		 Doctor doc=(Doctor)auth.getPrincipal();
		
		 if(this.passwordEncoder.matches(request.getOldPassword(), doc.getPassword())){
			 doc.setPassword(passwordEncoder.encode(request.getNewPassword()));
			 doctorRepo.save(doc);
		 }else {
			 throw new GenericException("old password is not correct");
		 }
		 

		
	 }

	 public Page<DoctorData> doctorSearch(DoctorFilteration filter,PageProperties p) {
		 Pageable page=PageRequest.of(p.getPageNum(), p.getPageSize(),p.getDir(),p.getSortAttripute());
		 Page<Doctor> docPage=doctorRepo.findAll(
				 Specification.where(DoctorSpecifications.hasExperienceYears(filter.getExperienceYears()))
				 			.and(DoctorSpecifications.hasGender(filter.getSex()))
				 			.and(DoctorSpecifications.hasRate(filter.getTotalRating()))
				 			.and(DoctorSpecifications.hasServicePrice(filter.getLowPrice(), filter.getHighPrice()))
				 			.and(DoctorSpecifications.hasSpeciality(filter.getSpeciality()))
				 ,page );
		 
		 return paginateDotorData(docPage);
		 
	 } 
		private DoctorData convertDoctorToDto(Doctor doc) {
			return new DoctorData(doc);
		}
		private Page<DoctorData> paginateDotorData(Page <Doctor> doctors){
			Page<DoctorData> dtos= doctors.map(this::convertDoctorToDto);
			return dtos;
		}
		
	
	 public DoctorProfileData getProfileData() {
		 Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		 Doctor doctor=(Doctor)auth.getPrincipal();
		 Doctor doc=doctorRepo.findById(doctor.getId()).orElseThrow();
		 
		 List<Specialties> specialties=new ArrayList<>();
		 for(int i=0;i<doc.getDoctorSpecilization().size();i++) {
			 Specialties speciality=doc.getDoctorSpecilization().get(i).getSpeciality();
			 specialties.add(speciality);
		 }
		 String name=doc.getFirstName()+doc.getSecondName();
		 String displayName=doc.getDisplayName();
		 Photo p=doc.getProfilePhoto();
		
		 return new DoctorProfileData(name, displayName, specialties, new GetPhoto(p));
	 }
	 
	 public BasicDetailes getBasicDetailes(Long doctorId) {

		 Doctor doc=doctorRepo.findById(doctorId).orElseThrow(()->new NotFoundException("Doctor not found"));
		 List<Memberships> m=membershipRepo.findAllByDoctorId(doctorId);
		 List<MembershipsDto> membershipsDto=new ArrayList<>();
		 for(int i=0;i<m.size();i++) {
			membershipsDto.add(new MembershipsDto(m.get(i)));
		 }
		 
		 return new BasicDetailes(doc.getFirstName(), doc.getSecondName(), doc.getDisplayName(), doc.getDesignation(), doc.getPhoneNumbers(), doc.getUsername(), new GetPhoto(doc.getProfilePhoto()) ,doc.getLanguages(),membershipsDto);
		 
		 
	 }
	 @Transactional(value = TxType.REQUIRES_NEW)
	 public BasicDetailes editBasicDetailes(DoctorBasicDetailesRequest request) {
		 Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		 Doctor doctor=(Doctor)auth.getPrincipal();
		 Doctor doc=doctorRepo.findById(doctor.getId()).orElseThrow();
		 
		 Optional <UsersBaseEntity> user =baseUserRepo.findByUserName(request.getEmailAddress());
		 if(user.isPresent()) {
			 throw new DuplicateException("you are not allowed to use this email 'it is already used'");
		 }
		 else {
			 doc.setFirstName(request.getFirstName());
			 doc.setSecondName(request.getLastName());
			 doc.setDisplayName(request.getDisplayName());
			 doc.setDesignation(request.getDesignation());
			 doc.setPhoneNumbers(request.getPhoneNumbers());
			 doc.setUserName(request.getEmailAddress());
			 doc.setProfilePhoto(new Photo(request.getPhoto()));
			 doc.setLanguages(request.getLangusgaes());
			 
			 for(int i=0;i<request.getMembershipsRequest().size();i++) {
				Memberships m= new Memberships(request.getMembershipsRequest().get(i));
				m.setDoctor(doc);
				membershipRepo.save(m);
				 
				 
			 }
			 
		 }
		 doctorRepo.save(doc);
		 return getBasicDetailes(doc.getId());
	 }
	 public void DeleteMembership(Long id) {
		 Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		 Doctor doctor=(Doctor)auth.getPrincipal();
		 Memberships m=membershipRepo.findByIdAndDoctorId(id, doctor.getId()).orElseThrow(()->new NotFoundException("you don`t have membership to delete"));
		 membershipRepo.delete(m);
	 }
	 public List<GetExperienceDto> getExperience(Long doctorId) {

		 List<Experience> e=experienceRepo.findAllByDoctorId(doctorId);
		 List<GetExperienceDto> ExperienceList=new ArrayList<>();
		 for(int i=0;i<e.size();i++) {
			 GetPhoto p=new GetPhoto(photoRepo.findById(e.get(i).getLogo().getId()).orElseThrow(()-> new NotFoundException("photo not found")));
			 ExperienceList.add(new GetExperienceDto(e.get(i),p));
		 }
		 return ExperienceList;
	 }
	 @Transactional(value = TxType.REQUIRES_NEW)
	 public List<GetExperienceDto> setExperience(List<SetExperienceRequest> request){
		 
		 Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		 Doctor doctor=(Doctor)auth.getPrincipal();
		 Doctor doc=doctorRepo.findById(doctor.getId()).orElseThrow(); 
		 
		 for(int i=0;i<request.size();i++) {
			 Experience e=new Experience(request.get(i));
			 e.setDoctor(doc);
			 experienceRepo.save(e);
		 }

		 return getExperience(doc.getId());
	 }
	 
	 
	 
	 public void deleteExperience(Long ExperienceId) {
		 Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		 Doctor doctor=(Doctor)auth.getPrincipal();
		 Experience e= experienceRepo.findByIdAndDoctorId(ExperienceId, doctor.getId()).orElseThrow(()->new NotFoundException("you do not have experience to delete"));
		 experienceRepo.delete(e);
	 }
	 
	 
	 public List<GetEducation> getEducation(Long DoctorId){

		 List<Education>e=educationRepo.findAllByDoctorId(DoctorId);
		 List<GetEducation> educationList=new ArrayList<>();
		 for(int i=0;i<e.size();i++) {
			 educationList.add(new GetEducation(e.get(i)));
		 }
		 return educationList;
	 }
	 
	 @Transactional(value = TxType.REQUIRES_NEW)
	 public List<GetEducation> setEducation(List<SetEducation> request){
		 
		 Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		 Doctor doctor=(Doctor)auth.getPrincipal();
		 Doctor doc=doctorRepo.findById(doctor.getId()).orElseThrow(); 
		 for(int i=0;i<request.size();i++) {
			 Education e=new Education(request.get(i));
			 e.setDoctor(doc);
			 educationRepo.save(e);
		 }
		 return getEducation(doc.getId());
	 }
	 
	 public void deleteEducation(Long id) {
		 Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		 Doctor doctor=(Doctor)auth.getPrincipal();
		 Education e=educationRepo.findByIdAndDoctorId(id, doctor.getId()).orElseThrow(()->new NotFoundException("you don`t have education to delete"));
		
		 educationRepo.delete(e);
		 
	 }
	 
	 public List<GetAwards> getAwards(Long doctorId){
		 List<Awards>awards =awardsRepo.findAllByDoctorId(doctorId);
		 List<GetAwards> getAwards=new ArrayList<>();
		 for(int i=0;i<awards.size();i++) {
			 getAwards.add(new GetAwards(awards.get(i)));
		 }
		 return getAwards;
	 }
	 
	 @Transactional(value = TxType.REQUIRES_NEW)
	 public List<GetAwards> setAwards(List<SetAwards> request){
		 
		 Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		 Doctor doctor=(Doctor)auth.getPrincipal();
		 Doctor doc=doctorRepo.findById(doctor.getId()).orElseThrow(); 
		 for(int i=0;i<request.size();i++) {
			 Awards e=new Awards(request.get(i));
			 e.setDoctor(doc);
			 awardsRepo.save(e);
		 }
		 return getAwards(doc.getId());
	 }
	 public void deleteAward(Long id) {
		 Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		 Doctor doctor=(Doctor)auth.getPrincipal();
		 Awards a= awardsRepo.findByIdAndDoctorId(id, doctor.getId()).orElseThrow(()->new NotFoundException("you don`t have award to delete"));
		 awardsRepo.delete(a);
	 }

	
}
