import {Injectable} from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoadingService {

  private _loading: Subject<boolean> = new Subject();

  constructor() {
    this.stop();
  }

  get loading() {
    return this._loading.asObservable();
  }

  public start() {
    this._loading.next(true);
  }

  public stop() {
    this._loading.next(false);
  }
}
