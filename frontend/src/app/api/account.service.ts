import { Injectable } from '@angular/core';
import { environment } from "../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { IAccountResponse } from "./interfaces/account.interface";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private path: string = environment.apiUrl + '/account';
  constructor(private http: HttpClient) { }
  public getMyAccount(): Observable<IAccountResponse> {
    return this.http.get<IAccountResponse>(this.path + '/my-account');
  }
}
