import {Component, OnInit} from '@angular/core';
import {RegisterService} from "../../services/register.service";
import {CacheService} from "../../services/cache.service";
import {Router} from "@angular/router";
import {SnackbarService} from "../../services/snackbar.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  public firstName: string = '';
  public lastName: string = '';
  public email: string = '';
  public password: string = '';

  constructor(private registerService: RegisterService,
              private cacheService: CacheService,
              private router: Router,
              private snackbar: SnackbarService) {
  }

  ngOnInit(): void {
  }

  public isValid(): boolean {
    return this.firstName !== ''
      && this.lastName !== ''
      && this.email !== ''
      && this.password !== ''
  }

  public register(): void {
    this.registerService.register({
      user: {
        email: this.email,
        firstName: this.firstName,
        lastName: this.lastName
      },
      password: this.password
    }).subscribe(user => {
      this.cacheService.setData('user', user);
      this.snackbar.show('Successfully registered!');
      this.router.navigate(['/']);
    }, error => {
      alert("An unexpected error occurred :(")
    })
  }
}
