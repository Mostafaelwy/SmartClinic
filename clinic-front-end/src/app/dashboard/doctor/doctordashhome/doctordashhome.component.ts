import { Component, OnInit } from '@angular/core';
import { DoctorService } from '../../../services/doctor/doctor.service';
import { forkJoin, Observable } from 'rxjs';
import { DoctorStatistics, ReservationFilter, ReservationStatus, PatientsApiResponse, PatientData, UpcomingAppointmentResponse, UpcomingAppointmentData, ClinicData } from '../../../../types';
import { AppointmentListMiniComponent } from './appointment-list-mini/appointment-list-mini.component';
import { AuthServiceService } from '../../../services/auth-service.service';
import { JwtService } from '../../../services/jwt.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-doctordashhome',
  standalone: true,
  imports: [AppointmentListMiniComponent, CommonModule, FormsModule],
  templateUrl: './doctordashhome.component.html',
  styleUrl: './doctordashhome.component.scss'
})
export class DoctordashhomeComponent implements OnInit {
  statistics: DoctorStatistics | null = null;
  patients: PatientData[] = [];
  upcomingAppointment: UpcomingAppointmentData | null = null;
  clinics: ClinicData[] = [];
  loading: boolean = true;
  reservationFilter: ReservationFilter = {
  };
  constructor(private doctorService: DoctorService, private jwtService: JwtService) {

    this.reservationFilter = {
      doctorName: this.jwtService.getClaim("sub"),
      status: ReservationStatus.PENDING
    }

  }

  ngOnInit(): void {
    forkJoin({
      stats: this.intializeStatistics(),
      patients: this.initializePatients(),
      upcomingAppointments: this.initializeUpcomingAppointments(),
      clinics: this.initializeClinics()
    }).subscribe({
      next: ({ stats, patients, upcomingAppointments, clinics }) => {
        this.statistics = stats;
        this.patients = patients.content || [];
        this.upcomingAppointment = this.findUpcomingAppointment(upcomingAppointments.content || []);
        this.clinics = clinics || [];
        this.loading = false;
      },
      error: (err) => {
        console.error('Failed to initialize doctor dashboard:', err);
        // Optionally set an error flag or retry logic
      }
    });

  }

  intializeStatistics(): Observable<DoctorStatistics> {
    return this.doctorService.getStatistics().pipe(

    )
  }

  initializePatients(): Observable<PatientsApiResponse> {
    return this.doctorService.getPatients();
  }

  initializeUpcomingAppointments(): Observable<UpcomingAppointmentResponse> {
    return this.doctorService.getUpcomingAppointment();
  }

  initializeClinics(): Observable<ClinicData[]> {
    return this.doctorService.getClinics();
  }

  findUpcomingAppointment(appointments: UpcomingAppointmentData[]): UpcomingAppointmentData | null {
    if (!appointments || appointments.length === 0) {
      return null;
    }

    const now = new Date();

    // Find the first appointment that is in the future
    const upcomingAppointment = appointments.find(appointment => {
      const appointmentDate = new Date(appointment.reservationDate);
      appointmentDate.setHours(appointment.reservationTime.hour);
      appointmentDate.setMinutes(appointment.reservationTime.minute);
      appointmentDate.setSeconds(appointment.reservationTime.second);

      return appointmentDate > now;
    });

    return upcomingAppointment || null;
  }

  formatTime(time: any): string {
    if (!time) return '';
    return `${time.hour.toString().padStart(2, '0')}:${time.minute.toString().padStart(2, '0')}`;
  }

  formatTimeString(timeString: string): string {
    if (!timeString) return '';
    // Convert "01:00:00" to "01:00"
    return timeString.substring(0, 5);
  }

  isWorkingHoursMapValid(workingHoursMap: any): boolean {
    return workingHoursMap &&
      typeof workingHoursMap === 'object' &&
      !Array.isArray(workingHoursMap) &&
      Object.keys(workingHoursMap).length > 0;
  }

}
