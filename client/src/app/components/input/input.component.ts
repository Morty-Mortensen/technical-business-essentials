import {Component, Input, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.scss']
})
export class InputComponent implements OnInit {

  @Input() label: string = '';
  @Input() type: string = 'text';
  @Output() output = new EventEmitter<string>();

  public value = new FormControl('', [
    Validators.required
  ]);

  constructor() {
  }

  ngOnInit(): void {
  }

  public inputSelected(): void {
    this.output.emit(this.value.value);
  }

}
