import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError, switchMap } from 'rxjs/operators';
import { SessionsApiService } from "../api/session/session.service";
import { SessionApiEnum } from "../api/session/session.enum";
import { LoginApiEnum } from "../api/login/login.enum";
import { RouteEnum } from "../enums/route.enum";

@Injectable()
export class RefreshSessionInterceptor implements HttpInterceptor {

  constructor(
    private refreshSessionService: SessionsApiService,
    private router: Router
  ) {
  }

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    // Exclude login and refresh-session requests from refresh logic
    if (req.url.endsWith(LoginApiEnum.LOGIN_PATH) || req.url.endsWith(SessionApiEnum.REFRESH_SESSION_PATH)) {
      return next.handle(req);
    }

    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401) {
          return this.refreshSessionService.refreshSession().pipe(
            switchMap(() => {
              return next.handle(req);
            }),
            catchError((refreshError: HttpErrorResponse) => {
              // Refresh session failed, redirect the user to the login page
              this.router.navigate([RouteEnum.LOGIN_PATH]);
              return throwError(refreshError);
            })
          );
        } else {
          return throwError(error);
        }
      })
    );
  }
}
