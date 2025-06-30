import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpErrorResponse } from '@angular/common/http';
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

export interface Photo {
    type: string;
    id: number;
    url: string;
}

export interface Patient {
    name: string;
    address: Address;
}

export interface Doctor {
    doctorId: number;
    doctorName: string;
    address: Address;
    photo: Photo;
}

export interface Medication {
    id: number;
    name: string;
    duration: string;
    instructions: string;
    dosage: string;
}

export interface Prescription {
    prescriptionId: number;
    issuedDate: string;
    patient: Patient;
    doctor: Doctor;
    medications: Medication[];
    otherInformation: string;
    followUp: string;
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

export interface PrescriptionResponse {
    totalPages: number;
    totalElements: number;
    size: number;
    content: Prescription[];
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
export class PrescriptionService {
    private baseUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) { }

    getPatientPrescriptions(patientId: number, page: number = 0, size: number = 10): Observable<PrescriptionResponse> {
        const params = new HttpParams()
            .set('page', page.toString())
            .set('size', size.toString());

        return this.http.get<PrescriptionResponse>(`${this.baseUrl}/smart/doctor/patient/${patientId}/prescriptions`, { params })
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

        console.error('Prescription service error:', errorMessage);
        return throwError(() => new Error(errorMessage));
    }
} 