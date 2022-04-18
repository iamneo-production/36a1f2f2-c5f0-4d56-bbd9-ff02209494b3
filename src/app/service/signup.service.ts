import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../classes/user';
import{HttpClient, HttpHeaders} from '@angular/common/http'
@Injectable({
  providedIn: 'root'
})
export class SignupService {

  requestHeader=new HttpHeaders(
    {
      "No-Auth":"True"
    }
  );

  
  constructor(private http: HttpClient) { }

  public loginUser(user: User):Observable<any>{
return this.http.post('http://localhost:8080/user/authenticate',user,{headers:this.requestHeader});
  }


public signupUser(user: User):Observable<any>{
  return this.http.post('http://localhost:8080/user/register',user)
    }

    public signupadmin(user:User):Observable<any>{
return this.http.post('http://localhost:8080/admin/registeradmin',user)
    }

    public getuseremail(){
      return this.http.get('http://localhost:8080/user/api/loggeduser',{responseType: 
      'text'})
    }
}