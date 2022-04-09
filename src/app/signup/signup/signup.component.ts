import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/classes/user';
import { NgForm } from '@angular/forms';
import { SignupService } from 'src/app/service/signup.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
user=new User();
msg='';
  constructor(private serv: SignupService,private router :Router) { }

  ngOnInit(): void {
  }

  signupUser(){
    this.serv.signupUser(this.user).subscribe(
      {
        next: data=>{
          console.log("registered succesfully")
          this.router.navigate(['/dashboard']);
        },
        error: err=>{
          console.log(err.msg);
          this.msg='bad details';
        }
      });

  }
}
