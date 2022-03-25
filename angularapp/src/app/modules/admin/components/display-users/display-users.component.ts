import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MustMatch } from 'src/app/helpers/must-match.validator';

@Component({
  selector: 'app-display-users',
  templateUrl: './display-users.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./display-users.component.css']
})
export class DisplayUsersComponent implements OnInit {
  form=new FormGroup({
    username: new FormControl(''),
    email: new FormControl(''),
    role: new FormControl('User'),
    mobileNumber: new FormControl(''),
    password: new FormControl(''), 
    confirmPassword: new FormControl('')  
});

  closeResult!: string;

  constructor(private modalService: NgbModal, private formbuilder:FormBuilder) {}

  openBackDropCustomClass(content: any) {
    this.modalService.open(content, {backdropClass: 'light-blue-backdrop'});
  }
  ngOnInit(): void {
    this.form = this.formbuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      mobileNumber: ['', [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
     
  }, {
    validator: MustMatch('password', 'confirmPassword')
  });
  }
  onSubmit(): void {
  }
}
