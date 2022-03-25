import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { UserRoutingModule } from './user-routing.module';
import { UserHomeComponent } from './components/user-home/user-home.component';
import { MybookingComponent } from './components/mybooking/mybooking.component';
import { NavbarComponent } from './components/navbar/navbar.component';


@NgModule({
  declarations: [
    UserDashboardComponent,
    UserHomeComponent,
    MybookingComponent,
    NavbarComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    ReactiveFormsModule
  ]
})
export class UserModule { }
