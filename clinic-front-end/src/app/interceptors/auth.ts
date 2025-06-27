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
    constructor(private jwtService:JwtService){

    }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("interceptor callled ")
  const token = this.jwtService.getToken() // or from AuthService
  console.log(token, "token")
    console.log(req.url.includes('/login'))
  if (token &&  !req.url.includes('/login')) {
    console.log("cloned req")
    const cloned = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next.handle(cloned);
  }

  return next.handle(req);
}

}
