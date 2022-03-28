import { Component, OnInit } from '@angular/core';
import { grounds } from 'src/app/shared/grounds';

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit {
  grounds=grounds;
  constructor() { }

  ngOnInit(): void {
  }

}
