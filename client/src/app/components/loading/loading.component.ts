import {Component, Input, OnInit} from '@angular/core';
import {LoadingService} from "../../services/utils/loading.service";

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.scss']
})
export class LoadingComponent implements OnInit {

  public loading: boolean = false;

  constructor(private loadingService: LoadingService) {
  }

  ngOnInit(): void {
    this.loadingService.loading.subscribe((value) => {
      this.loading = value;
    })
  }

}
