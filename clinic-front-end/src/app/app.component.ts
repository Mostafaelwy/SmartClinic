import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { RouterOutlet, RouterModule, Router } from '@angular/router';
import { JwtService } from './services/jwt.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  constructor(private jwtService: JwtService, private router: Router) {}

  title = 'clinic-front-end';

  get isLoggedIn(): boolean {
    const token = this.jwtService.getToken();
    return !!token && !this.jwtService.isTokenExpired();
  }

  logout() {
    this.jwtService.clearToken();
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
