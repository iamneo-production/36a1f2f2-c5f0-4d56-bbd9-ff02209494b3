import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard/dashboard.component';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword/forgotpassword.component';
import { HasRoleGuard } from './guard/has-role.guard';
import { IsAuthenticatedGuard } from './guard/is-authenticated.guard';
import { LoginComponent } from './login/login/login.component';
import { NavigationComponent } from './navigation/navigation/navigation.component';
import { GroundsComponent } from './service/grounds/grounds.component';
import { SignupComponent } from './signup/signup/signup.component';

const routes: Routes = [

  { path:'',redirectTo:'/login', pathMatch:'full'},
  {path:'login',component: LoginComponent },
  {path:'signup',component: SignupComponent},
  {path:'forgotpassword',component:ForgotpasswordComponent},
  {path:'navigation',component:NavigationComponent},
  {path:'dashboard',
  component:DashboardComponent,
  canActivate:[IsAuthenticatedGuard]
  },
  {
    path:'grounds',
    component:GroundsComponent,
    canActivate:[IsAuthenticatedGuard,HasRoleGuard],
    data:{
      role:'ROLE_USER'
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],

exports: [RouterModule]
})
export class AppRoutingModule { }
