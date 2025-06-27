import { Component, OnInit } from '@angular/core';
import { DoctorService } from '../../../services/doctor/doctor.service';
import { forkJoin, Observable } from 'rxjs';
import { DoctorStatistics, ReservationFilter, ReservationStatus } from '../../../../types';
import { AppointmentListMiniComponent } from './appointment-list-mini/appointment-list-mini.component';
import { AuthServiceService } from '../../../services/auth-service.service';
import { JwtService } from '../../../services/jwt.service';

@Component({
  selector: 'app-doctordashhome',
  standalone: true,
  imports: [AppointmentListMiniComponent],
  templateUrl: './doctordashhome.component.html',
  styleUrl: './doctordashhome.component.scss'
})
export class DoctordashhomeComponent implements OnInit   {
    statistics:DoctorStatistics|null = null;
    loading:boolean = true;
      reservationFilter: ReservationFilter = {
  };
  constructor(private doctorService:DoctorService, private jwtService:JwtService){

    this.reservationFilter = {
      doctorName:this.jwtService.getClaim("sub"),
      status:ReservationStatus.PENDING
   }

  }

  ngOnInit(): void {
      forkJoin({
      stats: this.intializeStatistics(),

    }).subscribe({
      next: ({ stats }) => {
        this.statistics = stats;
        // this.doctorName = name;
        // this.patientsList = patients;
        this.loading = false;
      },
      error: (err) => {
        console.error('Failed to initialize doctor dashboard:', err);
        // Optionally set an error flag or retry logic
      }
    });
      
    }
    
    intializeStatistics():Observable<DoctorStatistics>{
     return  this.doctorService.getStatistics().pipe(
  
     )
    }
  
}
