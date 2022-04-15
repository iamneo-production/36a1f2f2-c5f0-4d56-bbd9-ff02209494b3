import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-deleteuser',
  templateUrl: './deleteuser.component.html',
  styleUrls: ['./deleteuser.component.scss']
})
export class DeleteuserComponent implements OnInit {
  constructor(private service:UserService,private router:Router,private route:ActivatedRoute) { }

  id!:string;
  ngOnInit(): void {

    this.id=this.route.snapshot.params['id'];

  }

  delete(){
    this.service.deleteuser(this.id).subscribe(
      data=>{
        console.log(data);
        alert("User Deleted Successfully");
        this.router.navigate(['/admin/displayusers']);
      },
      error=>{
        console.log(error);
      }
    )
  }
  
}
