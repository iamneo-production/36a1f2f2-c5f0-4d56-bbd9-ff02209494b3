import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Review } from 'src/app/classes/review';
import { ReviewServiceService } from 'src/app/service/review-service.service';

@Component({
  selector: 'app-addreview',
  templateUrl: './addreview.component.html',
  styleUrls: ['./addreview.component.css']
})
export class AddreviewComponent implements OnInit {

  userEmail!:string
  groundId!:number

  addReviewForm: FormGroup =new FormGroup({
    review:new FormControl('')
  })
  constructor(private router:Router,private service:ReviewServiceService,private formbuilder:FormBuilder,private route:ActivatedRoute) { }

  review=new Review();
  ngOnInit(): void {
    this.userEmail=this.route.snapshot.params['userEmail'];
    this.groundId=this.route.snapshot.params['groundId'];
  this.addReviewForm = this.formbuilder.group({
    review: [
      '', 
      [Validators.required, 
       
      ]
    ]
  })
  }
  
  addReview(){
  
  this.service.addReview(this.userEmail,this.groundId,this.review).subscribe(
    data=>{
      console.log(data)
      alert("Review added Successfully")
      this.router.navigate(['/user/dashboard'])
    },
    error=>{
      console.log(error)
      this.router.navigate(['/user/dashboard'])
    }
  )
  }

}
