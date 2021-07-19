import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../services/login.service";
import {CacheService} from "../../services/cache.service";
import {Router} from "@angular/router";
import {SnackbarService} from "../../services/snackbar.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public email: string = '';
  public password: string = '';

  constructor(private loginService: LoginService,
              private cacheService: CacheService,
              private router: Router,
              private snackbar: SnackbarService) {
  }

  ngOnInit(): void {
  }

  public isValid(): boolean {
    return this.email !== '' && this.password !== '';
  }

  public login(): void {
    this.loginService.login({
      email: this.email,
      password: this.password
    }).subscribe(user => {
      localStorage.setItem('user', JSON.stringify(user));
      this.cacheService.setData('user', user);
      this.snackbar.show('Successfully logged in!');
      this.router.navigate(['/']);
    }, error => {
      alert("Invalid credentials.");
    })
  }

}
