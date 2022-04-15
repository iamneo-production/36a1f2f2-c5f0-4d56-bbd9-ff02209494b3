import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { review } from 'src/app/shared/review';
import { ReviewServiceService } from 'src/app/services/review-service.service';

@Component({
  selector: 'app-deletereview',
  templateUrl: './deletereview.component.html',
  styleUrls: ['./deletereview.component.css']
})
export class DeletereviewComponent implements OnInit {
  id!:number;
  groundId!:number;
  item!:review[];

  constructor(private service:ReviewServiceService,private route:ActivatedRoute,private router:Router) { }
  review=new review();
  
  ngOnInit(): void {
    this.id=this.route.snapshot.params['id'];
    this.groundId=this.route.snapshot.params['groundId'];

   this.service.getReviewById(this.id).subscribe(
     (data)=>{
       this.review=data;
     },
       (error)=>{console.log(error)});
   }
  
   onSubmit(){       
   }
  
   deleteReview(){
     this.service.deleteReview(this.id,this.groundId).subscribe(
       (data)=>{
         console.log(data);
        alert("Deleted Successfully");
        this.goToViewReview();
         //navigate to ground dashboard
       },
       error=>{
         console.log(error);
         this.router.navigate(['/user/viewreview' ,this.groundId]);
       }
       );
      }

   goToViewReview(){
    this.router.navigate(['/user/viewreview' ,this.groundId]);
   }
   

}
