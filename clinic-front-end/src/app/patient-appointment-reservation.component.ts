import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthServiceService } from './services/auth-service.service';
import { FormsModule } from '@angular/forms';
import { NgSelectModule } from '@ng-select/ng-select';
import { ClinicData } from '../types';
import { ReservationRequest, ReservationResponse, DoctorBasicData } from '../types';

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

  timeSlots: { [key: string]: boolean } = {};
  selectedDate: string = '';
  minDate: string = '';
  noSlotsMessage: string = '';
  selectedTimeSlot: string | null = null;

  morningSlots: string[] = [];
  afternoonSlots: string[] = [];
  eveningSlots: string[] = [];

  reservationResponse: ReservationResponse | null = null;
  doctorBasicData: DoctorBasicData | null = null;

  constructor(private route: ActivatedRoute, private authService: AuthServiceService, private router: Router) {
    this.route.paramMap.subscribe(params => {
      this.doctorId = params.get('doctorId');
    });
    // Initialize selectedDate and minDate to today
    const today = new Date();
    this.selectedDate = today.toISOString().slice(0, 10); // YYYY-MM-DD
    this.minDate = this.selectedDate;
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
      this.authService.getDoctorBasicData(this.doctorId).subscribe({
        next: (data) => {
          this.doctorBasicData = data;
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
      if (this.step === 3 && this.selectedClinic) {
        this.onDaySelect(this.selectedDate);
      }
    }
  }

  prevStep() {
    if (this.step > 1) {
      this.step--;
    }
  }

  submitReservation() {
    if (!this.clinicId || !this.selectedTimeSlot || !this.selectedService) return;
    // Parse selectedTimeSlot (HH:mm) to hour and minute
    const [hourStr, minuteStr] = this.selectedTimeSlot.split(':');
    const reservationTime = `${hourStr.padStart(2, '0')}:${minuteStr.padStart(2, '0')}`;
    const data: ReservationRequest = {
      reservationDate: this.selectedDate,
      reservationTime,
      visitType: 'GENERAL', // or use this.visitType if set elsewhere
      serviceId: this.selectedService.id
    };
    this.authService.reserveAppointment(this.clinicId, data).subscribe({
      next: (res: ReservationResponse) => {
        this.reservationResponse = res;
        this.step = 4;
      },
      error: (err) => {
        // Handle error (show message, etc.)
      }
    });
  }

  startNewBooking() {
    this.router.navigate([this.router.url]).then(() => {
      window.location.reload();
    });
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

  onDaySelect(date: string) {
    this.selectedDate = date;
    if (!this.selectedClinic) return;
    const clinicId = this.selectedClinic.id;
    this.authService.getClinicTimeAvailability(clinicId, date)
      .subscribe({
        next: (response) => {
          if ('success' in response && response['success'] === false) {
            this.noSlotsMessage = String(response['message'] || 'No slots available');
            this.timeSlots = {};
          } else {
            this.noSlotsMessage = '';
            this.timeSlots = response;
            this.groupSlots();
          }
          this.selectedTimeSlot = null;
        },
        error: (err) => {
          this.noSlotsMessage = 'No slots available';
          this.timeSlots = {};
          this.selectedTimeSlot = null;
        }
      });
  }

  onDateChange(event: any) {
    const date = event.target.value;
    this.selectedDate = date;
    if (this.selectedClinic) {
      this.onDaySelect(date);
    }
  }

  private groupSlots() {
    this.morningSlots = [];
    this.afternoonSlots = [];
    this.eveningSlots = [];
    Object.keys(this.timeSlots).forEach(slot => {
      // Parse hour for grouping, include all slots regardless of value
      const hour = parseInt(slot.split(':')[0], 10);
      if (hour >= 5 && hour < 12) {
        this.morningSlots.push(slot);
      } else if (hour >= 12 && hour < 17) {
        this.afternoonSlots.push(slot);
      } else if (hour >= 17 && hour <= 22) {
        this.eveningSlots.push(slot);
      }
    });
  }
}
