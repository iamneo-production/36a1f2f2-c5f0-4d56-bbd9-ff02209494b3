import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingService } from 'src/app/services/booking.service';

@Component({
  selector: 'app-deletebooking',
  templateUrl: './deletebooking.component.html',
  styleUrls: ['./deletebooking.component.css']
})
export class DeletebookingComponent implements OnInit {

  constructor(private service:BookingService,private router:Router,private route:ActivatedRoute) { }

  bookingId!:number;
  
  ngOnInit(): void {
    this.bookingId=this.route.snapshot.params['bookingId'];
  }

  deleteBook(){
    this.service.deleteBooking(this.bookingId).subscribe(
      (data)=>{
        console.log(data);
        alert("Booking deleted successfully");
        this.deleteBooking();
      },
      (error)=>{
        console.log(error);
        this.deleteBooking();
      }
    )
  }

  deleteBooking(){
    this.router.navigate(['/user/dashboard']);
  }

}
