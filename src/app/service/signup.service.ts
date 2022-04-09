import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../classes/user';
import{HttpClient} from '@angular/common/http'
@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor(private http: HttpClient) { }

  public loginUser(user: User):Observable<any>{
return this.http.post('http://localhost:8080/user/authenticate',user)
  }


public signupUser(user: User):Observable<any>{
  return this.http.post('http://localhost:8080/user/register',user)
    }
}