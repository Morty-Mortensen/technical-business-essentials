import {Injectable} from '@angular/core';
import {User} from "../models/user";
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CacheService {
  private _user: Subject<User> = new Subject<User>();

  constructor() {
    this._user.next({
      email: '',
      firstName: '',
      lastName: ''
    })
  }

  public next(user: User) {
    this._user.next(user);
  }

  get user() {
    return this._user.asObservable();
  }
}
