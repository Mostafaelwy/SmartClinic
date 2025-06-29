import { Injectable } from '@angular/core';
import { JwtService } from '../jwt.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Clinic, DayOfWeek, DaySlot, DaySlotMap, DoctorStatistics, PaginatedReservations, PagingFilter, ReservationFilter, ReservationStatus, SlotDTO, SpecialityDto, WorkingHoursMap, PagedReviews, PatientsApiResponse, UpcomingAppointmentResponse, ClinicData, DoctorBasicDetailsResponse, DoctorBasicDetailsRequest } from '../../../types';
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

  getReservations(reservationFilter:ReservationFilter, pagingFilter:PagingFilter):Observable<PaginatedReservations>{
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

  getMyClinics(): Observable<Clinic[]> {
    console.log(API_ENDPOINTS.DOCTOR.MY_CLINICS)
    return this.http.get<Clinic[]>(API_ENDPOINTS.DOCTOR.MY_CLINICS, {});
  }
  getDaySlots(clinicId: number, day: DayOfWeek): Observable<DaySlotMap> {
    const params = {workingDay:day}
    return this.http.get<DaySlotMap>(`${API_ENDPOINTS.DOCTOR.AVAILABLE_TIMINGS}/${clinicId}`,{params:params});
  }

  postSlots(day: DayOfWeek, clinicId: number, slot: SlotDTO): Observable<WorkingHoursMap> {
    const url = `${API_ENDPOINTS.DOCTOR.WORKING_DAY_SLOT}/${day}/clinic/${clinicId}`;
    return this.http.patch<WorkingHoursMap>(url, slot);
  }

   getSpecialties(): Observable<SpecialityDto[]> {
    return this.http.get<SpecialityDto[]>(`${API_ENDPOINTS.DOCTOR.SPECIALITIES}`);
  }
    deleteSpeciality(id: number): Observable<void> {
    return this.http.delete<void>(`${API_ENDPOINTS.DOCTOR.SPECIALITY}/${id}`);
  }

  deleteService(id: number): Observable<void> {
    return this.http.delete<void>(`${API_ENDPOINTS.DOCTOR.SERVICE}/${id}`);
  }

  saveSpeciality(data: SpecialityDto): Observable<any> {
    return this.http.post(`${API_ENDPOINTS.DOCTOR.SPECIALITY}`, data);
  }

  getReviews(params: {
    pageNum: number;
    pageSize: number;
    sortAttripute: string;
    dir: 'ASC' | 'DESC';
    startDate: string;
    endDate: string;
  }): Observable<PagedReviews> {
    return this.http.get<PagedReviews>(API_ENDPOINTS.DOCTOR.REVIEWS, { params });
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
      API_ENDPOINTS.DOCTOR.MY_CLINICS,
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
