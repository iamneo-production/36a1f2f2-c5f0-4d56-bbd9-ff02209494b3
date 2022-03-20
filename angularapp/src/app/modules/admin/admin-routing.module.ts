import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AddgroundComponent } from './components/addground/addground.component';
import { EditgroundComponent } from './components/editground/editground.component';
import { AddadminComponent } from './components/addadmin/addadmin.component';
import { EditgrdComponent } from './components/editground/editgrd/editgrd.component';
import { DeletegroundComponent } from './components/editground/deleteground/deleteground.component';
import { DisplayUsersComponent } from './components/display-users/display-users.component';

const routes: Routes = [
  {path: '',component: AdminDashboardComponent,
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'addadmin', component: AddadminComponent },
      { path: 'addground', component: AddgroundComponent },
      { path: 'displayusers', component: DisplayUsersComponent },
      { path: 'editground', component: EditgroundComponent },
      { path: 'deleteground', component: DeletegroundComponent },
      { path: 'editgrd', component: EditgrdComponent },
      { path: '', redirectTo: '/admin/home', pathMatch: 'full' },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}