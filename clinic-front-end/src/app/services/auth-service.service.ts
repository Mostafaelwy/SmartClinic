import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { AuthorizedUser, LoginRequest, ReservationRequest, ReservationResponse, DoctorBasicData } from '../../types';
import { API_ENDPOINTS } from '../config/api-endpoints';
import { Observable } from 'rxjs';
import { CanActivate, Router, UrlTree } from '@angular/router';
import { JwtService } from './jwt.service';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor(private httpClient:HttpClient) { 
  }
  login(user:LoginRequest):Observable<AuthorizedUser>{
    return this.httpClient.post<AuthorizedUser>(API_ENDPOINTS.AUTH.LOGIN, 
      user)
  }

  reserveAppointment(clinicId: number, reservationData: ReservationRequest): Observable<ReservationResponse> {
    return this.httpClient.post<ReservationResponse>(`${API_ENDPOINTS.PATIENT.RESERVATION_CLINIC}/${clinicId}`, reservationData);
  }

  getDoctorSpecialties(doctorId: string): Observable<any[]> {
    return this.httpClient.get<any[]>(`${API_ENDPOINTS.PATIENT.DOCTOR_SPECIALTIES}/${doctorId}/specialties`);
  }

  getDoctorClinics(doctorId: string): Observable<any[]> {
    return this.httpClient.get<any[]>(`${API_ENDPOINTS.PATIENT.DOCTOR_CLINICS}/${doctorId}/clinics`);
  }

  getClinicTimeAvailability(clinicId: number, date: string): Observable<{ [key: string]: boolean }> {
    return this.httpClient.get<{ [key: string]: boolean }>(
      `${API_ENDPOINTS.PATIENT.TIME_AVAILABILITY}/${clinicId}/time-avilabilty`,
      { params: { date } }
    );
  }

  getDoctorBasicData(doctorId: string): Observable<DoctorBasicData> {
    return this.httpClient.get<DoctorBasicData>(`${API_ENDPOINTS.PATIENT.DOCTOR_BASIC_DATA}/${doctorId}/basic-data`);
  }

}

@Injectable({ providedIn: 'root' })
export class AuthGuardService implements CanActivate {
  constructor(private jwtService: JwtService, private router: Router) {}

  canActivate(route: any, state: any): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    const token = this.jwtService.getToken();
    if (!token || this.jwtService.isTokenExpired()) {
      return this.router.createUrlTree(['/login']);
    }
    // If route data has roles, check for role
    const expectedRoles = route.data && route.data['roles'] ? route.data['roles'] : null;
    if (expectedRoles) {
      const roles = this.jwtService.getClaim('roles') || [];
      const hasRole = roles.some((role: any) => expectedRoles.includes(role.authority));
      if (!hasRole) {
        // Redirect based on user role and expected route
        if (roles.some((role: any) => role.authority === 'DOCTOR')) {
          return this.router.createUrlTree(['/dashboard/doctor']);
        }
        if (roles.some((role: any) => role.authority === 'PATIENT')) {
          return this.router.createUrlTree(['/dashboard/patient']);
        }
        return this.router.createUrlTree(['/login']);
      }
    }
    return true;
  }
}

@Injectable({ providedIn: 'root' })
export class AuthRedirectGuard implements CanActivate {
  constructor(private jwtService: JwtService, private router: Router) {}

  canActivate(): boolean | UrlTree {
    const token = this.jwtService.getToken();
    if (!token || this.jwtService.isTokenExpired()) {
      return this.router.createUrlTree(['/login']);
    }
    const roles = this.jwtService.getClaim('roles') || [];
    if (roles.some((role: any) => role.authority === 'DOCTOR')) {
      return this.router.createUrlTree(['/dashboard/doctor']);
    }
    if (roles.some((role: any) => role.authority === 'PATIENT')) {
      return this.router.createUrlTree(['/dashboard/patient']);
    }
    return this.router.createUrlTree(['/login']);
  }
}
