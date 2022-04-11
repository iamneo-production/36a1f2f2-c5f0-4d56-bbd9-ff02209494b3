import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/classes/user';
import { UserService } from 'src/app/service/user.service';




@Component({
  selector: 'app-edituser',
  templateUrl: './edituser.component.html',
  styleUrls: ['./edituser.component.scss']
})
export class EdituserComponent implements OnInit {
  id!:string;
  user=new User();
  form=new FormGroup({
    email: new FormControl(''),
    username: new FormControl(''),
    mobileNumber: new FormControl(''),
    password: new FormControl(''), 
    confirmPassword: new FormControl('') , 
    userRole:new FormControl('')
});

  

  constructor(private formbuilder:FormBuilder,private service:UserService,private router:Router,private route:ActivatedRoute) {}

  ngOnInit(): void {
    this.form = this.formbuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      mobileNumber: ['', [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
      userRole:['', Validators.required]
  });
  this.id=this.route.snapshot.params['id'];
  this.service.getUserById(this.id).subscribe(
    {
    next: data=>{
      this.user=data;
    },
   error:  err=>{
      console.log(err);
    }
  });
  }
  onSubmit(): void {
  }
  editUser(){

    this.service.edituser(this.id,this.user).subscribe({
      next: data=>{
        console.log(data);
        alert("User edited Successfully")
        this.router.navigate(['/admin/displayusers']);
      },
     error: err=>{
        console.log(err)
        alert(err)
      }
    });
  }
}
