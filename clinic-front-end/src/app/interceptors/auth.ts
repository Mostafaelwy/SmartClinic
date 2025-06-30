import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { JwtService } from '../services/jwt.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private jwtService: JwtService) {

  }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("interceptor called for URL:", req.url);
    const token = this.jwtService.getToken();
    console.log("Token available:", !!token);

    // Skip token for login/register endpoints
    if (req.url.includes('/login') || req.url.includes('/register') || req.url.includes('/auth')) {
      console.log("Skipping token for auth endpoint");
      return next.handle(req);
    }

    // Add token to all other requests
    if (token && !this.jwtService.isTokenExpired()) {
      console.log("Adding Bearer token to request");
      const cloned = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json'
        }
      });
      return next.handle(cloned);
    } else {
      console.log("No valid token found, proceeding without authorization");
      // You might want to redirect to login here
      // this.router.navigate(['/login']);
    }

    return next.handle(req);
  }
}
