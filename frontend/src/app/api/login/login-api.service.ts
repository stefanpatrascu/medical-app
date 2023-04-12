import { Injectable } from '@angular/core';
import { environment } from "../../../environments/environment";
import { Observable } from "rxjs";
import { GenericApiResponse } from "../../interfaces/generic.interface";
import { HttpClient } from "@angular/common/http";
import { LoginApiEnum } from "./login.enum";
import { IAccountResponse } from "../account/account.interface";

@Injectable({
  providedIn: 'root'
})
export class LoginApiService {

  private path: string = environment.apiUrl + LoginApiEnum.AUTH_PATH;

  constructor(private http: HttpClient) { }
  public authentificate({email, password}: {email: string, password: string}): Observable<GenericApiResponse<IAccountResponse>> {
    return this.http.post<GenericApiResponse<IAccountResponse>>(this.path + LoginApiEnum.LOGIN_PATH, { email, password });
  }

}
