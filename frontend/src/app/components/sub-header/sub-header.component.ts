import {Component, OnInit, Input} from '@angular/core';
import Link from "../../models/link";

@Component({
  selector: 'app-sub-header',
  templateUrl: './sub-header.component.html',
  styleUrls: ['./sub-header.component.scss']
})
export class SubHeaderComponent implements OnInit {

  @Input() title: string = '';
  @Input() links: Link[] = [];

  constructor() {
  }

  ngOnInit(): void {
  }

}
