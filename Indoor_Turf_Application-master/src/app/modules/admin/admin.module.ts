import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { AdminRoutingModule } from './admin-routing.module';
import { AddgroundComponent } from './components/addground/addground.component';
import { EditgroundComponent } from './components/editground/editground.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AddadminComponent } from './components/addadmin/addadmin.component';
import { DeletegroundComponent } from './components/editground/deleteground/deleteground.component';
import { DisplayUsersComponent } from './components/display-users/display-users.component';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import { EdituserComponent } from './components/edituser/edituser.component';
import { DeleteuserComponent } from './components/deleteuser/deleteuser.component';
import { AdduserComponent } from './components/adduser/adduser.component';


@NgModule({
  declarations: [
    AdminDashboardComponent,
    HeaderComponent,
    HomeComponent,
    AddgroundComponent,
    EditgroundComponent,
    AddadminComponent,
    DeletegroundComponent,
    DisplayUsersComponent,
    EdituserComponent,
    DeleteuserComponent,
    AdduserComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    ReactiveFormsModule
  ],
  providers: [NgbRatingConfig],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class AdminModule { }
