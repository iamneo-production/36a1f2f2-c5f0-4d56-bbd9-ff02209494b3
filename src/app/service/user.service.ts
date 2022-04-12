import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../classes/user';
import { Userd } from '../classes/userd';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }
  updateUser: any;
  getUserbyid: any;
  
  private baseURL = "https://8080-cefcccadbaddebfdaffdacedbbebcbf.examlyiopb.examly.io/api/v1/Users";
  
  getUserList(): Observable<Userd[]>
  {
    return this.http.get<Userd[]>("http://localhost:8080/admin/getUsers");
  }
  createUser(user:User):Observable<any>
  {
    return this.http.post<any>("http://localhost:8080/admin/adduser",user);
  }
  getUserById(id: string) :Observable<any>
  {
    return this.http.get<any> ("http://localhost:8080/admin/user/"+id);
  }
  edituser(id: string, user: User): Observable<any>{
    return this.http.put<any>("http://localhost:8080/admin/edituser/"+id, user);
  }

  deleteuser(id: string): Observable<Object>{
    return this.http.delete("http://localhost:8080/admin/deleteuser/"+id);
  }
  
}
