import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthServiceService } from './services/auth-service.service';
import { DoctorBasicData } from '../types';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgSelectModule } from '@ng-select/ng-select';
import { DoctorService, DoctorEducation, DoctorExperience, DoctorSpecialty, DoctorClinic, DoctorReview, DoctorReviewsResponse } from './services/doctor/doctor.service';

@Component({
  selector: 'app-doctor-profile',
  standalone: true,
  templateUrl: './doctor-profile.component.html',
  imports: [CommonModule, FormsModule, NgSelectModule],
  styleUrls: ['./doctor-profile.component.scss']
})
export class DoctorProfileComponent implements OnInit {
  doctorData: DoctorBasicData | null = null;
  education: DoctorEducation[] = [];
  experience: DoctorExperience[] = [];
  specialties: DoctorSpecialty[] = [];
  clinics: DoctorClinic[] = [];
  reviews: DoctorReview[] = [];
  reviewsPage = 0;
  reviewsPageSize = 3;
  reviewsTotalPages = 0;
  reviewsLoading = false;
  newReviewRate: number = 0;
  newReviewText: string = '';
  submittingReview = false;
  reviewErrorMessage: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private authService: AuthServiceService,
    private router: Router,
    private doctorService: DoctorService
  ) {}

  ngOnInit() {
    const doctorId = this.route.snapshot.paramMap.get('id');
    if (doctorId) {
      this.authService.getDoctorBasicData(doctorId).subscribe({
        next: (data) => this.doctorData = data
      });
      this.doctorService.getDoctorEducation(doctorId).subscribe({
        next: (data) => this.education = data || []
      });
      this.doctorService.getDoctorExperience(doctorId).subscribe({
        next: (data) => this.experience = data || []
      });
      this.doctorService.getDoctorSpecialties(doctorId).subscribe({
        next: (data) => this.specialties = data || []
      });
      this.doctorService.getDoctorClinics(doctorId).subscribe({
        next: (data) => this.clinics = data || []
      });
      this.fetchReviews(doctorId);
    }
  }

  onBookAppointment() {
    const doctorId = this.route.snapshot.paramMap.get('id');
    if (doctorId) {
      this.router.navigate(['/reserve-appointment', doctorId]);
    }
  }

  get hasNoServices(): boolean {
    return this.specialties.length === 0 || this.specialties.every(s => !s.services || s.services.length === 0);
  }

  fetchReviews(doctorId: string) {
    this.reviewsLoading = true;
    this.doctorService.getDoctorReviews(doctorId, this.reviewsPage, this.reviewsPageSize).subscribe({
      next: (res) => {
        this.reviews = [...this.reviews, ...(res.content || [])];
        this.reviewsTotalPages = res.totalPages;
        this.reviewsLoading = false;
      },
      error: () => { this.reviewsLoading = false; }
    });
  }

  showMoreReviews() {
    const doctorId = this.route.snapshot.paramMap.get('id');
    if (doctorId && this.reviewsPage + 1 < this.reviewsTotalPages) {
      this.reviewsPage++;
      this.fetchReviews(doctorId);
    }
  }

  submitReview() {
    const doctorId = this.route.snapshot.paramMap.get('id');
    if (!doctorId || !this.newReviewRate || !this.newReviewText.trim()) return;
    this.submittingReview = true;
    this.reviewErrorMessage = null;
    this.doctorService.postDoctorReview(doctorId, this.newReviewRate, this.newReviewText.trim()).subscribe({
      next: () => {
        this.newReviewRate = 0;
        this.newReviewText = '';
        this.reviews = [];
        this.reviewsPage = 0;
        this.fetchReviews(doctorId);
        this.submittingReview = false;
        this.reviewErrorMessage = null;
      },
      error: (err) => {
        this.submittingReview = false;
        if (err?.error?.message) {
          this.reviewErrorMessage = err.error.message;
        } else {
          this.reviewErrorMessage = 'Failed to submit review.';
        }
      }
    });
  }
} 