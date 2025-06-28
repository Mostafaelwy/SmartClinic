import { Injectable } from '@angular/core';
import { JwtService } from '../jwt.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DoctorStatistics, PaginatedReservations, PagingFilter, ReservationFilter, ReservationStatus, PatientsApiResponse, UpcomingAppointmentResponse, ClinicData, DoctorBasicDetailsResponse, DoctorBasicDetailsRequest } from '../../../types';
import { API_ENDPOINTS } from '../../config/api-endpoints';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor(private http: HttpClient, private jwtService: JwtService) { }

  getStatistics(): Observable<DoctorStatistics> {
    const token = this.jwtService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.get<DoctorStatistics>(API_ENDPOINTS.DOCTOR.STAISTICS);
  }

  getReservations(reservationFilter: ReservationFilter, pagingFilter: PagingFilter): Observable<PaginatedReservations> {
    const token = this.jwtService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    const params = {
      ...reservationFilter,
      pageNum: pagingFilter.pageNum ?? 0,
      pageSize: pagingFilter.pageSize ?? 5
    };
    return this.http.get<PaginatedReservations>(
      API_ENDPOINTS.DOCTOR.RESERVATIONS,
      { params: params }
    );
  }

  updateStatus(reservationId: number, status: ReservationStatus): Observable<any> {
    return this.http.patch(`${API_ENDPOINTS.DOCTOR.RESERVATION}/${reservationId}?status=${status}`, {});
  }

  acceptReservation(id: number): Observable<any> {
    return this.updateStatus(id, ReservationStatus.ACCEPTED);
  }

  rejectReservation(id: number): Observable<any> {
    return this.updateStatus(id, ReservationStatus.REJECTED);
  }

  getPatients(): Observable<PatientsApiResponse> {
    const token = this.jwtService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<PatientsApiResponse>(
      API_ENDPOINTS.DOCTOR.PATIENTS,
      { headers: headers }
    );
  }

  getUpcomingAppointment(): Observable<UpcomingAppointmentResponse> {
    return this.http.get<UpcomingAppointmentResponse>(
      API_ENDPOINTS.DOCTOR.UPCOMING_APPOINTMENT
    );
  }

  getClinics(): Observable<ClinicData[]> {
    const token = this.jwtService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<ClinicData[]>(
      API_ENDPOINTS.DOCTOR.CLINICS,
      { headers: headers }
    );
  }

  getBasicDetails(): Observable<DoctorBasicDetailsResponse> {
    const token = this.jwtService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<DoctorBasicDetailsResponse>(
      API_ENDPOINTS.DOCTOR.BASIC_DETAILS,
      { headers: headers }
    );
  }

  updateBasicDetails(basicDetails: DoctorBasicDetailsRequest): Observable<any> {
    const token = this.jwtService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    return this.http.post(
      API_ENDPOINTS.DOCTOR.BASIC_DETAILS,
      basicDetails,
      { headers: headers }
    );
  }
}
