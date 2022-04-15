import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  constructor(private auth: AuthService,private router:Router) { }


  email!:string;
  ngOnInit(): void {

  }

  logout(): void {
    this.auth.logout();
  }
  
  bookedground(){
    //need to pass the useremail
    this.router.navigate(['/user/bookedgrounds',"user2@gmaail.com"]);
  }
  
}
