import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public username: string = '';
  public password: string = '';

  constructor() {
  }

  ngOnInit(): void {
  }

  private isValid(): boolean {
    return this.username !== '' && this.password !== '';
  }

  async onSubmit(): Promise<void> {
    try {
      if (this.isValid()) {

      } else {

      }
    } catch (ex) {

    }
  }

}
