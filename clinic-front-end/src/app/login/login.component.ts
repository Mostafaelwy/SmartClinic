import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthServiceService } from '../services/auth-service.service';
import { JwtService } from '../services/jwt.service';
import { AuthorizedUser} from '../../types';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  email: string = '';
  password: string = '';
  loginError: string | null = null;
  constructor( private loginService:AuthServiceService,private jwtService:JwtService, private router:Router) {

  }

  onSubmit() {
    // Implement your login logic here
    console.log('Username:', this.email);
    console.log('Password:', this.password);
    let user = {
      'email':this.email,
      "password":this.password
    }
    this.loginService.login(user).subscribe(
      {
        next: (loggedUser:AuthorizedUser) => {
          
          console.log("loggin in")
          this.jwtService.setToken(loggedUser.token.toString())
          console.log(this.jwtService.decodeToken())
          console.log(this.jwtService.hasRole("DOCTOR"))
          this.router.navigate(['/dashboard']);
        },
        error: (err) => {
          // this.errorMessage = err.message;
          // this.loading = false;
          this.loginError = 'Invalid email or password'; 
        },
        // complete: () => {
        //   // this.loading = false;
        // }
      }
    )
  }

}
