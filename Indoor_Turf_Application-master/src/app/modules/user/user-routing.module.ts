import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { GroundComponent } from './components/ground/ground.component';
import { EditbookingComponent } from './components/editbooking/editbooking.component';
import { DeletebookingComponent } from './components/deletebooking/deletebooking.component';
import { AddbookingComponent } from './components/addbooking/addbooking.component';
import { ReviewComponent } from './components/addreview/review.component';
import { ViewreviewComponent } from './components/viewreview/viewreview.component';
import { EditreviewComponent } from './components/editreview/editreview.component';
import { DeletereviewComponent } from './components/deletereview/deletereview.component';


const routes: Routes = [
  {path: '',component: NavbarComponent,
    children: [
      { path: 'dashboard', component: UserDashboardComponent },
      { path: 'ground/:groundId', component: GroundComponent },
      { path: 'bookedgrounds/:email', component: AddbookingComponent },
      { path: 'deletebookedgrounds/:bookingId', component: DeletebookingComponent },
      { path: 'editbookedgrounds/:bookingId', component: EditbookingComponent },
      { path: 'addreview/:userId/:groundId',component:ReviewComponent},
      { path: 'viewreview/:groundId',component:ViewreviewComponent},
      { path: 'editreview/:id/:groundId',component:EditreviewComponent},
      { path: 'deletereview/:id/:groundId',component:DeletereviewComponent},
      { path: '', redirectTo: '/user/dashboard', pathMatch: 'full' },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserRoutingModule {}