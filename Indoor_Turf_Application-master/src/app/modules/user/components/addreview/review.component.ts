import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ReviewServiceService } from 'src/app/services/review-service.service';
import { review } from 'src/app/shared/review';


@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {

  userEmail!:string
  groundId!:number

  addReviewForm: FormGroup =new FormGroup({
    review:new FormControl('')
  })
  constructor(private router:Router,private service:ReviewServiceService,private formbuilder:FormBuilder,private route:ActivatedRoute) { }

  review=new review();
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
