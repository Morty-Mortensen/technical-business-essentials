import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public email: string = '';
  public password: string = '';

  constructor(private loginService: LoginService) {
  }

  ngOnInit(): void {
  }

  public isValid(): boolean {
    return this.email !== '' && this.password !== '';
  }

  public login(): void {
    this.loginService.register({
      email: this.email,
      password: this.password
    }).subscribe(isValid => {
      if (isValid) {
        alert("Valid! You have logged in :)");
      } else {
        alert("Invalid...Please try again :(");
      }
    }, error => {
      alert("No user exists with the given credentials.");
    })
  }

}
