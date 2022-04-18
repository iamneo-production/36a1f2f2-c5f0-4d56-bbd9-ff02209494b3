import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ground } from 'src/app/classes/ground';
import { GroundService } from 'src/app/service/ground.service';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit {
  grounds!:ground[];
  closeResult!: string;
  groundbookId: any;
  constructor(private service:GroundService,private router:Router,private modalService:NgbModal) { }

  ngOnInit(): void {
    this.getGrounds();
  }

  private getGrounds(){
    this.service.getAllGroundsUser().subscribe({
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

  public searchground(key:string):void{
    const results:ground[]=[];
    for(const ground of this.grounds ){
      if(ground.groundName.toLowerCase().indexOf(key.toLowerCase())!== -1
      || (ground.price==key)
      || ground.groundAddress.toLowerCase().indexOf(key.toLowerCase())!== -1){
        results.push(ground);
      }
    }
    this.grounds=results;
    if(results.length===0 || !key){
      this.getGrounds();
    }
  }



  viewReview(groundId:string){
    this.router.navigate(['user/viewreview', groundId]);
  }

  addBooking(groundId:string){
    this.router.navigate(['user/ground',groundId]);
  }










  
}
