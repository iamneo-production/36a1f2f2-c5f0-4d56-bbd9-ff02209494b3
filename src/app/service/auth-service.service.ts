import { Injectable } from '@angular/core';

import { BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { User } from '../classes/user';
import { SignupService } from './signup.service';
import { Router } from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  public _isLoggedIn=new BehaviorSubject<boolean>(false);
  private readonly TOKEN_NAME='Access';
  isLoggedIn=this._isLoggedIn.asObservable();
  user!: User;
  
  get token():any{
    return localStorage.getItem(this.TOKEN_NAME);
  }
  constructor(private serv:SignupService,private router:Router) { 
    this._isLoggedIn.next(!!this.token);
    this.user=this.getUser(this.token);
  }

  login(user:User){
    return this.serv.loginUser(user).pipe(
      tap((response:any)=>{
        this._isLoggedIn.next(true);
        localStorage.setItem(this.TOKEN_NAME,response.token);
        this.user=this.getUser(response.token);
        
      })
    );
  }
  private getUser(token:string): any {
    if(localStorage.getItem('Access')!==null){
      console.log(JSON.parse(atob(token.split('.')[1])));
return JSON.parse(atob(token.split('.')[1])) as User;
  }
  else return null;
}
logout(){
  localStorage.clear();
  this.router.navigate(['/login']);
  this._isLoggedIn.next(false);
}
}


