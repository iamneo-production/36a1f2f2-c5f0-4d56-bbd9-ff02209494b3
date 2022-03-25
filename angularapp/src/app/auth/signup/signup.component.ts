import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MustMatch } from 'src/app/helpers/must-match.validator';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  form=new FormGroup({
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
    this.form = this.formBuilder.group({
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
    return this.form.controls; }
    onSubmit(): void {
      this.submitted=true;
      this.loading=false;
      if (this.form.invalid) {
        
        return;
      }
      
    if (this.form.valid) {
      
    } 
    }
}
