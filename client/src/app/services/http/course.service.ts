import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {Course, CourseRequest} from "../../models/course";

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private http: HttpClient) {
  }

  public getCourses(request: CourseRequest) {
    return this.http.get<Course[]>(`/courses/${request.courseId}/sections/${request.sectionId}/topics/${request.topicId}`)
      .pipe(
        map(response => {
          return response;
        })
      );
  }
}
