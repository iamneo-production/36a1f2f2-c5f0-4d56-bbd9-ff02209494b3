import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MustMatch } from 'src/app/helpers/must-match.validator';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  signupform=new FormGroup({
    email: new FormControl(''),
    username: new FormControl(''),
    mobileNumber: new FormControl(''),
    password: new FormControl(''), 
    confirmPassword: new FormControl('')  
});
  submitted = false;
  loading = false;
  errorMessage = '';
  constructor(private formBuilder: FormBuilder,private router: Router, private route: ActivatedRoute, private auth: AuthService) { }
  ngOnInit(): void {
    this.signupform = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      username: ['', Validators.required],
      mobileNumber: ['', [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
     
  }, {
    validator: MustMatch('password', 'confirmPassword')
  });
  }
  get f(): { [key: string]: AbstractControl }{
    return this.signupform.controls; }
    onSubmit(): void {
      this.submitted=true;
      this.loading=false;
      if (this.signupform.invalid) {
        
        return;
      }
      
    if (this.signupform.valid) {
      
    } 
    }
}
