import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public firstName: string = '';
  public lastName: string = '';
  public username: string = '';
  public password: string = '';

  constructor() {
  }

  ngOnInit(): void {
  }

}
