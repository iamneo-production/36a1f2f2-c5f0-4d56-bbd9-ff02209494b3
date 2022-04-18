import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { GroundComponent } from './components/ground/ground.component';
import { EditbookingComponent } from './components/editbooking/editbooking.component';
import { DeletebookingComponent } from './components/deletebooking/deletebooking.component';
import { AddbookingComponent } from './components/addbooking/addbooking.component';
import { UserhomeComponent } from './components/userhome/userhome.component';
import { ViewreviewComponent } from './components/viewreview/viewreview.component';
import { EditreviewComponent } from './components/editreview/editreview.component';
import { DeletereviewComponent } from './components/deletereview/deletereview.component';
import { AddreviewComponent } from './components/addreview/addreview.component';


const routes: Routes = [
  {path: '',component: NavbarComponent,
    children: [
      { path: 'dashboard', component: UserDashboardComponent },
      { path: 'ground/:groundId', component: GroundComponent },
      { path: 'bookedgrounds/:email', component: AddbookingComponent },
      { path: 'deletebookedgrounds/:id', component: DeletebookingComponent },
      { path: 'editbookedgrounds/:id', component: EditbookingComponent },
      { path: 'addreview/:userEmail/:groundId',component:AddreviewComponent},
      { path: 'viewreview/:groundId',component:ViewreviewComponent},
      { path: 'editreview/:id/:groundId',component:EditreviewComponent},
      { path: 'deletereview/:id/:groundId',component:DeletereviewComponent},
      {path:'home',component:UserhomeComponent},
      { path: '', redirectTo: '/user/home', pathMatch: 'full' },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserRoutingModule {}