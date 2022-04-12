import { Component, OnInit} from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ModalDismissReasons,NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpClient } from '@angular/common/http';
import { UserService } from './../../../../service/user.service';
import { Roles } from 'src/app/classes/roles';
import { Userd } from 'src/app/classes/userd';



@Component({
  selector: 'app-display-users',
  templateUrl: './display-users.component.html',
  styleUrls: ['./display-users.component.scss']
})
export class DisplayUsersComponent implements OnInit {

users!:Userd[];
editUser!: Userd;
editFormUser!:FormGroup;
roles!:Roles[];
role!:Roles;
  closeResult!: string;
  delId: string | undefined;
  constructor(private formbuilder:FormBuilder,private modalService:NgbModal,private httpClient:HttpClient,private service:UserService) {


  }

  ngOnInit(): void {
  this.getUsers();
  this.editFormUser=this.formbuilder.group({
    userId: [''],
    email: [''],
    username: [''],
    mobile: [''],
    bookings: [''],
  });
  }
 

private getUsers(){
  this.service.getUserList().subscribe({
    next: (data)=>{
       this.users=data;
       console.log(this.users);
     },
     error:(err)=>{
       console.log(err);
     }
     
    });
}



onSaveuser(){

  const editURL = 'http://localhost:8080/admin/editGround/' + this.editFormUser.value.groundId ;
    console.log(this.editFormUser.value);
    this.httpClient.put(editURL, this.editFormUser.value)
      .subscribe((results) => {
        this.ngOnInit();
        this.modalService.dismissAll();
      });
}






  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }



  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }

}

openEditUser(contentEdit: any,user: Userd){
  this.modalService.open(contentEdit, {
    centered: true,
    backdrop: 'static',
    size: 'lg'
  });
  this.editFormUser.patchValue( {
    userId: user.userId, 
    email: user.email,
    username: user.username,
    mobile: user.mobile,
  });
}


openDeleteUser(contentDelete: any, user:Userd) {
  this.delId = user.userId;
this.modalService.open(contentDelete, {
 backdrop: 'static',
 size: 'lg'
});
}

onDelete(){
  const deleteURL = 'http://localhost:8080/admin/deleteUser/' + this.delId;
    this.httpClient.delete(deleteURL)
      .subscribe((results) => {
        this.ngOnInit();
        this.modalService.dismissAll();
      });
}

}
