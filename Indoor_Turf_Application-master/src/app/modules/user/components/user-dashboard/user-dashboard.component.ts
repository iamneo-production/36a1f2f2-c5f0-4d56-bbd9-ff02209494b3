import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { GroundService } from 'src/app/services/ground.service';
import { ground } from 'src/app/shared/ground';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {
  grounds!:ground[];
  
  constructor(private service:GroundService,private router:Router) { }

  ngOnInit(): void {
    this.getGrounds();
  }

  private getGrounds(){
    this.service.getAllGrounds().subscribe(
      data=>{
        this.grounds=data;
      },
      error=>{
        console.log(error);
      }
    );
  }

  bookGround(id:number){
    this.router.navigate(['/user/Ground' ,id]);
  }

  viewReview(groundId:number){
    this.router.navigate(['user/viewreview', groundId]);
  }

  addBooking(groundId:number){
    this.router.navigate(['user/ground',groundId]);
  }

}
