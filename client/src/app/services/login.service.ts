import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ValidateUserRequest} from "../models/user";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl = 'http://localhost:8080/user/validate'

  constructor(private http: HttpClient) {
  }

  public register(request: ValidateUserRequest) {
    return this.http.post<any>(this.baseUrl, {
      email: request.email,
      password: request.password
    })
      .pipe(
        map(response => {
          return response;
        })
      );
  }
}
