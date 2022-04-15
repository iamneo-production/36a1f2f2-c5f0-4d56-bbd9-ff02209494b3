import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ground } from '../shared/ground';

@Injectable({
  providedIn: 'root'
})
export class GroundService {

  constructor(private http:HttpClient) { }
  addGround(ground:ground):Observable<any>{
    return this.http.post<any>("http://localhost:8080/admin/addGround",ground);
  }
  editGround(id:number,ground:ground):Observable<any>{
    return this.http.put<any>("http://localhost:8080/admin/editGround/"+id,ground);
  }
  deleteGround(id:number):Observable<any>{
    return this.http.delete<any>("http://localhost:8080/admin/deleteGround/"+id);
  }
  getGroundById(id:number):Observable<any>{
    return this.http.get<any>("http://localhost:8080/admin/viewground/"+id);
  }
  getAllGrounds():Observable<ground[]>{
    return this.http.get<ground[]>("http://localhost:8080/admin/dashboard");
  }
  bookGround(id:number):Observable<any>{
    return this.http.post<any>("http://localhost:8080/user/ground",id);
  }
  getGroundByName(name:string):Observable<any>{
    return this.http.get<any>("http://localhost:8080/getGroundByName/"+name);
  }
}
