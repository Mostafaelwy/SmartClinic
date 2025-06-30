import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../../env/enviroment';

export interface Address {
    id: number;
    country: string;
    state: string;
    city: string;
    street: string;
}

export interface PatientPhoto {
    type: string;
    id: number;
    url: string;
}

export interface PatientProfile {
    patientId: number;
    photo: PatientPhoto;
    firstName: string;
    lastName: string;
    dateOfBirth: string;
    phoneNumbers: string[];
    emailAddress: string;
    groub: string;
    address: Address;
}

export interface UpdateProfileRequest {
    photo: PatientPhoto;
    firstName: string;
    lastName: string;
    dateOfBirth: string;
    phoneNumbers: string[];
    emailAddress: string;
    groub: string;
    address: Address;
}

export interface ChangePasswordRequest {
    oldPassword: string;
    newPassword: string;
}

export interface ChangePasswordResponse {
    message: string;
    success: boolean;
}

@Injectable({
    providedIn: 'root'
})
export class PatientService {
    private baseUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) { }

    getPatientProfile(): Observable<PatientProfile> {
        return this.http.get<PatientProfile>(`${this.baseUrl}/smart/patient/me/profile-data`)
            .pipe(
                catchError(this.handleError)
            );
    }

    getPatientBasicData(): Observable<PatientProfile> {
        return this.http.get<PatientProfile>(`${this.baseUrl}/smart/patient/me/basic-data`)
            .pipe(
                catchError(this.handleError)
            );
    }

    updatePatientProfile(request: UpdateProfileRequest): Observable<PatientProfile> {
        return this.http.post<PatientProfile>(`${this.baseUrl}/smart/patient/me/basic-data`, request)
            .pipe(
                catchError(this.handleError)
            );
    }

    changePassword(request: ChangePasswordRequest): Observable<ChangePasswordResponse> {
        return this.http.post<ChangePasswordResponse>(`${this.baseUrl}/smart/patient/me/change-password`, request)
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

        console.error('Patient service error:', errorMessage);
        return throwError(() => new Error(errorMessage));
    }
} 