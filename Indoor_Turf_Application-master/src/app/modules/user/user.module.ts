import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { UserRoutingModule } from './user-routing.module';
import { NavbarComponent } from './components/navbar/navbar.component';
import { GroundComponent } from './components/ground/ground.component';
import { EditbookingComponent } from './components/editbooking/editbooking.component';
import { DeletebookingComponent } from './components/deletebooking/deletebooking.component';
import { AddbookingComponent } from './components/addbooking/addbooking.component';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { NgbModule, NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { ViewreviewComponent } from './components/viewreview/viewreview.component';
import { ReviewComponent } from './components/addreview/review.component';
import { EditreviewComponent } from './components/editreview/editreview.component';
import { DeletereviewComponent } from './components/deletereview/deletereview.component';

@NgModule({
  declarations: [
    UserDashboardComponent,
    NavbarComponent,
    GroundComponent,
    EditbookingComponent,
    DeletebookingComponent,
    AddbookingComponent,
    ViewreviewComponent,
    ReviewComponent,
    EditreviewComponent,
    DeletereviewComponent,
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    ReactiveFormsModule,
    NgbModule,
  ],
  providers: [NgbRatingConfig],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class UserModule { }
