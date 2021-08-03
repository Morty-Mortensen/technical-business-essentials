import {Injectable} from '@angular/core';
import {map} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {PostUserRequest, User} from "../../models/user";

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  private baseUrl = 'http://localhost:8080/user'

  constructor(private http: HttpClient) {
  }

  public register(request: PostUserRequest) {
    console.log(request);
    return this.http.post<User>(this.baseUrl, {
      user: request.user,
      password: request.password
    })
      .pipe(
        map(response => {
          return response;
        })
      );
  }
}
