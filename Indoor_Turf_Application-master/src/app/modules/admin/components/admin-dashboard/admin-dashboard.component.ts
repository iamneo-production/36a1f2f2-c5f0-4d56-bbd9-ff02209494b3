import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { GroundService } from 'src/app/services/ground.service';
import { ground } from 'src/app/shared/ground';


@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
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

  editGround(id:number){
    this.router.navigate(['/admin/editGround' ,id]);
  }

  deleteGround(id:number){
    this.router.navigate(['/admin/deleteGround' ,id]);
  }
}
