import { Component } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { DoctorComponent } from './doctor/doctor.component';
import { CommonModule } from '@angular/common';
import { AuthServiceService } from '../services/auth-service.service';
import { JwtService } from '../services/jwt.service';
@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterOutlet, RouterModule, DoctorComponent, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {
  userRole: String | null = null; // This could come from a service or token

  constructor(private router: Router, private jwtService: JwtService) { }
  ngOnInit() {
    // Simulate role check (you can fetch from auth service or JWT)
    const role = this.jwtService.getRole();
    if (role) this.userRole = role.authority
    console.log(role);
    console.log(this.userRole)
    if (this.userRole === 'DOCTOR') {
      this.router.navigate(['dashboard/doctor']);
    } else if (this.userRole === 'PATIENT') {
    this.router.navigate(['dashboard/patient']);
    }
  }
}
