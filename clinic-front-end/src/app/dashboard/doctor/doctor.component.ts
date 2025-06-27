import { Component, OnInit } from '@angular/core';
import { DoctorStatistics } from '../../../types';
import { CommonModule } from '@angular/common';
import { DoctorService } from '../../services/doctor/doctor.service';
import { forkJoin, Observable } from 'rxjs';
import { RouterModule, RouterOutlet } from '@angular/router';

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
  constructor(private doctorService:DoctorService){

  }
  ngOnInit(): void {

  }

}
