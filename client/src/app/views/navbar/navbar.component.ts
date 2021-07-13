import {Component, OnInit} from '@angular/core';
import {CacheService} from "../../services/cache.service";
import {User} from "../../models/user";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  public currUser: User = {
    email: '',
    firstName: '',
    lastName: ''
  };

  constructor(private cacheService: CacheService) {
  }

  ngOnInit(): void {
    this.cacheService.user.subscribe(user => {
      this.currUser = user;
    })
  }

  public logout(): void {
    this.cacheService.next({
      email: '',
      firstName: '',
      lastName: ''
    })
  }

}
