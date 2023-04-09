import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from "../../../environments/environment";
import { SessionApiEnum } from "./session.enum";
@Injectable({
  providedIn: 'root'
})

@Injectable({
  providedIn: 'root',
})
export class SessionsApiService {

  private path: string = environment.apiUrl + SessionApiEnum.SESSION_PATH;

  constructor(private http: HttpClient) {}

  public refreshSession(): Observable<any> {
    return this.http.post(this.path + SessionApiEnum.REFRESH_SESSION_PATH, {});
  }
}
