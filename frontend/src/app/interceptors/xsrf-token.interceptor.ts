import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CookieUtil } from "../utils/cookie.util";
import { AccountApiEnum } from "../api/account/account.enum";
import { LoginApiEnum } from "../api/login/login.enum";

@Injectable()
export class XsrfTokenInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const cookieUtil: CookieUtil = new CookieUtil();

    if ((req.method === 'POST' || req.method === 'PUT' || req.method === 'DELETE') && !this.isUrlExcluded(req.url)) {
      const xsrfToken: string | undefined = cookieUtil.getCookie('XSRF-TOKEN');
      if (xsrfToken) {
        req = req.clone({
          setHeaders: {
            'X-XSRF-TOKEN': xsrfToken,
          },
        });
      }
    }
    return next.handle(req);
  }

  private isUrlExcluded(url: string): boolean {
    const excludedUrls: string[] = [LoginApiEnum.LOGIN_PATH];
    return excludedUrls.some((excludedUrl) => url.includes(excludedUrl));
  }
}
