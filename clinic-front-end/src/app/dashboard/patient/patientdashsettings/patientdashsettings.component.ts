import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PatientService, PatientProfile, UpdateProfileRequest, ChangePasswordRequest, ChangePasswordResponse } from '../../../services/patient.service';

@Component({
    selector: 'app-patientdashsettings',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './patientdashsettings.component.html',
    styleUrl: './patientdashsettings.component.scss'
})
export class PatientdashsettingsComponent implements OnInit {
    loading: boolean = false;
    patientProfile: PatientProfile | null = null;
    isEditing: boolean = false;
    activeTab: string = 'profile';

    // Form data
    formData = {
        firstName: '',
        lastName: '',
        dateOfBirth: '',
        phoneNumbers: [''],
        emailAddress: '',
        groub: '',
        address: {
            country: '',
            state: '',
            city: '',
            street: ''
        }
    };

    // Password form data
    passwordData = {
        currentPassword: '',
        newPassword: '',
        confirmPassword: ''
    };

    // States
    changingPassword: boolean = false;
    updatingProfile: boolean = false;
    passwordError: string = '';
    passwordSuccess: string = '';
    profileError: string = '';
    profileSuccess: string = '';

    // Blood group options
    bloodGroups = ['A', 'B', 'AB', 'O'];

    constructor(private patientService: PatientService) {

    }

    ngOnInit(): void {
        this.loadPatientProfile();
    }

    loadPatientProfile(): void {
        this.loading = true;
        this.patientService.getPatientBasicData()
            .subscribe({
                next: (profile: PatientProfile) => {
                    this.patientProfile = profile;
                    this.initializeFormData();
                    this.loading = false;
                    console.log('Patient basic data loaded for settings:', profile);
                },
                error: (error) => {
                    console.error('Error loading patient basic data:', error);
                    this.loading = false;
                }
            });
    }

    initializeFormData(): void {
        if (this.patientProfile) {
            this.formData = {
                firstName: this.patientProfile.firstName || '',
                lastName: this.patientProfile.lastName || '',
                dateOfBirth: this.patientProfile.dateOfBirth || '',
                phoneNumbers: this.patientProfile.phoneNumbers && this.patientProfile.phoneNumbers.length > 0
                    ? [...this.patientProfile.phoneNumbers]
                    : [''],
                emailAddress: this.patientProfile.emailAddress || '',
                groub: this.patientProfile.groub || '',
                address: {
                    country: this.patientProfile.address?.country || '',
                    state: this.patientProfile.address?.state || '',
                    city: this.patientProfile.address?.city || '',
                    street: this.patientProfile.address?.street || ''
                }
            };
        }
    }

    onTabChange(tab: string): void {
        this.activeTab = tab;
        // Clear messages when switching tabs
        this.passwordError = '';
        this.passwordSuccess = '';
        this.profileError = '';
        this.profileSuccess = '';
    }

    toggleEdit(): void {
        this.isEditing = !this.isEditing;
        if (!this.isEditing) {
            // Reset form data when canceling edit
            this.initializeFormData();
        }
    }

    addPhoneNumber(): void {
        this.formData.phoneNumbers.push('');
    }

    removePhoneNumber(index: number): void {
        if (this.formData.phoneNumbers.length > 1) {
            this.formData.phoneNumbers.splice(index, 1);
        }
    }

    validateProfileForm(): boolean {
        this.profileError = '';
        this.profileSuccess = '';

        // Check required fields
        if (!this.formData.firstName || !this.formData.lastName || !this.formData.emailAddress) {
            this.profileError = 'First name, last name, and email are required.';
            return false;
        }

        // Check email format
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(this.formData.emailAddress)) {
            this.profileError = 'Please enter a valid email address.';
            return false;
        }

        // Check phone numbers
        const validPhones = this.formData.phoneNumbers.filter(phone => phone.trim() !== '');
        if (validPhones.length === 0) {
            this.profileError = 'At least one phone number is required.';
            return false;
        }

        // Check date of birth
        if (!this.formData.dateOfBirth) {
            this.profileError = 'Date of birth is required.';
            return false;
        }

        return true;
    }

    saveProfile(): void {
        if (!this.validateProfileForm()) {
            return;
        }

        this.updatingProfile = true;
        this.profileError = '';
        this.profileSuccess = '';

        // Filter out empty phone numbers
        const phoneNumbers = this.formData.phoneNumbers.filter(phone => phone.trim() !== '');

        const request: UpdateProfileRequest = {
            photo: this.patientProfile?.photo || { type: '', id: 0, url: '' },
            firstName: this.formData.firstName,
            lastName: this.formData.lastName,
            dateOfBirth: this.formData.dateOfBirth,
            phoneNumbers: phoneNumbers,
            emailAddress: this.formData.emailAddress,
            groub: this.formData.groub,
            address: {
                id: this.patientProfile?.address?.id || 0,
                country: this.formData.address.country,
                state: this.formData.address.state,
                city: this.formData.address.city,
                street: this.formData.address.street
            }
        };

        this.patientService.updatePatientProfile(request)
            .subscribe({
                next: (response: PatientProfile) => {
                    this.updatingProfile = false;
                    this.profileSuccess = 'Profile updated successfully!';
                    this.patientProfile = response;
                    this.isEditing = false;
                    console.log('Profile update response:', response);
                },
                error: (error) => {
                    this.updatingProfile = false;
                    this.profileError = error.message || 'An error occurred while updating profile.';
                    console.error('Error updating profile:', error);
                }
            });
    }

    validatePasswordForm(): boolean {
        // Clear previous messages
        this.passwordError = '';
        this.passwordSuccess = '';

        // Check if all fields are filled
        if (!this.passwordData.currentPassword || !this.passwordData.newPassword || !this.passwordData.confirmPassword) {
            this.passwordError = 'All fields are required.';
            return false;
        }

        // Check if new password matches confirm password
        if (this.passwordData.newPassword !== this.passwordData.confirmPassword) {
            this.passwordError = 'New password and confirm password do not match.';
            return false;
        }

        // Check password length (minimum 6 characters)
        if (this.passwordData.newPassword.length < 6) {
            this.passwordError = 'New password must be at least 6 characters long.';
            return false;
        }

        // Check if new password is different from current password
        if (this.passwordData.currentPassword === this.passwordData.newPassword) {
            this.passwordError = 'New password must be different from current password.';
            return false;
        }

        return true;
    }

    changePassword(): void {
        if (!this.validatePasswordForm()) {
            return;
        }

        this.changingPassword = true;
        this.passwordError = '';
        this.passwordSuccess = '';

        const request: ChangePasswordRequest = {
            oldPassword: this.passwordData.currentPassword,
            newPassword: this.passwordData.newPassword
        };

        this.patientService.changePassword(request)
            .subscribe({
                next: (response: ChangePasswordResponse) => {
                    this.changingPassword = false;
                    if (response.success) {
                        this.passwordSuccess = response.message || 'Password changed successfully!';
                        // Reset password form
                        this.passwordData = {
                            currentPassword: '',
                            newPassword: '',
                            confirmPassword: ''
                        };
                    } else {
                        this.passwordError = response.message || 'Failed to change password.';
                    }
                    console.log('Password change response:', response);
                },
                error: (error) => {
                    this.changingPassword = false;
                    this.passwordError = error.message || 'An error occurred while changing password.';
                    console.error('Error changing password:', error);
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

    getFullName(): string {
        if (this.patientProfile) {
            return `${this.patientProfile.firstName || ''} ${this.patientProfile.lastName || ''}`.trim();
        }
        return '';
    }
} 