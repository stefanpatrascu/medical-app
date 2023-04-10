import { Injectable } from '@angular/core';
import { environment } from "../../../environments/environment";
import { HttpClient, HttpEvent } from "@angular/common/http";
import { BehaviorSubject, lastValueFrom, map, Observable } from "rxjs";
import { IAccountResponse, IAccountUpdateRequest } from "./account.interface";
import { AccountApiEnum } from "./account.enum";
import { GenericApiResponse } from "../../interfaces/generic.interface";
import { DomSanitizer, SafeUrl } from "@angular/platform-browser";

@Injectable({
  providedIn: 'root'
})
export class AccountApiService {
  private myAccount$: BehaviorSubject<IAccountResponse | null> = new BehaviorSubject<IAccountResponse | null>(null);
  private path: string = environment.apiUrl + AccountApiEnum.ACCOUNT_PATH;

  constructor(private http: HttpClient,
              private readonly sanitizer: DomSanitizer) {
  }

  public getMyAccount(forceUpdate: boolean = false): Observable<IAccountResponse | null> {
    if (this.myAccount$.getValue() === null || forceUpdate) {
      lastValueFrom(this.http.get<IAccountResponse>(this.path + AccountApiEnum.MY_ACCOUNT_PATH))
        .then((res: IAccountResponse) => this.myAccount$.next(res));
    }

    return this.myAccount$.asObservable();
  }

  public updateAccount(request: IAccountUpdateRequest): Observable<GenericApiResponse> {
    return this.http.put<GenericApiResponse>(this.path + AccountApiEnum.UPDATE_MY_ACCOUNT_PATH, request)
  }

  public uploadAvatar(file: File): Observable<HttpEvent<GenericApiResponse>> {
    const formData = new FormData();
    formData.append('file', file);

    const requestUrl = this.path + AccountApiEnum.UPLOAD_AVATAR_PATH;

    return this.http.post<GenericApiResponse>(requestUrl, formData, {
      reportProgress: true,
      observe: 'events',
    });
  }

  public getMyAvatar(): Observable<SafeUrl> {
    return this.http.get(this.path + AccountApiEnum.MY_AVATAR_PATH, {responseType: 'blob'})
      .pipe(
        map((blob: Blob) => {
          return this.sanitizer.bypassSecurityTrustResourceUrl(URL.createObjectURL(blob));
        }
      ));
  }

}
