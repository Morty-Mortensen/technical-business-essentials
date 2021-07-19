import {Component, OnInit} from '@angular/core';
import {CacheService} from "../../services/cache.service";
import {User} from "../../models/user";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private cacheService: CacheService) {
  }

  ngOnInit(): void {

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
