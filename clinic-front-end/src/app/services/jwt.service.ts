import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
import {  JwtPayload, Authority } from '../../types';

@Injectable({
  providedIn: 'root'
})
export class JwtService {
  constructor() { }
  
  private token: string | null = null;

  // Store token after login
  setToken(token: string): void {
    this.token = token;
    localStorage.setItem('access_token', token); // Optional: persist
  }
  // Get raw token
  getToken(): string | null {
    return this.token || localStorage.getItem('access_token');
  }

  // Clear token on logout
  clearToken(): void {
    this.token = null;
    localStorage.removeItem('access_token');
  }

  // Decode and get all claims
  decodeToken(): JwtPayload| null {
    const token = this.getToken();
    if (!token) return null;
    try {
      return jwtDecode<JwtPayload>(token);
    } catch (error) {
      console.error('Error decoding token:', error);
      return null;
    }
  }
  

  // Get specific claim
  getClaim(claimName: string): any | null {
    const decoded:JwtPayload | null = this.decodeToken();
    console.log(this.token)
    console.log(this.decodeToken())
    return decoded ? decoded[claimName] : null;
  }

  // Check if token is expired
  isTokenExpired(): boolean {
    const decoded = this.decodeToken();
    if (!decoded || !decoded.exp) return true;
    
    const currentTime = Date.now() / 1000;
    return decoded.exp < currentTime;
  }

  // Check if user has role
  hasRole(role: string): boolean {
    const typedRule:Authority = {authority:role}
    const roles:Authority[] = this.getClaim('roles') || [];
    console.log(typedRule)
    console.log(roles)
    return roles.some(role => role.authority === typedRule.authority);
  }
  getRole():Authority|null{
    const roles:Authority[] = this.getClaim('roles') || [];
    if (roles.length >= 1){
      return roles[0];
    }
    return null;
  }
}
