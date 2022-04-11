import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword/forgotpassword.component';
import { HasRoleGuard } from './guard/has-role.guard';
import { IsAuthenticatedGuard } from './guard/is-authenticated.guard';
import { LoginComponent } from './login/login/login.component';
import { NavigationComponent } from './navigation/navigation/navigation.component';
import { SignupComponent } from './signup/signup/signup.component';

const routes: Routes = [

  { path:'',redirectTo:'/login', pathMatch:'full'},
  {path:'login',component: LoginComponent },
  {path:'signup',component: SignupComponent},
  {path:'forgotpassword',component:ForgotpasswordComponent},
  {path:'navigation',component:NavigationComponent},
  {
    path: 'admin',
    canActivate: [IsAuthenticatedGuard,HasRoleGuard],
    data:{
      role:'ROLE_ADMIN'
    },
    loadChildren: () =>
      import('./modules/admin/admin.module').then((m)=>m.AdminModule),
  },
  {
    path: 'user',
    canActivate: [IsAuthenticatedGuard,HasRoleGuard],
      data:{
        role:'ROLE_USER'
      },
    
    loadChildren: () =>
      import('./module/user/user.module').then((m)=>m.UserModule),
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],

exports: [RouterModule]
})
export class AppRoutingModule { }
