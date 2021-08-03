import {Component, Input, OnInit} from '@angular/core';
import {CourseTopic} from "../../../models/course";

@Component({
  selector: 'app-course-topic',
  templateUrl: './course-topic.component.html',
  styleUrls: ['./course-topic.component.scss']
})
export class CourseTopicComponent implements OnInit {

  @Input() topic: CourseTopic = {
    id: -1,
    title: '',
    videoUrl: '',
    description: '',
    activities: []
  };

  constructor() {
  }

  ngOnInit(): void {
  }

}
