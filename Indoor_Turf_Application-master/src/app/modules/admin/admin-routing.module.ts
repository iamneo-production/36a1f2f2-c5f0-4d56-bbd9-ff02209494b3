import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AddgroundComponent } from './components/addground/addground.component';
import { EditgroundComponent } from './components/editground/editground.component';
import { AddadminComponent } from './components/addadmin/addadmin.component';
import { DeletegroundComponent } from './components/editground/deleteground/deleteground.component';
import { DisplayUsersComponent } from './components/display-users/display-users.component';
import { HeaderComponent } from './components/header/header.component';
import { DeleteuserComponent } from './components/deleteuser/deleteuser.component';
import { EdituserComponent } from './components/edituser/edituser.component';
import { AdduserComponent } from './components/adduser/adduser.component';

const routes: Routes = [
  {path: '',component: HeaderComponent,
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'dashboard', component: AdminDashboardComponent },
      { path: 'signup', component: AddadminComponent },
      { path: 'addGround', component: AddgroundComponent },
      { path: 'displayusers', component: DisplayUsersComponent },
      { path: 'editGround/:id', component: EditgroundComponent },
      { path: 'deleteGround/:id', component: DeletegroundComponent },
      { path: 'adduser', component: AdduserComponent },
      { path: 'edituser', component: EdituserComponent },
      { path: 'deleteuser', component: DeleteuserComponent },
      { path: '', redirectTo: '/admin/addGround', pathMatch: 'full' },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}