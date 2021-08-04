import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LogoutUserRequest, User, ValidateUserRequest} from "../../models/user";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl = 'http://localhost:8080'

  constructor(private http: HttpClient) {
  }

  public login(request: ValidateUserRequest) {
    return this.http.post<User>(this.baseUrl + '/user/validate', {
      email: request.email,
      password: request.password
    })
      .pipe(
        map(response => {
          return response;
        })
      );
  }

  public logout(request: LogoutUserRequest) {
    return this.http.post<void>(this.baseUrl + '/user/logout', {
      token: request.token,
    })
      .pipe(
        map(response => {
          return response;
        })
      );
  }
}
