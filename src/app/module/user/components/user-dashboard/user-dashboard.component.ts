import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ground } from 'src/app/classes/ground';
import { GroundService } from 'src/app/service/ground.service';


@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit {
  grounds!:ground[];
  constructor(private service:GroundService,private router:Router) { }

  ngOnInit(): void {
    this.getGrounds();
  }

  private getGrounds(){
    this.service.getAllGrounds().subscribe({
      next: data=>{
        this.grounds=data;
      },
     error: err=>{
        console.log(err);
      }
    });
  }

  bookGround(id:string){
    this.router.navigate(['/user/Ground' ,id]);
  }
}
