import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  name: string = '';
  phone: string = '';
  password: string = '';

  constructor(private router: Router) {}

  onSubmit() {
    // Registration logic here
    // For now, just log the values
    console.log('Name:', this.name);
    console.log('Phone:', this.phone);
    console.log('Password:', this.password);
    // TODO: Call registration API
  }

  goToLogin() {
    this.router.navigate(['/login']);
  }

  goToDoctorRegister() {
    this.router.navigate(['/doctor-register']);
  }
} 