import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { grounds } from 'src/app/shared/grounds';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {
  grounds=grounds;
  constructor(private auth: AuthService) { }

  ngOnInit(): void {
  }
  logout(): void {
    this.auth.logout();
  }

}
