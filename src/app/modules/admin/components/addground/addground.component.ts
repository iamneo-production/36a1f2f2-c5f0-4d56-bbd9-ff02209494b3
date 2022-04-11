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
  addGroundForm: FormGroup =new FormGroup({
    addName:new FormControl(''),
    addImageUrl:new FormControl(''),
    addAddress:new FormControl(''),
    addDescription:new FormControl(''),
    addTiming:new FormControl(''),
    addPrice:new FormControl('')
  })
 
  ground=new ground();
  constructor(private service:GroundService,private router:Router,private formBuilder:FormBuilder) { }

  ngOnInit(): void {
    this.addGroundForm = this.formBuilder.group({
      addName: [
        '', 
        [Validators.required, 
         
        ]
      ],
      addImageUrl: [
        '', 
        [Validators.required]
      ],
      addAddress: [
        '',
        [
          Validators.required,
        ]
      ],
      addDescription: [
        '',
        [
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(40)
        ]
      ],
      addTiming: [
        '',
        [
          Validators.required,
        ]
      ],
      addPrice: [
        '',
        [
          Validators.required,
        ]
      ]
    })
  }
  onSubmitadd(f: NgForm) {
        
    this.service.addGround(f.value)
      .subscribe((result) => {
        this.ngOnInit(); //reload the table
      });
     
  }
}
