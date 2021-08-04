import {Injectable} from '@angular/core';
import {map} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {PostUserRequest, User} from "../../models/user";

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) {
  }

  public register(request: PostUserRequest) {
    console.log(request);
    return this.http.post<User>('/user', {
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
