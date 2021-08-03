import {Component, OnInit} from '@angular/core';
import {CacheService} from "../../services/utils/cache.service";
import {User} from "../../models/user";
import {Course} from "../../models/course";
import {KeyValuePipe} from "@angular/common";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  // Dummy data for course layout.
  public courses: Course[] = [{
    id: 1,
    title: 'Ap Computer Science',
    sections: [{
      id: 1,
      title: 'History of Computer Science',
      orderNumber: 1,
      topics: [
        {
          id: 1,
          title: 'Michael Anchello, the very first.',
          videoUrl: '',
          description: 'Great video.',
          activities: []
        }
      ]
    }]
  },
    {
      id: 2,
      title: 'Java 101',
      sections: [{
        id: 2,
        title: 'History of Java',
        orderNumber: 1,
        topics: [
          {
            id: 1,
            title: 'The first Java program.',
            videoUrl: '',
            description: 'Great video.',
            activities: []
          }
        ]
      }]
    }
  ];

  constructor(private cacheService: CacheService) {
  }

  ngOnInit(): void {
    // console.log(this.getKeys(this.courses))
  }

  public filterContents(contents: Course[]): Course[] {
    return contents.filter(c => c);
  }

  public logout(): void {
    // Send to backend and clear that cache as well...
    this.cacheService.clearData();
  }

  public getUser(): User {
    try {
      return (this.cacheService.getData('user') as User);
    } catch (ex) {
      return {
        email: '',
        firstName: '',
        lastName: ''
      } as User;
    }
  }

}
