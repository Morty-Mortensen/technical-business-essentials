import {Component, Input, OnInit} from '@angular/core';
import {CourseSection, CourseTopic} from "../../../models/course";

@Component({
  selector: 'app-course-section',
  templateUrl: './course-section.component.html',
  styleUrls: ['./course-section.component.scss']
})
export class CourseSectionComponent implements OnInit {

  @Input() section: CourseSection = {
    id: -1,
    title: '',
    orderNumber: -1,
    topics: []
  };

  constructor() {
  }

  ngOnInit(): void {
  }

}
