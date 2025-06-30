import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../../env/enviroment';

export interface AppointmentTime {
    hour: number;
    minute: number;
    second: number;
    nano: number;
}

export interface DoctorPhoto {
    type: string;
    id: number;
    url: string;
}

export interface ClinicLocation {
    id: number;
    country: string;
    state: string;
    city: string;
    street: string;
}

export interface Appointment {
    appointmentId: number;
    doctorId: number;
    doctorName: string;
    getdoctorPhoto: DoctorPhoto;
    doctorEmail: string;
    doctorNumbers: string[];
    appointmentDate: string;
    appointmentTime: AppointmentTime;
    bookingDate: string;
    status: string;
    cost: number;
    clinicName: string;
    clinicLocation: ClinicLocation;
    visitType: string;
}

export interface Sort {
    empty: boolean;
    sorted: boolean;
    unsorted: boolean;
}

export interface Pageable {
    offset: number;
    sort: Sort;
    paged: boolean;
    pageSize: number;
    pageNumber: number;
    unpaged: boolean;
}

export interface AppointmentResponse {
    totalPages: number;
    totalElements: number;
    size: number;
    content: Appointment[];
    number: number;
    sort: Sort;
    first: boolean;
    last: boolean;
    numberOfElements: number;
    pageable: Pageable;
    empty: boolean;
}

@Injectable({
    providedIn: 'root'
})
export class AppointmentService {
    private baseUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) { }

    getPatientAppointments(page: number = 0, size: number = 10): Observable<AppointmentResponse> {
        const params = new HttpParams()
            .set('page', page.toString())
            .set('size', size.toString());

        return this.http.get<AppointmentResponse>(`${this.baseUrl}/smart/patient/me/appointments`, { params })
            .pipe(
                catchError(this.handleError)
            );
    }

    private handleError(error: HttpErrorResponse) {
        let errorMessage = 'An error occurred';

        if (error.error instanceof ErrorEvent) {
            // Client-side error
            errorMessage = `Error: ${error.error.message}`;
        } else {
            // Server-side error
            if (error.status === 401) {
                errorMessage = 'Authentication failed. Please login again.';
                // You can redirect to login here
                // this.router.navigate(['/login']);
            } else if (error.status === 403) {
                errorMessage = 'Access denied. You do not have permission to access this resource.';
            } else if (error.status === 404) {
                errorMessage = 'Resource not found.';
            } else if (error.status >= 500) {
                errorMessage = 'Server error. Please try again later.';
            } else {
                errorMessage = `Error: ${error.status} - ${error.message}`;
            }
        }

        console.error('Appointment service error:', errorMessage);
        return throwError(() => new Error(errorMessage));
    }
} 