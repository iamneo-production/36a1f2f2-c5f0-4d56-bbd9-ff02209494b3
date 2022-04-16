import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { review } from '../shared/review';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReviewServiceService {
  
  constructor(private router:Router,private http:HttpClient) { }

  addReview(userEmail:string,groundId:number,review:review):Observable<any>{
    return this.http.post<any>("http://localhost:8080/user/addreview/"+userEmail+"/"+groundId,review);
  }

  viewReview(groundId:number):Observable<any>{
    return this.http.get<any>("http://localhost:8080/user/viewreview/"+groundId);
  }

  editReview(id: number, groundId: number, review: review) {
    return this.http.put<any>("http://localhost:8080/user/editreview/"+id+"/"+groundId,review);
  }

  deleteReview(id:number,groundId:number):Observable<any>{
    return this.http.delete<any>("http://localhost:8080/user/deletereview/"+id+"/"+groundId);
  }

  getReviewById(id:number):Observable<any>{
    return this.http.get<any>("http://localhost:8080/user/getReviewById/"+id);
  }

}
