import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingService } from 'src/app/services/booking.service';
import { booking } from 'src/app/shared/booking';

@Component({
  selector: 'app-editbooking',
  templateUrl: './editbooking.component.html',
  styleUrls: ['./editbooking.component.css']
})
export class EditbookingComponent implements OnInit {


  editBookGroundForm: FormGroup =new FormGroup({
    fromDate:new FormControl(''),
    toDate:new FormControl(''),
    numberOfPersons:new FormControl(''),
    description:new FormControl('')
  })

  bookingId!:number;

  constructor(private router:Router,private service:BookingService,
  private route:ActivatedRoute) { 
}

booking=new booking();
ngOnInit(): void {
  this.bookingId=this.route.snapshot.params['bookingId'];
  this.service.getBooking(this.bookingId).subscribe(
    data=>{
      this.booking=data;
    },
    error=>{
      console.log(error);
    }
  )
  

  
}
editBooking(){
  
  this.service.editBooking(this.bookingId,this.booking).subscribe(
    data=>{
      console.log(data)
      //navigate to the ground 
      alert("Ground edited Successfully")
      this.router.navigate(['/user/dashboard']);
    },
    error=>{
      console.log(error)
      this.router.navigate(['/user/dashboard']);
    }
  )
}

}
