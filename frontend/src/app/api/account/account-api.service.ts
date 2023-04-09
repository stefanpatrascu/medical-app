import { Injectable } from '@angular/core';
import { environment } from "../../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { BehaviorSubject, lastValueFrom, Observable } from "rxjs";
import { IAccountResponse } from "./account.interface";
import { AccountApiEnum } from "./account.enum";

@Injectable({
  providedIn: 'root'
})
export class AccountApiService {
  private myAccount$: BehaviorSubject<IAccountResponse | null> = new BehaviorSubject<IAccountResponse | null>(null);
  private path: string = environment.apiUrl + AccountApiEnum.ACCOUNT_PATH;
  constructor(private http: HttpClient) { }
  public getMyAccount(): Observable<IAccountResponse | null> {
    if (this.myAccount$.getValue() === null) {
      lastValueFrom(this.http.get<IAccountResponse>(this.path + AccountApiEnum.MY_ACCOUNT_PATH))
        .then((res: IAccountResponse) => this.myAccount$.next(res));
    }

    return this.myAccount$.asObservable();
  }

}
