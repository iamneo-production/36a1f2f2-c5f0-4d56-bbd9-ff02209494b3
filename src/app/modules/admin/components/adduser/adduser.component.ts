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
  msg!: string;

  

  constructor(private serv:SignupService,private router:Router) {}

  ngOnInit(): void {
    
  }
  onSubmit(): void {
  }
  signupUser(){
    this.serv.signupUser(this.user).subscribe(
      {
        next: data=>{
          console.log("registered succesfully")
          this.router.navigate(['/login']);
        },
        error: err=>{
          console.log(this.user);
          console.log(err.msg);
          this.msg='bad details';
        }
      });

  }
}
