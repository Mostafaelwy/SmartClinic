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

  constructor(private doctorService: DoctorService) {}

   ngOnInit(): void {
    this.loadSpecialties();
  }

  loadSpecialties() {
    this.doctorService.getSpecialties().subscribe({
      next: (data) => {
        this.specialties = data;
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
        this.specialties = this.specialties.filter(s => s.id !== specialityId);
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

}
