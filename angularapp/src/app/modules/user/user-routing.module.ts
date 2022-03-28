import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserHomeComponent } from './components/user-home/user-home.component';

import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { MybookingComponent } from './components/mybooking/mybooking.component';
import { NavbarComponent } from './components/navbar/navbar.component';

const routes: Routes = [
  {path: '',component: NavbarComponent,
    children: [
      { path: 'home', component: UserHomeComponent },
      { path: 'dashboard', component: UserDashboardComponent },
      { path: 'Mybooking', component: MybookingComponent },
      { path: '', redirectTo: '/user/home', pathMatch: 'full' },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserRoutingModule {}