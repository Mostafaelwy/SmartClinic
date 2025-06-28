import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { DoctorService } from '../../../services/doctor/doctor.service';
import { DoctorBasicDetailsResponse, DoctorBasicDetailsRequest, DoctorMembershipRequest } from '../../../../types';

@Component({
  selector: 'app-doctordashprofile',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './doctordashprofile.component.html',
  styleUrl: './doctordashprofile.component.scss'
})
export class DoctordashprofileComponent implements OnInit {
  profileForm!: FormGroup;
  loading: boolean = false;
  saving: boolean = false;
  profileImage: string | null = null;
  selectedFile: File | null = null;

  constructor(
    private doctorService: DoctorService,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.initializeForm();
    this.loadBasicDetails();
  }

  private initializeForm(): void {
    this.profileForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      displayName: ['', Validators.required],
      designation: ['', Validators.required],
      phoneNumbers: this.fb.array([]),
      emailAddress: ['', [Validators.required, Validators.email]],
      languages: this.fb.array([]),
      memberships: this.fb.array([])
    });
  }

  private loadBasicDetails(): void {
    this.loading = true;
    this.doctorService.getBasicDetails().subscribe({
      next: (response) => {
        this.populateForm(response);
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading basic details:', error);
        this.loading = false;
      }
    });
  }

  private populateForm(data: DoctorBasicDetailsResponse): void {
    // Set basic fields
    this.profileForm.patchValue({
      firstName: data.firstName,
      lastName: data.lastName,
      displayName: data.displayName,
      designation: data.designation,
      emailAddress: data.emailAddress
    });

    // Set profile image
    if (data.photo?.url) {
      this.profileImage = data.photo.url;
    }

    // Set phone numbers
    const phoneNumbersArray = this.profileForm.get('phoneNumbers') as FormArray;
    phoneNumbersArray.clear();
    data.phoneNumbers.forEach(phone => {
      phoneNumbersArray.push(this.fb.control(phone, Validators.required));
    });

    // Set languages
    const languagesArray = this.profileForm.get('languages') as FormArray;
    languagesArray.clear();
    data.langusgaes.forEach(language => {
      languagesArray.push(this.fb.control(language, Validators.required));
    });

    // Set memberships
    const membershipsArray = this.profileForm.get('memberships') as FormArray;
    membershipsArray.clear();
    data.membershipsDto.forEach(membership => {
      membershipsArray.push(this.fb.group({
        id: [membership.id],
        title: [membership.title, Validators.required],
        about: [membership.about]
      }));
    });
  }

  // Form array getters
  get phoneNumbersArray(): FormArray {
    return this.profileForm.get('phoneNumbers') as FormArray;
  }

  get languagesArray(): FormArray {
    return this.profileForm.get('languages') as FormArray;
  }

  get membershipsArray(): FormArray {
    return this.profileForm.get('memberships') as FormArray;
  }

  // Add phone number
  addPhoneNumber(): void {
    this.phoneNumbersArray.push(this.fb.control('', Validators.required));
  }

  // Remove phone number
  removePhoneNumber(index: number): void {
    this.phoneNumbersArray.removeAt(index);
  }

  // Add language
  addLanguage(): void {
    this.languagesArray.push(this.fb.control('', Validators.required));
  }

  // Remove language
  removeLanguage(index: number): void {
    this.languagesArray.removeAt(index);
  }

  // Add membership
  addMembership(): void {
    this.membershipsArray.push(this.fb.group({
      id: [0],
      title: ['', Validators.required],
      about: ['']
    }));
  }

  // Remove membership
  removeMembership(index: number): void {
    this.membershipsArray.removeAt(index);
  }

  // File upload handling
  onFileSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.profileImage = e.target.result; // This will be base64
      };
      reader.readAsDataURL(file);
    }
  }

  // Remove profile image
  removeProfileImage(): void {
    this.profileImage = null;
    this.selectedFile = null;
  }

  // Save changes
  saveChanges(): void {
    if (this.profileForm.valid) {
      this.saving = true;

      const formValue = this.profileForm.value;
      const requestData: DoctorBasicDetailsRequest = {
        firstName: formValue.firstName,
        lastName: formValue.lastName,
        displayName: formValue.displayName,
        designation: formValue.designation,
        phoneNumbers: formValue.phoneNumbers,
        emailAddress: formValue.emailAddress,
        langusgaes: formValue.languages,
        photo: {
          type: 'image/jpeg',
          id: 0,
          url: this.profileImage || ''
        },
        membershipsRequest: formValue.memberships.map((membership: any) => ({
          id: membership.id,
          title: membership.title,
          about: membership.about
        }))
      };

      this.doctorService.updateBasicDetails(requestData).subscribe({
        next: (response) => {
          console.log('Profile updated successfully:', response);
          this.saving = false;
          // Optionally show success message
        },
        error: (error) => {
          console.error('Error updating profile:', error);
          this.saving = false;
          // Optionally show error message
        }
      });
    }
  }

  // Cancel changes
  cancelChanges(): void {
    this.loadBasicDetails(); // Reload original data
  }
}
