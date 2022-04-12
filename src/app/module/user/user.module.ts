import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { UserRoutingModule } from './user-routing.module';
import { NavbarComponent } from './components/navbar/navbar.component';
import { GroundComponent } from './components/ground/ground.component';
import { EditbookingComponent } from './components/editbooking/editbooking.component';
import { DeletebookingComponent } from './components/deletebooking/deletebooking.component';
import { AddbookingComponent } from './components/addbooking/addbooking.component';
import { UserhomeComponent } from './components/userhome/userhome.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


@NgModule({
  declarations: [
    UserDashboardComponent,
    NavbarComponent,
    GroundComponent,
    EditbookingComponent,
    DeletebookingComponent,
    AddbookingComponent,
    UserhomeComponent
  ],
  imports: [
  CommonModule,
    UserRoutingModule,
    ReactiveFormsModule,
    NgbModule,
    FormsModule
  ]
})
export class UserModule { }
