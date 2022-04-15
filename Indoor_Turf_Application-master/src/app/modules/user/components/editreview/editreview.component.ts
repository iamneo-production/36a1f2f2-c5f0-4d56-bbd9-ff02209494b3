import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { review } from 'src/app/shared/review';
import { ReviewServiceService } from 'src/app/services/review-service.service';

@Component({
  selector: 'app-editreview',
  templateUrl: './editreview.component.html',
  styleUrls: ['./editreview.component.css']
})
export class EditreviewComponent implements OnInit {

  id!:number;
  groundId!:number;

  editReviewForm: FormGroup =new FormGroup({
    review:new FormControl('')
  })

constructor(private router:Router,private service:ReviewServiceService,
  private route:ActivatedRoute) { 
}

review=new review();
ngOnInit(): void {
  this.id=this.route.snapshot.params['id'];
  this.groundId=this.route.snapshot.params['groundId'];
  this.service.getReviewById(this.id).subscribe(
    data=>{
      this.review=data;
    },
    error=>{
      console.log(error);
    }
  )
  

  
}
editReview(){
  
  this.service.editReview(this.id,this.groundId,this.review).subscribe(
    data=>{
      console.log(data)
      //navigate to the ground 
      alert("Ground edited Successfully")
      this.router.navigate(['/user/viewreview',this.groundId]);
    },
    error=>{
      console.log(error)
      this.router.navigate(['/user/viewreview',this.groundId]);
    }
  )
}

}
