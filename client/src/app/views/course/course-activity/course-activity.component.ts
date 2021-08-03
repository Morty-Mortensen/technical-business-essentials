import {Component, Input, OnInit} from '@angular/core';
import {CourseActivity} from "../../../models/course";

@Component({
  selector: 'app-course-activity',
  templateUrl: './course-activity.component.html',
  styleUrls: ['./course-activity.component.scss']
})
export class CourseActivityComponent implements OnInit {

  @Input() activity: CourseActivity = {
    id: -1,
    description: '',
    sampleCode: ''
  };

  @Input() activityIndex: number = -1;

  public code: string = '';

  constructor() {
    // this.code = highlight('int test = 1', languages['typescript'], 'typescript');
  }

  ngOnInit(): void {
  }

}
