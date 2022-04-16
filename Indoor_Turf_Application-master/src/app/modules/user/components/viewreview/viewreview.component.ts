import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { review } from 'src/app/shared/review';
import { ReviewServiceService } from 'src/app/services/review-service.service';

@Component({
  selector: 'app-viewreview',
  templateUrl: './viewreview.component.html',
  styleUrls: ['./viewreview.component.css']
})
export class ViewreviewComponent implements OnInit {

  reviews!:review[];
  groundId!:number;
  loggedUser:number=3;

  constructor(private router:Router,private service:ReviewServiceService,private route:ActivatedRoute) { 
   
}
review=new review();
  ngOnInit(): void {
    this.groundId=this.route.snapshot.params['groundId'];
    this.viewReview();
  }
  viewReview(){
    this.service.viewReview(this.groundId).subscribe(
      data=>{
       this.reviews=data;
       console.log("Success");
      }
    );
  }

  editReview(id: number){
    this.router.navigate(['/user/editreview', id, this.groundId]);
  }

  deleteReview(id: number){
    this.router.navigate(['/user/deletereview', id, this.groundId]);
  }



}
