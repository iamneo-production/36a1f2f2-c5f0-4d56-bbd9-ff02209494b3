import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { UserRoutingModule } from './user-routing.module';
import { NavbarComponent } from './components/navbar/navbar.component';
import { GroundComponent } from './components/ground/ground.component';
import { EditbookingComponent } from './components/editbooking/editbooking.component';
import { DeletebookingComponent } from './components/deletebooking/deletebooking.component';
import { AddbookingComponent } from './components/addbooking/addbooking.component';


@NgModule({
  declarations: [
    UserDashboardComponent,
    NavbarComponent,
    GroundComponent,
    EditbookingComponent,
    DeletebookingComponent,
    AddbookingComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    ReactiveFormsModule
  ]
})
export class UserModule { }
