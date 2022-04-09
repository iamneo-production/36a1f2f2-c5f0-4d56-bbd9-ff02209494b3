import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { AuthServiceService } from 'src/app/service/auth-service.service';
import { Router } from '@angular/router';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private auth:AuthServiceService,private route:Router) {}

  intercept(request: HttpRequest<any>,
     next: HttpHandler
     ): Observable<HttpEvent<any>> {
    if(request.headers.get('No-Auth')=='True'){
      return next.handle(request);
    }
    const token=this.auth.token;
    request=this.addToken(request,token);
return next.handle(request).pipe(
  catchError(
    (err:HttpErrorResponse)=>{
      console.log(err.status);
      if(err.status===401){
this.route.navigate(['/login']);
      }
      else if(err.status===403) {
        this.route.navigate(['/forbidden']);
      }
      return throwError(()=>"Some thing is wrong ");
    }
  )
);
  }
  private addToken(request:HttpRequest<any>,token:string){
    return request.clone({
      setHeaders:{
        Authorization : `Bearer${token}`
      }
    });
  }
}
