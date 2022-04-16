import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { review } from 'src/app/shared/review';
import { ReviewServiceService } from 'src/app/services/review-service.service';
import { GroundService } from 'src/app/services/ground.service';
import { Ground } from 'src/app/shared/grounds';
import { ground } from 'src/app/shared/ground';

@Component({
  selector: 'app-viewreview',
  templateUrl: './viewreview.component.html',
  styleUrls: ['./viewreview.component.css']
})
export class ViewreviewComponent implements OnInit {

  reviews!:review[];
  groundId!:number;
  loggedUser:string="user2@gmaail.com";
  ground!:ground;
  name!:String;

  constructor(private router:Router,private service:ReviewServiceService,private route:ActivatedRoute,private groundservice:GroundService) { 
   
}
review=new review();
  ngOnInit(): void {
    this.groundId=this.route.snapshot.params['groundId'];
    this.findGround();
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

  findGround(){
    this.groundservice.getGroundById(this.groundId).subscribe(
      data=>{
        this.ground=data;
        this.name=this.ground.groundName;
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
