import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import CodeEditor from "../../models/code-editor";

@Injectable({
  providedIn: 'root'
})
export class ActivityService {

  constructor(private http: HttpClient) {
  }

  public compileActivity(activityId: number, request: CodeEditor) {
    return this.http.post<any>(`/activities/${activityId}/compile`, request)
      .pipe(
        map(response => {
          return response;
        })
      );
  }
}
