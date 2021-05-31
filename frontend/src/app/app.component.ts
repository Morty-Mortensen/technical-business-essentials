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

  public textResponse: any = '';

  constructor(private http: HttpClient) {

  }

  public getRequest() {
    return this.http.get('https://technicalbusinessessentialsserver.us-west-1.elasticbeanstalk.com/test')
      .subscribe(response => {
        this.textResponse = response;
      });
  }

}
