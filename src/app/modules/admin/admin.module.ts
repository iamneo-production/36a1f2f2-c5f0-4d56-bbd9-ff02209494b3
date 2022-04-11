import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { AdminRoutingModule } from './admin-routing.module';
import { AddgroundComponent } from './components/addground/addground.component';
import { EditgroundComponent } from './components/editground/editground.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddadminComponent } from './components/addadmin/addadmin.component';
import { DeletegroundComponent } from './components/editground/deleteground/deleteground.component';
import { DisplayUsersComponent } from './components/display-users/display-users.component';
import { EdituserComponent } from './components/edituser/edituser.component';
import { DeleteuserComponent } from './components/deleteuser/deleteuser.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from 'src/app/service/auth.interceptor';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { GrounddetailsComponent } from './components/grounddetails/grounddetails.component';
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
    AdduserComponent,
    GrounddetailsComponent
  ],
  imports: [
  CommonModule,
    AdminRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    NgbModule
  ],
  providers: [
    {
      provide:HTTP_INTERCEPTORS,
      useClass:AuthInterceptor,
      multi:true
    }
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class AdminModule { }
