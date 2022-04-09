import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/classes/user';
import { AuthServiceService } from 'src/app/service/auth-service.service';
import { SignupService } from 'src/app/service/signup.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 user=new User();
 msg='';
  constructor(private authService:AuthServiceService,private router:Router) { }

  ngOnInit(){
  }

  loginUsers(){
this.authService.login(this.user).subscribe(
  {
 next: data=>{
   console.log("response recieved");
   console.log(data);
   this.router.navigate(['/dashboard']);

 },
  error: err=>{console.log(err.msg);
  this.msg='Bad credentials';
  
  }
  });
  
}
  }
