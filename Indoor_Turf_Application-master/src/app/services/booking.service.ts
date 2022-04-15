import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { booking } from '../shared/booking';

@Injectable({
  providedIn: 'root'
})
export class BookingService {
  
  constructor(private http:HttpClient) { }
  saveBooking(data:booking,email:string,groundId:number):Observable<any>{
    return this.http.put<any>("http://localhost:8080/user/addBooking/"+"user2@gmaail.com"+"/"+groundId,data);
  }
  editBooking(id:number,data:booking):Observable<any>{
    return this.http.put<any>("http://localhost:8080/user/editbookedgrounds/"+id,data);
  }
  deleteBooking(id:number):Observable<any>{
    return this.http.delete<any>("http://localhost:8080/user/deletebookedgrounds/"+id);
  }
  getBooking(bookingId:number):Observable<any>{
    return this.http.get<any>("http://localhost:8080/user/getBookingById/"+bookingId);
  }
  bookedground(email:string):Observable<any>{
    return this.http.get<any>("http://localhost:8080/user/bookedgrounds/"+email);
  }
  
}
