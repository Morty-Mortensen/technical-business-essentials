import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CourseService} from "../../services/course.service";

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.scss']
})
export class CourseComponent implements OnInit {

  constructor(private route: ActivatedRoute, private courseService: CourseService) {
  }

  ngOnInit(): void {
    const courseId = Number(this.route.snapshot.queryParamMap.get('courseId')) || null;
    const sectionId = Number(this.route.snapshot.queryParamMap.get('sectionId')) || null;
    const topicId = Number(this.route.snapshot.queryParamMap.get('topicId')) || null;
  }

  public getCourseInfo(courseId: number, sectionId: number, topicId: number): void {

  }

}
