import { Component } from '@angular/core';
import { SpecialityDto, SpecialityService, Specialties } from '../../../../types';
import { DoctorService } from '../../../services/doctor/doctor.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-doctordashspecandserv',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './doctordashspecandserv.component.html',
  styleUrl: './doctordashspecandserv.component.scss'
})
export class DoctordashspecandservComponent {
  specialties: SpecialityDto[] = [];
  allSpecialtyOptions: string[] = Object.values(Specialties);
  allServiceOptions: string[] = Object.values(SpecialityService);
  editedSpecialtyIds = new Set<number>();


  constructor(private doctorService: DoctorService) {}

   ngOnInit(): void {
    this.loadSpecialties();
  }

  loadSpecialties() {
    this.doctorService.getSpecialties().subscribe({
      next: (data) => {
        this.specialties = data;
        console.log(this.specialties)
      },
      error: (err) => {
        console.error('Failed to load specialties:', err);
      },
    });
  }


  deleteSpecialityById(specialityId: number): void {
  if (confirm('Are you sure you want to delete this speciality?')) {
    this.doctorService.deleteSpeciality(specialityId).subscribe({
      next: () => {
        this.specialties = this.specialties.filter(s => s.id != specialityId);
      },
      error: err => {
        console.error('Failed to delete speciality', err);
        alert('Error deleting speciality');
      }
    });
  }
}

deleteServiceById(serviceId: number, specialityId: number): void {
  if (confirm('Are you sure you want to delete this service?')) {
    this.doctorService.deleteService(serviceId).subscribe({
      next: () => {
        const speciality = this.specialties.find(s => s.id === specialityId);
        if (speciality) {
          speciality.services = speciality.services.filter(s => s.id !== serviceId);
        }
      },
      error: err => {
        console.error('Failed to delete service', err);
        alert('Error deleting service');
      }
    });
  }
  }
  onChange(specialtyId: number) {
  this.editedSpecialtyIds.add(specialtyId);
}
isEdited(specialtyId: number): boolean {
  return this.editedSpecialtyIds.has(specialtyId);
}
saveChanges(specialtyId: number) {
  const specialty = this.specialties.find(s => s.id === specialtyId);
  if (!specialty) {
    console.error('Specialty not found:', specialtyId);
    return;
  }

  const payload: SpecialityDto = {
    id: specialty.id,
    speciality: specialty.speciality,
    services: specialty.services.map(service => ({
      id: service.id ?? null,
      serviceType: service.serviceType,
      price: service.price,
      hint: service.hint
    }))
  };

  this.doctorService.saveSpeciality(payload).subscribe({
    next: () => {
      this.editedSpecialtyIds.delete(specialtyId);
      // Optionally notify success
    },
    error: (err) => {
      console.error('Failed to save specialty:', err);
    }
  });
}

addNewSpeciality() {
  this.specialties.push({
    id: null,
    speciality: this.allSpecialtyOptions[0] as Specialties,
    services: []
  });
  // Optionally, you may want to track this as edited for immediate saving
  // this.editedSpecialtyIds.add(null); // Only if your logic supports null as a key
}

addNewService(specialtyId: number | null) {
  const specialty = this.specialties.find(s => s.id === specialtyId);
  if (specialty) {
    specialty.services.push({
      id: null,
      serviceType: this.allServiceOptions[0] as SpecialityService,
      price: 0,
      hint: ''
    });
    if (specialtyId !== null) {
      this.editedSpecialtyIds.add(specialtyId);
    }
  }
}

}
