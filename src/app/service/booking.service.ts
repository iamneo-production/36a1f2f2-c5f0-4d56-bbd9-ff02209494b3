import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { booking } from '../classes/booking';
import { AuthServiceService } from './auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class BookingService {
  
  constructor(private http:HttpClient,private auth:AuthServiceService) { }
  saveBooking(data:booking,email:string,groundId:number):Observable<any>{
    return this.http.put<any>("http://localhost:8080/user/addbooking/"+localStorage.getItem('email')+"/"+groundId,data);
  }
  editBooking(id:number,data:booking):Observable<any>{
    return this.http.put<any>("http://localhost:8080/user/editbookedgrounds/"+id,data);
  }
  deletebooking(id:number):Observable<any>{
    return this.http.delete<any>("http://localhost:8080/user/deletebookedgrounds/"+id);
  }
  getBooking(bookingId:number):Observable<any>{
    return this.http.get<any>("http://localhost:8080/user/getbookingById/"+bookingId);
  }
  bookedground(email:string):Observable<any>{
    return this.http.get<any>("http://localhost:8080/user/bookedgrounds/"+localStorage.getItem('email'));
  }
  
}
