import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingService } from 'src/app/service/booking.service';

@Component({
  selector: 'app-deletebooking',
  templateUrl: './deletebooking.component.html',
  styleUrls: ['./deletebooking.component.scss']
})
export class DeletebookingComponent implements OnInit {

  constructor(private service:BookingService,private router:Router,private route:ActivatedRoute) { }

  bookingId!:number;
  
  ngOnInit(): void {
    this.bookingId=this.route.snapshot.params['id'];
  }

  deleteBook(){
    this.service.deletebooking(this.bookingId).subscribe({
    

    next:  (data)=>{
        console.log(data);
        alert("Booking deleted successfully");
        this.deleteBooking();
      },
     error: (error)=>{
        console.log(error);
        alert(error)
        this.deleteBooking();
      }
    })
  }

 deleteBooking(){
   this.router.navigate(['/user/dashboard']);
  }
}
