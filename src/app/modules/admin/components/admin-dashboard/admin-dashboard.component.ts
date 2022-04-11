import { HttpErrorResponse, HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, NgForm, FormGroup, FormBuilder} from '@angular/forms';
import { Router } from '@angular/router';

import { GroundService } from 'src/app/service/ground.service';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { ground } from 'src/app/classes/ground';





@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit {
  grounds!:ground[];
  editground!: ground;
  closeResult: string | undefined;
  editForm!:FormGroup;
  deleteId!:string;

  constructor(private service:GroundService,
    private router:Router,
    private modalService:NgbModal,
     private fb: FormBuilder,
     private httpClient:HttpClient
     ) { }

  ngOnInit(): void {
    this.getGrounds();
    this.editForm = this.fb.group({
      groundId: [''],
      groundName: [''],
      groundTiming: [''],
      capacity: [''],
      groundAddress: [''],
      imageURL: [''],
      price: [''],
      groundDescription: [''],
      
    } );
  }

  private getGrounds(){
    this.service.getAllGrounds().subscribe({
     next: (data)=>{
        this.grounds=data;
      },
      error:(err)=>{
        console.log(err);
      }
     });
  }

  editGround(id:string){
    this.router.navigate(['/admin/editGround' ,id]);
  }
  onSave() {
    const editURL = 'http://localhost:8080/admin/editGround/' + this.editForm.value.groundId ;
    console.log(this.editForm.value);
    this.httpClient.put(editURL, this.editForm.value)
      .subscribe((results) => {
        this.ngOnInit();
        this.modalService.dismissAll();
      });
  }

  deleteGround(id:string){
    this.router.navigate(['/admin/deleteGround' ,id]);
  }

  openDelete(contentDelete: any, ground: ground) {
       this.deleteId = ground.groundId;
    this.modalService.open(contentDelete, {
      backdrop: 'static',
      size: 'lg'
    });
  }


  onDelete() {
    const deleteURL = 'http://localhost:8080/admin/deleteGround/' + this.deleteId;
    this.httpClient.delete(deleteURL)
      .subscribe((results) => {
        this.ngOnInit();
        this.modalService.dismissAll();
      });
  }

  public onaddground(addForm: NgForm): void{
document.getElementById('add-ground-')?.click();

   this.service.addGround(addForm.value).subscribe({
     next: (response:any)=>{
       console.log(response);
       this.getGrounds();
       addForm.reset();
     },
    error:  (err:HttpErrorResponse)=>{
       alert(err.message);
       addForm.reset();
     }
    });

  }
  public onupdateground(ground: ground): void{
       this.service.editGround(ground).subscribe({
         next: (response:any)=>{
           console.log(response);
           this.getGrounds();
         },
        error:  (err:HttpErrorResponse)=>{
           alert(err.message);
         }
        })
    
      }

      public searchground(key:string):void{
        const results:ground[]=[];
        for(const ground of this.grounds ){
          if(ground.groundName.toLowerCase().indexOf(key.toLowerCase())!== -1
          || (ground.price==key)
          || ground.groundAddress.toLowerCase().indexOf(key.toLowerCase())!== -1){
            results.push(ground);
          }
        }
        this.grounds=results;
        if(results.length===0 || !key){
          this.getGrounds();
        }
      }
      onSubmitadd(f: NgForm) {
        
        this.service.addGround(f.value)
          .subscribe((result) => {
            this.ngOnInit(); //reload the table
          });
        this.modalService.dismissAll(); //dismiss the modal
      }


      open(content: any) {
        this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
          this.closeResult = `Closed with: ${result}`;
        }, (reason) => {
          this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
        });
      }

      openEdit(contentEdit: any,ground: ground){
        this.modalService.open(contentEdit, {
          centered: true,
          backdrop: 'static',
          size: 'lg'
        });
        this.editForm.patchValue( {
          groundId: ground.groundId, 
          groundName: ground.groundName,
          groundTiming: ground.GroundTiming,
          capacity: ground.capacity,
          groundAddress: ground.groundAddress,
          imageURL: ground.imageURL,
          price: ground.price,
          groundDescription: ground.groundDescription
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









}
