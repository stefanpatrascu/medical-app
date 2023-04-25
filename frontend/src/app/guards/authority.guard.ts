import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router } from '@angular/router';
import { Observable, of, tap, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { AccountApiService } from "../api/account/account-api.service";
import { IAccountResponse } from "../api/account/account.interface";
import { RoleEnum } from "../enums/role.enum";
import { RouteEnum } from "../enums/route.enum";

@Injectable({providedIn: 'root'})
export class AuthorityGuard {
  constructor(
    private accountApiService: AccountApiService,
    private router: Router
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot): Observable<boolean> {
    return this.accountApiService.getMyAccount().pipe(
      map((account: IAccountResponse | null): boolean => {
        if (account) {
          const authorities: RoleEnum[] = route.data['authorities'];
          const hasAnyAuthority: boolean = authorities.includes(account.role);

          if ((!authorities || authorities.length === 0 || hasAnyAuthority)) {
            return true;
          }
          return this.goToLogin();
        }
        return this.goToLogin();
      }),
      catchError(() => {
        return of(this.goToLogin());
      })
    );
  }

  private goToLogin(): boolean {
    this.router.navigate([RouteEnum.LOGIN_PATH]).then();
    return false;
  }
}
