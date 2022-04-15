import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MustMatch } from 'src/app/helpers/must-match.validator';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent implements OnInit {
  form=new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''), 
    confirmPassword: new FormControl('')  
});
  submitted = false;
  loading = false;
  errorMessage = '';
  constructor(private formBuilder: FormBuilder,private router: Router, private route: ActivatedRoute, private auth: AuthService) { }
  ngOnInit(): void {
    this.form = this.formBuilder.group({
      email: ['',[Validators.required, Validators.email]],
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
    if (this.form.valid) {
     
        (err: Error) => {
          alert(err.message);
        }
      
    }
  }
}
