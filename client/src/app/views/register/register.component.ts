import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  public firstName: string = '';
  public lastName: string = '';
  public username: string = '';
  public password: string = '';

  constructor() {
  }

  ngOnInit(): void {
  }

  public isValid(): boolean {
    return this.firstName !== ''
      && this.lastName !== ''
      && this.username !== ''
      && this.password !== ''
  }
}
