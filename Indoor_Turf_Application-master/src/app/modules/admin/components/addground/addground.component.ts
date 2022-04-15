import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { GroundService } from 'src/app/services/ground.service';
import { ground } from 'src/app/shared/ground';

@Component({
  selector: 'app-addground',
  templateUrl: './addground.component.html',
  styleUrls: ['./addground.component.css']
})
export class AddgroundComponent implements OnInit {
  
addGroundForm: FormGroup =new FormGroup({
  groundName:new FormControl(''),
  imageURL:new FormControl(''),
  groundAddress:new FormControl(''),
  groundDescription:new FormControl(''),
  capacity:new FormControl(''),
  price:new FormControl('')
})





constructor(private router:Router,private service:GroundService,private formBuilder: FormBuilder) { 

}

grounds=new ground();
ngOnInit(): void {
this.addGroundForm = this.formBuilder.group({
  groundName: [
    '', 
    [Validators.required, 
     
    ]
  ],
  imageURL: [
    '', 
    [Validators.required]
  ],
  groundAddress: [
    '',
    [
      Validators.required,
    ]
  ],
  groundDescription: [
    '',
    [
      Validators.required,
      Validators.minLength(6),
      Validators.maxLength(40)
    ]
  ],
  capacity: [
    '',
    [
      Validators.required,
    ]
  ],
  price: [
    '',
    [
      Validators.required,
    ]
  ]
})
}

handleGround(){

this.service.addGround(this.grounds).subscribe(
  data=>{
    console.log(data)
    alert("Ground added Successfully")
    this.router.navigate(['/admin/dashboard']);
  },
  error=>{
    console.log(error)
    alert(error)
  }
)
}
}

