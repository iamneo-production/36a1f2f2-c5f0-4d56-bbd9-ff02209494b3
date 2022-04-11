import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/classes/user';
import { AuthServiceService } from 'src/app/service/auth-service.service';
import { SignupService } from 'src/app/service/signup.service';




@Component({
  selector: 'app-addadmin',
  templateUrl: './addadmin.component.html',
  styleUrls: ['./addadmin.component.scss']
})
export class AddadminComponent implements OnInit {
  
  form=new FormGroup({
    email: new FormControl(''),
    username: new FormControl(''),
    mobileNumber: new FormControl(''),
    password: new FormControl(''), 
    confirmPassword: new FormControl('') , 
    userRole:new FormControl('')
});

  

  constructor(private formbuilder:FormBuilder,private service:AuthServiceService,private router:Router,private serv: SignupService) {}
  user=new User();
  ngOnInit(): void {
    this.form = this.formbuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      mobileNumber: ['', [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
      userRole:['', Validators.required]
  }, );
  }
  onSubmit(): void {
  }
  adminSignup(){
console.log(this.user);
    this.serv.signupadmin(this.user).subscribe(
      {
      next: data=>{
        console.log(data);
        alert("Admin added Successfully")
        this.router.navigate(['/admin/displayusers']);
      },
      error: error=>{
        console.log(error);
        alert(error)
      }
    });
    
  }
}
