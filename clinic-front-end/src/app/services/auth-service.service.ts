import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { AuthorizedUser, LoginRequest } from '../../types';
import { API_ENDPOINTS } from '../config/api-endpoints';
import { Observable } from 'rxjs';

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

}
