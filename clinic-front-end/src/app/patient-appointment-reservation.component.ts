import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { AuthServiceService } from './services/auth-service.service';
import { FormsModule } from '@angular/forms';
import { NgSelectModule } from '@ng-select/ng-select';
import { ClinicData } from '../types';

@Component({
  selector: 'app-patient-appointment-reservation',
  templateUrl: './patient-appointment-reservation.component.html',
  styleUrls: ['./patient-appointment-reservation.component.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule, NgSelectModule]
})
export class PatientAppointmentReservationComponent implements OnInit {
  step = 1;
  doctorId: string | null = null;

  // Reservation data
  reservationDate: string = '';
  reservationTime: { hour: number, minute: number, second: number, nano: number } = { hour: 0, minute: 0, second: 0, nano: 0 };
  visitType: string = '';
  serviceId: number | null = null;
  clinicId: number | null = null;

  specialties: any[] = [];
  selectedSpecialty: any = null;
  selectedService: any = null;
  selectedSpecialtyId: number | null = null;

  serviceSelectionError: boolean = false;

  clinics: ClinicData[] = [];
  selectedClinic: ClinicData | null = null;

  constructor(private route: ActivatedRoute, private authService: AuthServiceService) {
    this.route.paramMap.subscribe(params => {
      this.doctorId = params.get('doctorId');
    });
  }

  ngOnInit() {
    if (this.doctorId) {
      this.authService.getDoctorSpecialties(this.doctorId).subscribe({
        next: (specialties) => {
          this.specialties = specialties;
          this.selectedSpecialtyId = null;
          this.selectedSpecialty = null;
          this.selectedService = null;
        },
        error: (err) => {
          // Handle error
        }
      });
    }
  }

  // Add properties for form data for each step
  // e.g. selectedSpecialty, selectedType, selectedDateTime, patientInfo, paymentInfo, etc.

  loadClinics() {
    if (this.clinics.length === 0 && this.doctorId) {
      this.authService.getDoctorClinics(this.doctorId).subscribe({
        next: (clinics: ClinicData[]) => {
          this.clinics = clinics;
        },
        error: (err) => {
          // Handle error
        }
      });
      this.selectedClinic = null;
    }
  }

  nextStep() {
    if (this.step === 1) {
      if (!this.selectedService) {
        this.serviceSelectionError = true;
        return;
      } else {
        this.serviceSelectionError = false;
      }
    }
    if (this.step < 4) {
      this.step++;
      if (this.step === 2) {
        this.loadClinics();
      }
    }
  }

  prevStep() {
    if (this.step > 1) {
      this.step--;
    }
  }

  submitReservation() {
    if (!this.clinicId) return;
    const data = {
      reservationDate: this.reservationDate,
      reservationTime: this.reservationTime,
      visitType: this.visitType,
      serviceId: this.selectedService?.id
    };
    this.authService.reserveAppointment(this.clinicId, data).subscribe({
      next: (res) => {
        // Show confirmation, move to confirmation step, etc.
        this.step = 4;
      },
      error: (err) => {
        // Handle error (show message, etc.)
      }
    });
  }

  startNewBooking() {
    this.step = 1;
    // Reset all form data
  }

  onSpecialtyChange() {
    if (this.selectedSpecialtyId != null && Array.isArray(this.specialties)) {
      const found = this.specialties.find(s => s.id === +this.selectedSpecialtyId!);
      this.selectedSpecialty = found ? found : null;
    } else {
      this.selectedSpecialty = null;
    }
    this.selectedService = null;
  }

  onServiceChange(service: any) {
    this.selectedService = service;
    this.serviceId = service.id;
  }

  selectService(service: any) {
    this.selectedService = service;
    this.onServiceChange(service);
  }
}
