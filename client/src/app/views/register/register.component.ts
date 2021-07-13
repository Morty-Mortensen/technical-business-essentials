import {Component, OnInit} from '@angular/core';
import {RegisterService} from "../../services/register.service";

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

  constructor(private registerService: RegisterService) {
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
    }).subscribe(response => {
      // Insert user into cache.
      console.log(response);
      alert("Welcome " + response.firstName);
    }, error => {
      alert("An unexpected error occurred :(")
    })
  }
}
