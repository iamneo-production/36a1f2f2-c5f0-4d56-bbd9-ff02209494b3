import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-mlogin',
  templateUrl: './mlogin.component.html',
  styleUrls: ['./mlogin.component.scss']
})
export class MloginComponent implements OnInit {
  Form = new FormGroup({
    mobileNumber: new FormControl(''),
    password: new FormControl(''),
  });
  submitted = false;
  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.Form = this.formBuilder.group({  
      mobileNumber: ['', [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]],
      password: ['',
          [
            Validators.required,
            Validators.minLength(6)
          ]
        ]  
    })  
  }
  get f(){
    return this.Form.controls;
  }
  onSubmit(): void {
    this.submitted=true;
    if (this.Form.valid) {
     
        (err: Error) => {
          alert(err.message);
        }
      
    } 
  }

}
