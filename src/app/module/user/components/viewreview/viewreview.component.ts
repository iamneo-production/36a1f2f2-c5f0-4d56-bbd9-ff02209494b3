import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ground } from 'src/app/classes/ground';
import { Review } from 'src/app/classes/review';
import { GroundService } from 'src/app/service/ground.service';
import { ReviewServiceService } from 'src/app/service/review-service.service';

@Component({
  selector: 'app-viewreview',
  templateUrl: './viewreview.component.html',
  styleUrls: ['./viewreview.component.css']
})
export class ViewreviewComponent implements OnInit {

  reviews!:Review[];
  groundId!:any;
  loggedUser:string="user2@gmaail.com";
  ground!:ground;
  name!:String;

  constructor(private router:Router,private service:ReviewServiceService,private route:ActivatedRoute,private groundservice:GroundService) { 
   
}
review=new Review();
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
