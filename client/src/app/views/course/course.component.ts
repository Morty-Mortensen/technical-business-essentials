import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CourseService} from "../../services/http/course.service";
import {Course, CourseRequest, CourseTopic} from "../../models/course";

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.scss']
})
export class CourseComponent implements OnInit {

  public course: Course = {
    id: 1,
    title: 'Ap Computer Science',
    imageUrl: '',
    sections: [{
      id: 1,
      title: 'Section 1',
      orderNumber: 1,
      topics: [
        {
          id: 1,
          title: 'Topic 1, Section 1.',
          videoUrl: '',
          description: 'This is a fantastic video were you learn about the basics of what it takes to start being a computer scientist. Good luck!',
          activities: [{
            id: 1,
            description: 'The point is the have the program write out to the console.',
            sampleCode: 'console.log("Hello World!")'
          },
            {
              id: 2,
              description: 'This is the second activity. You should be proud! This exercise is meant to help you print out a variable in the console.',
              sampleCode: 'console.log(`{var}!`)'
            },
            {
              id: 3,
              description: 'Print out your favorite message to the console.',
              sampleCode: 'console.log("I love spinach sandwiches :)")'
            }]
        },
        {
          id: 2,
          title: 'Topic 2, Section 1.',
          videoUrl: '',
          description: 'Great video.',
          activities: [{
            id: 1,
            description: 'The point is the have the program write out to the console.',
            sampleCode: 'console.log("Hello World!")'
          }]
        },
        {
          id: 3,
          title: 'Topic 3, Section 1.',
          videoUrl: '',
          description: 'Great video.',
          activities: [{
            id: 1,
            description: 'The point is the have the program write out to the console.',
            sampleCode: 'console.log("Hello World!")'
          }]
        },
        {
          id: 4,
          title: 'Topic 4, Section 1.',
          videoUrl: '',
          description: 'Great video.',
          activities: [{
            id: 1,
            description: 'The point is the have the program write out to the console.',
            sampleCode: 'console.log("Hello World!")'
          }]
        },
        {
          id: 5,
          title: 'Topic 5, Section 1.',
          videoUrl: '',
          description: 'Great video.',
          activities: [{
            id: 1,
            description: 'The point is the have the program write out to the console.',
            sampleCode: 'console.log("Hello World!")'
          }]
        }
      ]
    },
      {
        id: 1,
        title: 'Section 2',
        orderNumber: 1,
        topics: [
          {
            id: 1,
            title: 'Topic 1, Section 2.',
            videoUrl: '',
            description: 'Great video.',
            activities: [{
              id: 1,
              description: 'The point is the have the program write out to the console.',
              sampleCode: 'console.log("Hello World!")'
            }]
          },
          {
            id: 2,
            title: 'Topic 2, Section 2.',
            videoUrl: '',
            description: 'Great video.',
            activities: [{
              id: 1,
              description: 'The point is the have the program write out to the console.',
              sampleCode: 'console.log("Hello World!")'
            }]
          },
          {
            id: 3,
            title: 'Topic 3, Section 2.',
            videoUrl: '',
            description: 'Great video.',
            activities: [{
              id: 1,
              description: 'The point is the have the program write out to the console.',
              sampleCode: 'console.log("Hello World!")'
            }]
          },
          {
            id: 4,
            title: 'Topic 4, Section 2.',
            videoUrl: '',
            description: 'Great video.',
            activities: [{
              id: 1,
              description: 'The point is the have the program write out to the console.',
              sampleCode: 'console.log("Hello World!")'
            }]
          },
          {
            id: 5,
            title: 'Topic 5, Section 2.',
            videoUrl: '',
            description: 'Great video.',
            activities: [{
              id: 1,
              description: 'The point is the have the program write out to the console.',
              sampleCode: 'console.log("Hello World!")'
            }]
          }
        ]
      },
      {
        id: 1,
        title: 'Section 3',
        orderNumber: 1,
        topics: [
          {
            id: 1,
            title: 'Topic 1, Section 3.',
            videoUrl: '',
            description: 'Great video.',
            activities: [{
              id: 1,
              description: 'The point is the have the program write out to the console.',
              sampleCode: 'console.log("Hello World!")'
            }]
          },
          {
            id: 2,
            title: 'Topic 2, Section 3.',
            videoUrl: '',
            description: 'Great video.',
            activities: [{
              id: 1,
              description: 'The point is the have the program write out to the console.',
              sampleCode: 'console.log("Hello World!")'
            }]
          },
          {
            id: 3,
            title: 'Topic 3, Section 3.',
            videoUrl: '',
            description: 'Great video.',
            activities: [{
              id: 1,
              description: 'The point is the have the program write out to the console.',
              sampleCode: 'console.log("Hello World!")'
            }]
          },
          {
            id: 4,
            title: 'Topic 4, Section 3.',
            videoUrl: '',
            description: 'Great video.',
            activities: [{
              id: 1,
              description: 'The point is the have the program write out to the console.',
              sampleCode: 'console.log("Hello World!")'
            }]
          },
          {
            id: 5,
            title: 'Topic 5, Section 3.',
            videoUrl: '',
            description: 'Great video.',
            activities: [{
              id: 1,
              description: 'The point is the have the program write out to the console.',
              sampleCode: 'console.log("Hello World!")'
            }]
          }
        ]
      }
    ]
  };

  public selectedTopic: CourseTopic = this.course.sections[0].topics[0];

  constructor(private route: ActivatedRoute, private courseService: CourseService) {
  }

  ngOnInit(): void {
    const courseId = Number(this.route.snapshot.queryParamMap.get('courseId'));
    const sectionId = Number(this.route.snapshot.queryParamMap.get('sectionId')) || null;
    const topicId = Number(this.route.snapshot.queryParamMap.get('topicId')) || null;
    this.getCourseInfo(courseId, sectionId, topicId)
  }

  public getCourseInfo(courseId: number, sectionId: number | null, topicId: number | null): void {

    // const request = {
    //   courseId,
    //   sectionId,
    //   topicId
    // } as CourseRequest
    //
    // this.courseService.getCourses(request).subscribe(response => {
    //   console.log('Response!');
    //   console.log(response);
    // },
    // error => {
    //     console.log('An error occurred');
    //     console.log(error);
    // });
  }

}
