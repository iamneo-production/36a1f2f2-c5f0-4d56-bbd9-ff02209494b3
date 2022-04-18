import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { booking } from 'src/app/classes/booking';
import { AuthServiceService } from 'src/app/service/auth-service.service';
import { BookingService } from 'src/app/service/booking.service';

@Component({
  selector: 'app-addbooking',
  templateUrl: './addbooking.component.html',
  styleUrls: ['./addbooking.component.scss']
})
export class AddbookingComponent implements OnInit {

  bookings!:booking[];
  email!:string;
  groundId!:number;

  constructor(private router:Router,private service:BookingService,private route:ActivatedRoute,private auth:AuthServiceService) { 
   
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
    this.router.navigate(['/user/addreview',this.auth.usermail,id]);
  }



}
