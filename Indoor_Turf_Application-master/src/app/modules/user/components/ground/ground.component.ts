import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { BookingService } from 'src/app/services/booking.service';
import { GroundService } from 'src/app/services/ground.service';
import { booking } from 'src/app/shared/booking';
import { grounds } from 'src/app/shared/grounds';

@Component({
  selector: 'app-ground',
  templateUrl: './ground.component.html',
 
  styleUrls: ['./ground.component.css']
})
export class GroundComponent implements OnInit {
  bookGroundForm: FormGroup =new FormGroup({
    fromDate:new FormControl(''),
    toDate:new FormControl(''),
    numberOfPersons:new FormControl(''),
    description:new FormControl('')
  })
  groundId!:number;
  email!:string;
  
  
  
  
  constructor(private router:Router,private service:BookingService,private formBuilder: FormBuilder, private route:ActivatedRoute) { 
  
  }
  
  bookings=new booking();
  ngOnInit(): void {
    this.groundId=this.route.snapshot.params['groundId'];
  this.bookGroundForm = this.formBuilder.group({
    fromDate: [
      '', 
      [Validators.required, 
       
      ]
    ],
    toDate: [
      '', 
      [Validators.required]
    ],
    numberOfPersons: [
      '',
      [
        Validators.required,
      ]
    ],
    description: [
      '',
      [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(40)
      ]
    ]
  })
  }
  
  handleGround(){
  
  this.service.saveBooking(this.bookings,this.email,this.groundId).subscribe(
    data=>{
      console.log(data)
      alert("Ground Booked Successfully")
      this.router.navigate(['/user/dashboard']);
    },
    error=>{
      console.log(error)
      alert(error)
    }
  )
  }

}
