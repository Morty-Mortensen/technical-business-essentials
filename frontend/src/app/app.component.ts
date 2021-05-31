import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";

// @ts-ignore
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';

  constructor(private http: HttpClient) {

  }

  public getRequest() {
    return this.http.get('http://localhost:8080/hello')
      .subscribe(response => {
        console.log(response);
      });
  }

}
