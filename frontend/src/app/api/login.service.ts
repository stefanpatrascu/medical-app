import { Injectable } from '@angular/core';
import { environment } from "../../environments/environment";
import { Observable } from "rxjs";
import { GenericApiResponse } from "./interfaces/generic.interface";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  path: string = environment.apiUrl + '/api/auth';

  constructor(private http: HttpClient) { }
  public authentificate({email, password}: {email: string, password: string}): Observable<GenericApiResponse> {
    return this.http.post<GenericApiResponse>(this.path + '/login', { email, password });
  }

}
