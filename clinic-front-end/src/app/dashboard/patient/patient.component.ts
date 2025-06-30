import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, RouterOutlet } from '@angular/router';
import { PatientService, PatientProfile } from '../../services/patient.service';

@Component({
    selector: 'app-patient',
    standalone: true,
    imports: [CommonModule, RouterOutlet, RouterModule],
    templateUrl: './patient.component.html',
    styleUrl: './patient.component.scss'
})
export class PatientComponent implements OnInit {
    loading: boolean = true;
    patientProfile: PatientProfile | null = null;
    patientId: number | null = null;

    constructor(private patientService: PatientService) {

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
                    this.loading = false;
                    console.log('Patient profile loaded:', profile);
                },
                error: (error) => {
                    console.error('Error loading patient profile:', error);
                    this.loading = false;
                }
            });
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
        return 'assets/img/doctors-dashboard/profile-06.jpg';
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

    formatAge(age: number): string {
        if (!age) return 'N/A';
        const years = Math.floor(age / 12);
        const months = age % 12;
        return `${years} years ${months} months`;
    }

    get patientAge(): string {
        if (this.patientProfile?.dateOfBirth) {
            const dob = new Date(this.patientProfile.dateOfBirth);
            const age = this.calculateAge(dob);
            return this.formatAge(age);
        }
        return 'N/A';
    }

    private calculateAge(dob: Date): number {
        const diff = Date.now() - dob.getTime();
        const ageDate = new Date(diff);
        return Math.abs(ageDate.getUTCFullYear() - 1970);
    }
} 