import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { JwtService } from '../services/jwt.service';
import { HttpClient } from '@angular/common/http';
import { API_ENDPOINTS } from '../config/api-endpoints';

@Component({
  selector: 'app-doctor-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './doctor-register.component.html',
  styleUrl: './doctor-register.component.scss'
})
export class DoctorRegisterComponent {
  firstName: string = '';
  secondName: string = '';
  email: string = '';
  password: string = '';
  error: string | null = null;

  constructor(private http: HttpClient, private jwtService: JwtService, private router: Router) {}

  onSubmit() {
    const payload = {
      firstName: this.firstName,
      secondName: this.secondName,
      email: this.email,
      password: this.password
    };
    this.http.post<{ token: string }>(API_ENDPOINTS.AUTH.DOCTOR_REGISTER, payload).subscribe({
      next: (res) => {
        this.jwtService.setToken(res.token);
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        this.error = err?.error?.message || 'Registration failed.';
      }
    });
  }
} 