import { Component, OnInit } from '@angular/core';
import { DoctorSearchService } from './doctor-search.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Specialties } from '../../../types';
import { Router } from '@angular/router';

@Component({
  selector: 'app-doctor-search',
  standalone: true,
  imports: [FormsModule, CommonModule], 
  templateUrl: './doctor-search.component.html',
  styleUrls: ['./doctor-search.component.scss']
})
export class DoctorSearchComponent implements OnInit { 
  doctors: any[] = [];
  filter = {
    sex: null,
    totalRating: null,
    lowPrice: null,
    highPrice: null,
    experienceYears: null,
    speciality: null,
    name: ''
  };
  paging = {
    pageNum: 0,
    sortAttripute: null,
    dir: 'ASC',
    pageSize: 10
  };
  totalPages: number | null = null;
  specialties = Object.values(Specialties);
  showFilters = false;

  constructor(private doctorSearchService: DoctorSearchService, private router: Router) {}

  ngOnInit() {
    this.fetchDoctors();
  }

  toggleFilters() {
    this.showFilters = !this.showFilters;
  }

  onFilter() {
    this.paging.pageNum = 0;
    this.fetchDoctors();
  }

  clearFilters() {
    this.filter = {
      sex: null,
      totalRating: null,
      lowPrice: null,
      highPrice: null,
      experienceYears: null,
      speciality: null,
      name: ''
    };
    this.paging.pageNum = 0;
    this.fetchDoctors();
    this.showFilters = false;
  }

  private fetchDoctors() {
    this.doctorSearchService.getDoctors(this.filter, this.paging).subscribe(res => {
      this.doctors = res.content || res;
      this.totalPages = res.totalPages || null;
    });
  }

  loadMore() {
    this.paging.pageNum++;
    this.doctorSearchService.getDoctors(this.filter, this.paging).subscribe(res => {
      const newDoctors = res.content || res;
      this.doctors = this.doctors.concat(newDoctors);
      this.totalPages = res.totalPages || null;
    });
  }

  get hasMorePages() {
    return this.totalPages === null ? true : this.paging.pageNum < this.totalPages - 1;
  }

  bookAppointment(doctor: any) {
    this.router.navigate(['/reserve-appointment', doctor.id || doctor.doctorId]);
  }

  goToDoctorProfile(doctor: any) {
    this.router.navigate(['/doctor', doctor.id || doctor.doctorId]);
  }
} 