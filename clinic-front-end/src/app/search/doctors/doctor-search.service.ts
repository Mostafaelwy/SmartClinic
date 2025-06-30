import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_ENDPOINTS } from '../../config/api-endpoints';

@Injectable({ providedIn: 'root' })
export class DoctorSearchService {
  constructor(private http: HttpClient) {}

  getDoctors(filter: any, paging: any): Observable<any> {
    let params = new HttpParams();
    if (filter) {
      Object.keys(filter).forEach(key => {
        if (filter[key] !== undefined && filter[key] !== null) {
          params = params.set(key, filter[key]);
        }
      });
    }
    if (paging) {
      Object.keys(paging).forEach(key => {
        if (paging[key] !== undefined && paging[key] !== null) {
          params = params.set(key, paging[key]);
        }
      });
    }
    return this.http.get(API_ENDPOINTS.PATIENT.DOCTORS, { params });
  }
} 