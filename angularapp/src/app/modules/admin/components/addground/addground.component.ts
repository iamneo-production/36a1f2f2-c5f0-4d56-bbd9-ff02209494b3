import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-addground',
  templateUrl: './addground.component.html',
  styleUrls: ['./addground.component.scss']
})
export class AddgroundComponent implements OnInit {
  GroundName=""
  GroundPrice = ""
  GroundLocation=""
  GameType = ""
  GroundDescription=""

  constructor() { }

  ngOnInit(): void {
  }
  handleGround(){
  console.log(this.GroundName);
  console.log(this.GroundPrice);
  console.log(this.GroundLocation);
  console.log(this.GameType);
  console.log(this.GroundDescription);
}
}
