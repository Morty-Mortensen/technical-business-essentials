import {Component, OnInit} from '@angular/core';
import Link from "../../models/link";

@Component({
  selector: 'app-fortune-fivehundred',
  templateUrl: './fortune-fivehundred.component.html',
  styleUrls: ['./fortune-fivehundred.component.scss']
})
export class FortuneFivehundredComponent implements OnInit {

  public fortuneFivehundredLinks: Link[] = [
    {
      name: 'Companies',
      route: '/fortune-fivehundred/companies'
    },
    {
      name: 'Finances',
      route: '/fortune-fivehundred/finances'
    },
    {
      name: 'Stocks',
      route: '/fortune-fivehundred/stocks'
    }
  ]

  constructor() {
  }

  ngOnInit(): void {
  }

}
