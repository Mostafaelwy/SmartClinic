import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { API_ENDPOINTS } from '../config/api-endpoints';
import { JwtService } from '../services/jwt.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
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
    this.http.post<{ token: string }>(API_ENDPOINTS.AUTH.PATIENT_REGISTER, payload).subscribe({
      next: (res) => {
        if (res.token) {
          this.jwtService.setToken(res.token);
        }
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        this.error = err?.error?.message || 'Registration failed.';
      }
    });
  }

  goToLogin() {
    this.router.navigate(['/login']);
  }

  goToDoctorRegister() {
    this.router.navigate(['/doctor-register']);
  }
} 