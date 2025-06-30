import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppointmentService, Appointment, AppointmentResponse } from '../../../services/appointment.service';
import { PrescriptionService, Prescription, PrescriptionResponse } from '../../../services/prescription.service';
import { PatientService, PatientProfile } from '../../../services/patient.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-patientdashhome',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './patientdashhome.component.html',
    styleUrl: './patientdashhome.component.scss'
})
export class PatientdashhomeComponent implements OnInit {
    loading: boolean = false;
    upcomingAppointments: Appointment[] = [];
    totalUpcomingAppointments: number = 0;

    // Patient profile data
    patientProfile: PatientProfile | null = null;
    patientId: number | null = null;

    // All appointments for reports
    allAppointments: Appointment[] = [];
    totalAppointments: number = 0;

    // Prescriptions
    prescriptions: Prescription[] = [];
    totalPrescriptions: number = 0;

    // Active tab for reports
    activeReportTab: string = 'appointments';

    constructor(
        private appointmentService: AppointmentService,
        private prescriptionService: PrescriptionService,
        private patientService: PatientService,
        private router: Router
    ) {

    }

    ngOnInit(): void {
        this.loadPatientProfile();
    }

    loadPatientProfile(): void {
        this.loading = true;
        this.patientService.getPatientProfile()
            .subscribe({
                next: (profile: PatientProfile) => {
                    this.patientProfile = profile;
                    this.patientId = profile.patientId;
                    console.log('Patient profile loaded:', profile);

                    // Load other data after getting patient ID
                    this.loadUpcomingAppointments();
                    this.loadAllAppointments();
                    this.loadPrescriptions();
                },
                error: (error) => {
                    console.error('Error loading patient profile:', error);
                    this.loading = false;
                }
            });
    }

    loadUpcomingAppointments(): void {
        this.appointmentService.getPatientAppointments(0, 10)
            .subscribe({
                next: (response: AppointmentResponse) => {
                    this.upcomingAppointments = response.content.filter(appointment =>
                        appointment.status === 'PENDING'
                    );
                    this.totalUpcomingAppointments = this.upcomingAppointments.length;
                    console.log('Upcoming appointments loaded:', this.upcomingAppointments.length);
                },
                error: (error) => {
                    console.error('Error loading upcoming appointments:', error);
                    this.upcomingAppointments = [];
                    this.totalUpcomingAppointments = 0;
                }
            });
    }

    loadAllAppointments(): void {
        this.appointmentService.getPatientAppointments(0, 50) // Get more appointments for reports
            .subscribe({
                next: (response: AppointmentResponse) => {
                    this.allAppointments = response.content;
                    this.totalAppointments = response.totalElements;
                    console.log('All appointments loaded:', this.allAppointments.length);
                },
                error: (error) => {
                    console.error('Error loading all appointments:', error);
                    this.allAppointments = [];
                    this.totalAppointments = 0;
                }
            });
    }

    loadPrescriptions(): void {
        if (!this.patientId) {
            console.error('Patient ID not available for prescriptions');
            return;
        }

        this.prescriptionService.getPatientPrescriptions(this.patientId, 0, 50)
            .subscribe({
                next: (response: PrescriptionResponse) => {
                    this.prescriptions = response.content;
                    this.totalPrescriptions = response.totalElements;
                    this.loading = false; // Set loading to false after all data is loaded
                    console.log('Prescriptions loaded:', this.prescriptions.length);
                },
                error: (error) => {
                    console.error('Error loading prescriptions:', error);
                    this.prescriptions = [];
                    this.totalPrescriptions = 0;
                    this.loading = false; // Set loading to false even on error
                }
            });
    }

    onReportTabChange(tab: string): void {
        this.activeReportTab = tab;
    }

    formatAppointmentTime(appointmentTime: any): string {
        if (!appointmentTime) return '';

        const hour = appointmentTime.hour || 0;
        const minute = appointmentTime.minute || 0;
        const ampm = hour >= 12 ? 'PM' : 'AM';
        const displayHour = hour % 12 || 12;
        const displayMinute = minute.toString().padStart(2, '0');

        return `${displayHour}:${displayMinute} ${ampm}`;
    }

    formatAppointmentDate(dateString: string): string {
        if (!dateString) return '';

        const date = new Date(dateString);
        return date.toLocaleDateString('en-US', {
            day: 'numeric',
            month: 'short',
            year: 'numeric'
        });
    }

    formatPrescriptionDate(dateString: string): string {
        if (!dateString) return '';

        const date = new Date(dateString);
        return date.toLocaleDateString('en-US', {
            day: 'numeric',
            month: 'short',
            year: 'numeric'
        });
    }

    getDoctorImageUrl(doctorPhoto: any): string {
        if (doctorPhoto?.url) {
            // Check if it's already a data URL
            if (doctorPhoto.url.startsWith('data:')) {
                return doctorPhoto.url;
            }
            // If it's a valid base64 string, convert to data URL
            if (doctorPhoto.url && this.isValidBase64(doctorPhoto.url)) {
                const mimeType = doctorPhoto.type || 'image/jpeg';
                return `data:${mimeType};base64,${doctorPhoto.url}`;
            }
        }
        return 'assets/img/doctors/doctor-thumb-01.jpg';
    }

    getPatientImageUrl(patientPhoto: any): string {
        if (patientPhoto?.url) {
            // Check if it's already a data URL
            if (patientPhoto.url.startsWith('data:')) {
                return patientPhoto.url;
            }
            // If it's a valid base64 string, convert to data URL
            if (patientPhoto.url && this.isValidBase64(patientPhoto.url)) {
                const mimeType = patientPhoto.type || 'image/jpeg';
                return `data:${mimeType};base64,${patientPhoto.url}`;
            }
        }
        return 'assets/img/patients/patient-thumb-01.jpg';
    }

    private isValidBase64(str: string): boolean {
        try {
            // Check if string contains only valid base64 characters
            const base64Regex = /^[A-Za-z0-9+/]*={0,2}$/;
            return base64Regex.test(str) && str.length > 0;
        } catch {
            return false;
        }
    }

    getAppointmentStatusClass(status: string): string {
        switch (status.toUpperCase()) {
            case 'PENDING':
                return 'badge-soft-warning';
            case 'ACCEPTED':
                return 'badge-soft-purple';
            case 'REJECTED':
                return 'badge-soft-danger';
            case 'COMPLETED':
                return 'badge-soft-success';
            default:
                return 'badge-soft-secondary';
        }
    }

    getAppointmentStatusText(status: string): string {
        switch (status.toUpperCase()) {
            case 'PENDING':
                return 'Pending';
            case 'ACCEPTED':
                return 'Accepted';
            case 'REJECTED':
                return 'Rejected';
            case 'COMPLETED':
                return 'Completed';
            default:
                return status;
        }
    }

    getVisitTypeIcon(visitType: string): string {
        switch (visitType?.toUpperCase()) {
            case 'VIDEO_CALL':
                return 'isax-video5';
            case 'AUDIO_CALL':
                return 'isax-call5';
            case 'CHAT':
                return 'isax-messages-25';
            case 'DIRECT':
            case 'GENERAL':
            case 'CONSULTATION':
            case 'FOLLOW_UP':
                return 'isax-hospital5';
            default:
                return 'isax-hospital5';
        }
    }

    goToDoctorSearch(): void {
        this.router.navigate(['/doctor-search']);
    }
} 