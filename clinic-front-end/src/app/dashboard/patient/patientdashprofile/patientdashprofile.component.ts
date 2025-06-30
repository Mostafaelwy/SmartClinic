import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PatientService, PatientProfile, UpdateProfileRequest, PatientPhoto, Address } from '../../../services/patient.service';
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-patientdashprofile',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './patientdashprofile.component.html',
    styleUrl: './patientdashprofile.component.scss'
})
export class PatientdashprofileComponent implements OnInit {
    loading: boolean = true;
    profile: PatientProfile | null = null;
    updateTimeout: any;
    error: string | null = null;

    constructor(private patientService: PatientService) {}

    ngOnInit(): void {
        this.loadProfile();
    }

    loadProfile() {
        this.loading = true;
        this.patientService.getPatientProfile().subscribe({
            next: (profile) => {
                this.profile = { ...profile };
                this.loading = false;
            },
            error: (err) => {
                this.error = err.message || 'Failed to load profile';
                this.loading = false;
            }
        });
    }

    onFieldChange() {
        if (!this.profile) return;
        clearTimeout(this.updateTimeout);
        this.updateTimeout = setTimeout(() => {
            this.saveProfile();
        }, 500); // debounce
    }

    saveProfile() {
        if (!this.profile) return;
        const request: UpdateProfileRequest = { ...this.profile };
        this.patientService.updatePatientProfile(request).subscribe({
            next: (updated) => {
                this.profile = { ...updated };
            },
            error: (err) => {
                this.error = err.message || 'Failed to update profile';
            }
        });
    }

    onPhotoSelected(event: any) {
        const file: File = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = (e: any) => {
                if (this.profile) {
                    this.profile.photo = {
                        type: file.type,
                        id: this.profile.photo?.id || 0,
                        url: e.target.result.split(',')[1] // base64
                    };
                    this.onFieldChange();
                }
            };
            reader.readAsDataURL(file);
        }
    }
} 