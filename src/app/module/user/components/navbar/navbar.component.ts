import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/classes/user';
import { AuthServiceService } from 'src/app/service/auth-service.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  user!: User;
  useremail!:string;
  constructor(private auth: AuthServiceService,private router:Router) { }

  ngOnInit(): void {
  
  }
  logout(): void {
    this.auth.logout();
    
  }

  bookedground(){
    //need to pass the useremail
// this.useremail=this.auth.user.username
    this.router.navigate(['/user/bookedgrounds/',this.auth.getusermail]);
  }
}
