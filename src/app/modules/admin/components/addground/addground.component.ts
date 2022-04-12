import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ground } from 'src/app/classes/ground';
import { GroundService } from 'src/app/service/ground.service';



@Component({
  selector: 'app-addground',
  templateUrl: './addground.component.html',
  styleUrls: ['./addground.component.scss']
})
export class AddgroundComponent implements OnInit {
  
  constructor(private service:GroundService,private router:Router,private formBuilder:FormBuilder) { }

  ngOnInit(): void {
  }
  onSubmitadd(f: NgForm) {
        
    this.service.addGround(f.value)
      .subscribe((result) => {
        this.ngOnInit(); //reload the table
        this.router.navigate(['./dashboard']);
      });
     
  }
}
