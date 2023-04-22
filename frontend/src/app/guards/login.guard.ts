import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, map } from 'rxjs/operators';
import { AccountApiService } from "../api/account/account-api.service";
import { RouteEnum } from "../enums/route.enum";
import { RoleEnum } from "../enums/role.enum";
import { of } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoginGuard {

  constructor(private accountApiService: AccountApiService,
              private router: Router) {
  }

  canActivate() {
    return this.accountApiService.getMyAccount().pipe(
      map(account => {
        if (account) {
          if (account.role === RoleEnum.ADMIN) {
            this.router.navigate(['/' + RouteEnum.ADMIN_DASHBOARD_PATH]).then();
          } else if (account.role === RoleEnum.DOCTOR) {
            this.router.navigate(['/' + RouteEnum.DOCTOR_DASHBOARD_PATH]).then();
          } else if (account.role === RoleEnum.PATIENT) {
            this.router.navigate(['/' + RouteEnum.PATIENT_DASHBOARD]).then();
          }
          return false;
        } else {
          return true;
        }
      }),
      catchError(() => {
        return of(true);
      })
    );
  }
}
