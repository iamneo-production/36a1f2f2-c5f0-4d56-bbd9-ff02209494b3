import { Observable, of, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { login } from '../shared/login';
import { User } from '../shared/user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private router: Router,private http:HttpClient) {}
  isUserPresent(login:login):Observable<any>{
    return this.http.post<any>("http://localhost:8080/user/login",login);
  }
  isAdminPresent(login:login):Observable<any>{
    return this.http.post<any>("http://localhost:8080/admin/login",login);
  }
  saveUser(user:User):Observable<any>{
    return this.http.post<any>("http://localhost:8080/user/signup",user);
  }
  saveAdmin(user:User):Observable<any>{
    return this.http.post<any>("http://localhost:8080/admin/signup",user);
  }


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