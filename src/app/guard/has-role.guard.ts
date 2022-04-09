import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthServiceService } from 'src/app/service/auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class HasRoleGuard implements CanActivate {
  constructor(private auth:AuthServiceService){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      console.log(this.auth.user.roles.includes(route.data['role']));
      console.log(this.auth.user.roles);
    const isAuthorized= this.auth.user.roles.includes(route.data['role']);
    if(!isAuthorized){
      window.alert('forbidden page requested')
    }
    return isAuthorized;
  }
  
}
