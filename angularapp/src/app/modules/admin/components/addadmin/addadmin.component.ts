import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-addadmin',
  templateUrl: './addadmin.component.html',
  styleUrls: ['./addadmin.component.scss']
})
export class AddadminComponent implements OnInit {
  firstName=""
  lastName=""
  emailId=""
  PhoneNumber=""
  password=""
  repassword=""

  constructor() { }

  ngOnInit(): void {
  }
  adminSignup(){
  console.log(this.firstName);
  console.log(this.lastName);
  console.log(this.emailId);
  console.log(this.PhoneNumber);
  console.log(this.password);
  console.log(this.repassword);
  }
}
