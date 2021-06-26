import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import DateRange from "../../models/date-range";

@Component({
  selector: 'app-datepicker-range',
  templateUrl: './datepicker-range.component.html',
  styleUrls: ['./datepicker-range.component.scss']
})
export class DatepickerRangeComponent implements OnInit {

  @Output() output = new EventEmitter<DateRange>();

  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl()
  });

  constructor() {
  }

  ngOnInit(): void {
  }

  public dateRangeSelected(): void {
    this.output.emit({
      start: this.range.controls.start.value,
      end: this.range.controls.end.value
    } as DateRange);
  }

}
