import { Observable, of, throwError } from 'rxjs';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private router: Router) {}

  setToken(token: string): void {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn() {
    return this.getToken() !== null;
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['login']);
  }

  login({ email, password }: any): Observable<any> {
    if (email === 'admin@gmail.com' && password === 'admin') {
      this.setToken('abcdefghijklmnopqrstuvwxyz');
      this.router.navigate(['/admin']);
      return of({ name: 'admin', email: 'admin@gmail.com' });
    }
    if (email === 'user@gmail.com' && password === 'user123') {
      this.setToken('abcdefghijklmnopqrstuvwxyz');
      this.router.navigate(['/user']);
      return of({ name: 'user', email: 'user@gmail.com' });
    }
    return throwError(new Error('Failed to login'));
  }
}