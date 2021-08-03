import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DateFormatterService {

  constructor() {
  }


  public static dateToYear(datetime: string): number {
    return new Date(Date.parse(datetime)).getFullYear();
  }
}
