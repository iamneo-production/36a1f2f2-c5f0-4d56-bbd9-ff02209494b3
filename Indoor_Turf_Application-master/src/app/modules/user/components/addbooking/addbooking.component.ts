import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { identity } from 'rxjs';
import { BookingService } from 'src/app/services/booking.service';
import { booking } from 'src/app/shared/booking';

@Component({
  selector: 'app-addbooking',
  templateUrl: './addbooking.component.html',
  styleUrls: ['./addbooking.component.css']
})
export class AddbookingComponent implements OnInit {

  bookings!:booking[];
  email!:string;

  constructor(private router:Router,private service:BookingService,private route:ActivatedRoute) { 
   
}
booking=new booking();
  ngOnInit(): void {
    this.email=this.route.snapshot.params['email'];
    this.viewBooking();
  }
  viewBooking(){
    this.service.bookedground(this.email).subscribe(
      data=>{
       this.bookings=data;
       console.log("Success");
      }
    );
  }

  editBooking(id: number){
    this.router.navigate(['/user/editbookedgrounds', id]);
  }

  deleteBooking(id: number){
    this.router.navigate(['/user/deletebookedgrounds', id]);
  }

  addReview(id:number){
    //need to pass the userid
    this.router.navigate(['/user/addreview',3,id]);
  }

}
