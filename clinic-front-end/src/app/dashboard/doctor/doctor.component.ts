import { Component, OnInit } from '@angular/core';
import { DoctorStatistics } from '../../../types';
import { CommonModule } from '@angular/common';
import { DoctorService } from '../../services/doctor/doctor.service';
import { forkJoin, Observable } from 'rxjs';
import { RouterModule, RouterOutlet } from '@angular/router';
import { AppComponent } from '../../app.component';

interface DoctorDashboardProfileData {
  name: string;
  specialites: string[];
  photo: { type: string; id: number; url: string };
  displayName: string;
}

@Component({
  selector: 'app-doctor',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterModule],
  templateUrl: './doctor.component.html',
  styleUrl: './doctor.component.scss'
})
export class DoctorComponent implements OnInit{
  statistics:DoctorStatistics|null = null;
  loading:boolean = true;
  doctorProfileData: DoctorDashboardProfileData | null = null;

  constructor(private doctorService: DoctorService, private appComponent: AppComponent){

  }
  ngOnInit(): void {
    this.doctorService.getProfileData().subscribe({
      next: (data: DoctorDashboardProfileData) => {
        this.doctorProfileData = data;
      },
      error: (err) => {
        console.error('Failed to load doctor profile data', err);
      }
    });
  }

  logout() {
    this.appComponent.logout();
  }
}
