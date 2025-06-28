package com.graduation.clinic.service;

import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.graduation.clinic.Specifications.ReservationSpecifications;
import com.graduation.clinic.Specifications.ReviewSpecification;
import com.graduation.clinic.dto.PageProperties;
import com.graduation.clinic.dto.RatingDto;
import com.graduation.clinic.dto.ReviewDto;
import com.graduation.clinic.dto.SetReply;
import com.graduation.clinic.dto.DateInterval;
import com.graduation.clinic.entity.Doctor;
import com.graduation.clinic.entity.Patient;
import com.graduation.clinic.entity.Rating;
import com.graduation.clinic.entity.Reservation;
import com.graduation.clinic.entity.ReservationStatus;
import com.graduation.clinic.exceptions.DuplicateException;
import com.graduation.clinic.exceptions.GenericException;
import com.graduation.clinic.exceptions.NotFoundException;
import com.graduation.clinic.repos.DoctorRepo;
import com.graduation.clinic.repos.RatingRepo;
import com.graduation.clinic.repos.ReservationRepo;

@Service
public class RatingService {

	private final RatingRepo ratingRepo;
	private final PatientService patientService;
	private final DoctorService doctorService;
	private final DoctorRepo doctorRepo;
	private final ReservationRepo reservationRepo;


	
	public RatingService(RatingRepo ratingRepo, PatientService patientService, DoctorService doctorService,DoctorRepo doctorRepo
			,ReservationRepo reservationRepo) {
		this.ratingRepo = ratingRepo;
		this.patientService = patientService;
		this.doctorService = doctorService;
		this.doctorRepo=doctorRepo;
		this.reservationRepo=reservationRepo;
	}

	private boolean isPatientVisitDoctor(Long doctorId,Long patientId) {
		boolean visitor=true;
		List<Reservation> reservations=reservationRepo.findAll(
				Specification.where(ReservationSpecifications.hasDoctorId(doctorId))
							.and(ReservationSpecifications.hasPatientId(patientId))
							.and(ReservationSpecifications.hasStatus(ReservationStatus.COMPLETED)));
		
		if(reservations.isEmpty()) {
			visitor=false;
		}
		return visitor;
		
		
	}

	public ReviewDto rate(RatingDto request,Long doctorId) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Patient patient=(Patient)auth.getPrincipal();
		
		if(isPatientVisitDoctor(doctorId, patient.getId())==true) {
			
			Optional<Rating> isPatientRateBefore=ratingRepo.findByRatedDoctorIdAndRaterId(doctorId, patient.getId());
			Rating rate=new Rating();
			if(isPatientRateBefore.isPresent()) {
			 rate=ratingRepo.findByRatedDoctorIdAndRaterId(doctorId,patient.getId() ).orElseThrow();
			}
				Doctor doctor =doctorService.findById(doctorId);
				patient=patientService.findById(patient.getId());
				
				rate.setRatedDoctor(doctor);
				rate.setRater(patient);
				rate.setRate(request.getRate());
				rate.setCreationDate(LocalDate.now());
				rate.setReview(request.getReview());
				ratingRepo.save(rate);
				int total=0;
				int value=0;
				for(int i=0;i<doctor.getRatings().size();i++) {
					value=doctor.getRatings().get(i).getRate();
					total+=value;
				}
				double totalRating=total/doctor.getRatings().size();
				doctor.setTotalRating(totalRating);
				doctorRepo.save(doctor);
				return new ReviewDto(rate);
		}else {
			throw new GenericException("you are not allowed to review untill visit doctor");
		}
			
		}	
	public ReviewDto readMyRview(Long doctorId) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Patient patient=(Patient)auth.getPrincipal();
		Rating isPatientRevieweBefore=ratingRepo.findByRatedDoctorIdAndRaterId(doctorId, patient.getId()).orElseThrow(()->new NotFoundException("you never review before"));
		return new ReviewDto(isPatientRevieweBefore);
	}
	public Page<ReviewDto> readDoctorReviews(Long doctorId,PageProperties p,DateInterval interval) {
		Pageable page=PageRequest.of(p.getPageNum(),p.getPageSize(),p.getDir(),p.getSortAttripute());
		
		Page<Rating> ratingPage=ratingRepo.findAll(
				Specification.where(ReviewSpecification.hasDoctorId(doctorId))
							.and(ReviewSpecification.hasTimeInterval(interval.getStartDate(), interval.getEndDate())), 
				page);
		return paginateReviewDto(ratingPage);
		
	}
	private ReviewDto convertReviewToDto(Rating review) {
		return new ReviewDto(review);
	}
	private Page<ReviewDto> paginateReviewDto(Page <Rating> reviews){
		Page<ReviewDto> dtos= reviews.map(this::convertReviewToDto);
		return dtos;
	}
	
	public ReviewDto doctorReplyOnReview(SetReply r) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Doctor doc=(Doctor) auth.getPrincipal();
		Rating rate=ratingRepo.findById(r.reviewId).orElseThrow(()-> new NotFoundException("there is not reviews"));
		if(rate.getRatedDoctor().getId()==doc.getId()) {
			rate.setReply(r.getReply());
			return new ReviewDto(ratingRepo.save(rate));
		}
		else{
			throw new GenericException("you are not allowed to reply, it allowed only for reviewed doctor");
		}
	}
}

