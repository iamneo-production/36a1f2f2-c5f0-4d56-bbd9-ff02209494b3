import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { GroundComponent } from './components/ground/ground.component';
import { EditbookingComponent } from './components/editbooking/editbooking.component';
import { DeletebookingComponent } from './components/deletebooking/deletebooking.component';
import { AddbookingComponent } from './components/addbooking/addbooking.component';


const routes: Routes = [
  {path: '',component: NavbarComponent,
    children: [
      { path: 'dashboard', component: UserDashboardComponent },
      { path: 'ground', component: GroundComponent },
      { path: 'bookedgrounds', component: AddbookingComponent },
      { path: 'deletebookedgrounds/:id', component: DeletebookingComponent },
      { path: 'editbookedgrounds/:id', component: EditbookingComponent },
      { path: '', redirectTo: '/user/dashboard', pathMatch: 'full' },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserRoutingModule {}