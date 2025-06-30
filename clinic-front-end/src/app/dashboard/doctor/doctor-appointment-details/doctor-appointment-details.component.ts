import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { DoctorService, DoctorAppointmentReservationDetails } from '../../../services/doctor/doctor.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '../../../shared/sharedModule';

export interface AppointmentDetailsForm {
  clinicalNotes: string;
  laboratoryTests: string[];
  complaints: string[];
  medications: {
    name: string;
    duration: string;
    instructions: string;
    dosage: string;
  }[];
  advice: string;
  followUp: string;
  previousMedicalHistory: string;
}

@Component({
  selector: 'app-doctor-appointment-details',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    RouterModule
  ],
  templateUrl: './doctor-appointment-details.component.html',
})
export class DoctorAppointmentDetailsComponent implements OnInit {
  appointmentId: string | null = null;
  appointmentDetails: DoctorAppointmentReservationDetails | null = null;
  form: AppointmentDetailsForm = {
    clinicalNotes: '',
    laboratoryTests: [],
    complaints: [],
    medications: [],
    advice: '',
    followUp: '',
    previousMedicalHistory: ''
  };
  newLabTest: string = '';
  newComplaint: string = '';
  newMedication: { name: string; duration: string; instructions: string; dosage: string } = { name: '', duration: '', instructions: '', dosage: '' };
  status: string | null = null;

  constructor(private route: ActivatedRoute, private doctorService: DoctorService) {}

  ngOnInit() {
    this.appointmentId = this.route.snapshot.paramMap.get('appointmentId');
    this.status = this.route.snapshot.queryParamMap.get('status');
    if (this.appointmentId) {
      this.doctorService.getAppointmentReservationDetails(this.appointmentId).subscribe({
        next: (data) => this.appointmentDetails = data
      });
    }
  }

  // Add/Remove for laboratoryTests
  addLaboratoryTest(test: string) {
    if (test && !this.form.laboratoryTests.includes(test)) {
      this.form.laboratoryTests.push(test);
    }
  }
  removeLaboratoryTest(test: string) {
    this.form.laboratoryTests = this.form.laboratoryTests.filter(t => t !== test);
  }

  // Add/Remove for complaints
  addComplaint(complaint: string) {
    if (complaint && !this.form.complaints.includes(complaint)) {
      this.form.complaints.push(complaint);
    }
  }
  removeComplaint(complaint: string) {
    this.form.complaints = this.form.complaints.filter(c => c !== complaint);
  }

  // Add/Remove for medications
  addMedication(med: { name: string; duration: string; instructions: string; dosage: string }) {
    this.form.medications.push({ ...med });
  }
  removeMedication(index: number) {
    this.form.medications.splice(index, 1);
  }

  submitAppointmentDetails() {
    if (!this.appointmentId) return;
    this.doctorService.postAppointmentDetails(this.appointmentId, this.form).subscribe({
      next: (res) => {
        // Optionally show a success message or navigate
        console.log('Appointment details submitted successfully', res);
      },
      error: (err) => {
        // Optionally show an error message
        console.error('Error submitting appointment details', err);
      }
    });
  }
} 