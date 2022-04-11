import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ground } from 'src/app/classes/ground';
import { GroundService } from 'src/app/service/ground.service';



@Component({
  selector: 'app-editground',
  templateUrl: './editground.component.html',
  styleUrls: ['./editground.component.scss']
})
export class EditgroundComponent implements OnInit {
  id!:string;

  editGroundForm: FormGroup =new FormGroup({
    groundName:new FormControl(''),
    imageURL:new FormControl(''),
    groundAddress:new FormControl(''),
    groundDescription:new FormControl(''),
    capacity:new FormControl(''),
    price:new FormControl('')
  })

constructor(private router:Router,private service:GroundService,
  private route:ActivatedRoute) { 
}

ground=new ground();
ngOnInit(): void {
  this.id=this.route.snapshot.params['id'];
  this.service.getGroundById(this.id).subscribe(
    data=>{
      this.ground=data;
    },
    error=>{
      console.log(error);
    }
  )
  

  
}
handleGround(){
  
  this.service.editGround(this.ground).subscribe(
    data=>{
      console.log(data)
      alert("Ground edited Successfully")
      this.router.navigate(['/admin/dashboard']);
    },
    error=>{
      console.log(error)
      alert(error)
    }
  )
}
}
