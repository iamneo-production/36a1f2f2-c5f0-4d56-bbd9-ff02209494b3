import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/classes/user';
import { SignupService } from 'src/app/service/signup.service';


@Component({
  selector: 'app-adduser',
  templateUrl: './adduser.component.html',
  styleUrls: ['./adduser.component.scss']
})
export class AdduserComponent implements OnInit {
  user=new User();
  form=new FormGroup({
    email: new FormControl(''),
    username: new FormControl(''),
    mobileNumber: new FormControl(''),
    password: new FormControl(''), 
    confirmPassword: new FormControl('') , 
    userRole:new FormControl('')
});

  

  constructor(private formbuilder:FormBuilder,private service:SignupService,private router:Router) {}

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
  userSignup(){

    this.service.signupUser(this.user).subscribe({
      next: data=>{
        console.log(data);
        alert("User added Successfully")
        this.router.navigate(['/admin/displayusers']);
      },
      error: err=>{
        console.log(err);
        alert(err);
      }
    });
  }
}
