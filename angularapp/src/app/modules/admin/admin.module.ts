import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { AdminRoutingModule } from './admin-routing.module';
import { AddgroundComponent } from './components/addground/addground.component';
import { EditgroundComponent } from './components/editground/editground.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AddadminComponent } from './components/addadmin/addadmin.component';
import { EditgrdComponent } from './components/editground/editgrd/editgrd.component';
import { DeletegroundComponent } from './components/editground/deleteground/deleteground.component';
import { DisplayUsersComponent } from './components/display-users/display-users.component';


@NgModule({
  declarations: [
    AdminDashboardComponent,
    HeaderComponent,
    HomeComponent,
    AddgroundComponent,
    EditgroundComponent,
    AddadminComponent,
    EditgrdComponent,
    DeletegroundComponent,
    DisplayUsersComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    ReactiveFormsModule
  ]
})
export class AdminModule { }
