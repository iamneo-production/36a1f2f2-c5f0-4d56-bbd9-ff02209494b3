import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { MustMatch } from 'src/app/helpers/must-match.validator';
import { User } from 'src/app/shared/user';

@Component({
  selector: 'app-addadmin',
  templateUrl: './addadmin.component.html',
  styleUrls: ['./addadmin.component.css']
})
export class AddadminComponent implements OnInit {
  
  form=new FormGroup({
    email: new FormControl(''),
    userName: new FormControl(''),
    mobile: new FormControl(''),
    password: new FormControl(''), 
    confirmPassword: new FormControl('') , 
    role:new FormControl('')
});

  

  constructor(private formbuilder:FormBuilder,private service:AuthService,private router:Router) {}
  user=new User();
  ngOnInit(): void {
    this.form = this.formbuilder.group({
      userName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      mobile: ['', [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
      role:['', Validators.required]
  }, {
    validator: MustMatch('password', 'confirmPassword')
  });
  }
  onSubmit(): void {
  }
  adminSignup(){

    this.service.saveAdmin(this.user).subscribe(
      data=>{
        console.log(data);
        alert("Admin added Successfully")
        this.router.navigate(['/admin/displayusers']);
      },
      error=>{
        console.log(error);
        alert(error)
      }
    )
  }
}
