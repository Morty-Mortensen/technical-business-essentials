import {Injectable} from '@angular/core';
import DateRange from "../models/date-range";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FortuneFivehundredService {

  constructor(private http: HttpClient) {
  }

  public getCompanies(range: DateRange): Observable<any> {
    // return this.http.get<any>('https://content.fortune.com/wp-json/irving/v1/data/franchise-search-results?list_id=3040727&token=Zm9ydHVuZTpCcHNyZmtNZCN5SndjWkkhNHFqMndEOTM=') 2021
    return this.http.get<any>('https://content.fortune.com/wp-json/irving/v1/data/franchise-search-results?list_id=2814606&token=Zm9ydHVuZTpCcHNyZmtNZCN5SndjWkkhNHFqMndEOTM=') //2020
    //https://content.fortune.com/wp-json/irving/v1/data/franchise-search-results?list_id=2611932&token=Zm9ydHVuZTpCcHNyZmtNZCN5SndjWkkhNHFqMndEOTM= 2019
  }

}
