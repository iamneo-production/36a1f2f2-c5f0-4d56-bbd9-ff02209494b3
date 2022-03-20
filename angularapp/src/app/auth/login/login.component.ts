import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  Form = new FormGroup({
    email: new FormControl('',[Validators.required, Validators.email]),
    password: new FormControl('',[
      Validators.required,
      Validators.minLength(5),
      Validators.maxLength(15)
    ]),
  });
  submitted = false;
  loading=false;
  errormessage=false;
  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit(): void {
    if (this.auth.isLoggedIn()) {
      this.router.navigate(['user']);
    }
  }
  get f(){
    return this.Form.controls;
  }
  onSubmit(): void {
    this.submitted = true;
    if (this.Form.valid) {
      this.auth.login(this.Form.value).subscribe(
        (result) => {
          console.log(result);
        },
        (err: Error) => {
          alert(err.message);
        }
      );
    } 
  }
}
