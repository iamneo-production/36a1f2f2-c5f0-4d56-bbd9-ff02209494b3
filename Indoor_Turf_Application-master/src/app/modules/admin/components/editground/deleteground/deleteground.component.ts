import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GroundService } from 'src/app/services/ground.service';

@Component({
  selector: 'app-deleteground',
  templateUrl: './deleteground.component.html',
  styleUrls: ['./deleteground.component.css']
})
export class DeletegroundComponent implements OnInit {
  constructor(private service:GroundService,private router:Router,private route:ActivatedRoute) { }

  id!:number;
  ngOnInit(): void {

    this.id=this.route.snapshot.params['id'];

  }

  delete(){
    this.service.deleteGround(this.id).subscribe(
      data=>{
        console.log(data);
        alert("Ground Deleted Successfully");
        this.deleteGround();
      },
      error=>{
        console.log(error);
      }
    )
  }

  deleteGround(){
    this.router.navigate(['/admin/dashboard']);
  }
  
}
