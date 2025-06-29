import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { DoctorService } from '../../../services/doctor/doctor.service';
import { DoctorBasicDetailsResponse, DoctorBasicDetailsRequest, DoctorMembershipRequest } from '../../../../types';
import { ClinicData } from '../../../../types';

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
  activeTab: string = 'profile';
  clinics: ClinicData[] = [];
  editedClinicIds = new Set<number>();
  experiences: any[] = [];
  editedExperienceIds = new Set<number>();
  educations: any[] = [];
  editedEducationIds = new Set<number>();

  constructor(
    private doctorService: DoctorService,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.initializeForm();
    this.loadBasicDetails();
    this.loadClinics();
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
          id: this.profileImage ? null : null,
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

  setActiveTab(tab: string) {
    this.activeTab = tab;
    if (tab === 'experience') {
      this.loadExperiences();
    }
    if (tab === 'education') {
      this.loadEducations();
    }
  }

  loadClinics(): void {
    this.doctorService.getClinics().subscribe({
      next: (clinics) => {
        this.clinics = clinics || [];
      },
      error: (error) => {
        console.error('Error loading clinics:', error);
        this.clinics = [];
      }
    });
  }

  onClinicChange(clinicId: number): void {
    this.editedClinicIds.add(clinicId);
  }

  isClinicEdited(clinicId: number): boolean {
    if (clinicId === null || clinicId === 0) {
      return true;
    }
    return this.editedClinicIds.has(clinicId);
  }

  saveClinic(clinic: ClinicData): void {
    this.doctorService.updateClinic(clinic).subscribe({
      next: () => {
        this.editedClinicIds.delete(clinic.id);
        // Optionally show a success message
      },
      error: (err) => {
        console.error('Failed to update clinic:', err);
        // Optionally show an error message
      }
    });
  }

  onClinicLogoSelected(event: any, clinic: any) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        clinic.logo = { ...clinic.logo, url: e.target.result, id: null };
        clinic._logoFile = file;
      };
      reader.readAsDataURL(file);
    }
  }

  onClinicGallerySelected(event: any, clinic: any) {
    const files = event.target.files;
    if (files && files.length) {
      (Array.from(files) as File[]).forEach((file: File) => {
        const reader = new FileReader();
        reader.onload = (e: any) => {
          clinic.gellery = clinic.gellery || [];
          clinic.gellery.push({ url: e.target.result, _file: file });
        };
        reader.readAsDataURL(file);
      });
    }
  }

  removeClinicGalleryImage(clinic: any, index: number) {
    clinic.gellery.splice(index, 1);
  }

  addNewClinic(): void {
    this.clinics.push({
      id: 0,
      logo: { url: '', type: '', id: null },
      clinicName: '',
      address: { id: 0, country: '', state: '', city: '', street: '' },
      location: '',
      gellery: [],
      workingHoursMap: {}
    });
  }

  addNewExperience() {
    this.experiences.push({
      id: null,
      logo: { url: '', type: '', id: null },
      title: '',
      hospital: '',
      yearOfExperience: '',
      location: '',
      employment: 'Full Time',
      jobDescription: '',
      startDate: '',
      endDate: '',
      currentlyWorking: false
    });
  }

  onExperienceChange(expId: number) {
    this.editedExperienceIds.add(expId);
  }

  isExperienceEdited(expId: number): boolean {
    if (expId === null) return true;
    return this.editedExperienceIds.has(expId);
  }

  onExperienceLogoSelected(event: any, exp: any) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        exp.logo = { ...exp.logo, url: e.target.result, id: null };
        exp._logoFile = file;
      };
      reader.readAsDataURL(file);
      this.onExperienceChange(exp.id);
    }
  }

  saveExperience(exp: any) {
    const employmentMap: Record<string, string> = {
      'Full Time': 'FullTime',
      'Part Time': 'PartTime',
      'FullTime': 'FullTime',
      'PartTime': 'PartTime'
    };
    const dto = {
      ...exp,
      jopDescription: exp.jobDescription,
      employment: employmentMap[exp.employment] || exp.employment
    };
    this.doctorService.updateExperience(dto).subscribe({
      next: () => {
        this.editedExperienceIds.delete(exp.id);
        // Optionally show a success message
      },
      error: (err) => {
        console.error('Failed to update experience:', err);
        // Optionally show an error message
      }
    });
  }

  loadExperiences(): void {
    this.doctorService.getExperiences().subscribe({
      next: (data) => {
        this.experiences = (data || []).map(exp => ({
          ...exp,
          yearOfExperience: exp.experienceYears,
          jobDescription: exp.jopDescription,
          currentlyWorking: exp.stillWorking,
          employment: exp.employment === 'FullTime' ? 'Full Time'
                     : exp.employment === 'PartTime' ? 'Part Time'
                     : exp.employment
        }));
      },
      error: (err) => {
        console.error('Failed to load experiences:', err);
        this.experiences = [];
      }
    });
  }

  addNewEducation() {
    this.educations.push({
      id: null,
      logo: { url: '', type: '', id: null },
      institution: '',
      course: '',
      startDate: '',
      endDate: '',
      years: '',
      description: ''
    });
  }

  onEducationChange(eduId: number) {
    this.editedEducationIds.add(eduId);
  }

  isEducationEdited(eduId: number): boolean {
    if (eduId === null) return true;
    return this.editedEducationIds.has(eduId);
  }

  onEducationLogoSelected(event: any, edu: any) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        edu.logo = { ...edu.logo, url: e.target.result, id: null };
        edu._logoFile = file;
      };
      reader.readAsDataURL(file);
      this.onEducationChange(edu.id);
    }
  }

  saveEducation(edu: any) {
    // Map UI fields to backend DTO
    const dto = {
      ...edu,
      institutionName: edu.institution,
      yearsNum: edu.years,
    };
    this.doctorService.updateEducation(dto).subscribe({
      next: () => {
        this.editedEducationIds.delete(edu.id);
        // Optionally show a success message
      },
      error: (err) => {
        console.error('Failed to update education:', err);
        // Optionally show an error message
      }
    });
  }

  loadEducations(): void {
    this.doctorService.getEducations().subscribe({
      next: (data) => {
        this.educations = (data || []).map(edu => ({
          ...edu,
          institution: edu.institutionName,
          years: edu.yearsNum
        }));
      },
      error: (err) => {
        console.error('Failed to load educations:', err);
        this.educations = [];
      }
    });
  }
}
