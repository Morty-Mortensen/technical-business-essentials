import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {Course} from "../../../models/course";
import {TechnicalDialogComponent} from "../../../components/technical-dialog/technical-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {DialogFactoryService} from "../../../services/utils/dialog-factory.service";
import {DialogService} from "../../../services/utils/dialog.service";

@Component({
  selector: 'app-admin-courses',
  templateUrl: './admin-courses.component.html',
  styleUrls: ['./admin-courses.component.scss']
})
export class AdminCoursesComponent implements OnInit {

  public courses: Course[] = [];
  dialog: DialogService | undefined;
  @ViewChild("userDialogTemplate")
  userDialogTemplate: TemplateRef<any> | undefined;

  constructor(private dialogFactoryService: DialogFactoryService) {
  }

  ngOnInit(): void {
    this.courses.push({
      id: 1,
      title: 'Ap Computer Science',
      imageUrl: 'https://google.com',
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
          ]
        },
      ]
    })
  }

  openDialog(): void {
    if (this.userDialogTemplate !== undefined) {
      this.dialog = this.dialogFactoryService.open({
        title: 'Add Course',
        template: this.userDialogTemplate
      });
    }

    // Change the header of the dialog
    // this.dialog.setTitle('New header')

    // Change the content of the dialog
    // this.dialog.setTemplate(this.userDialogTemplate)

    // Finally close the dialog
    //xconst {activity} = this.dialog.context // Get context before close

  }

  closeDialog(): void {
    this.dialog?.close();
  }
}
