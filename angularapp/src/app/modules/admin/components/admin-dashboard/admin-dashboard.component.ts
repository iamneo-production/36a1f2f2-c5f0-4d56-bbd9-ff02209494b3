import { Component, OnInit } from '@angular/core';
import { grounds } from 'src/app/shared/grounds';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
  grounds=grounds;
  constructor() { }

  ngOnInit(): void {
  }

}
