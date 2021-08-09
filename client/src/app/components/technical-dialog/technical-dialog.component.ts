import {Component, Inject, TemplateRef} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-technical-dialog',
  templateUrl: './technical-dialog.component.html',
  styleUrls: ['./technical-dialog.component.scss']
})
export class TechnicalDialogComponent<T> {

  constructor(
    public dialogRef: MatDialogRef<TechnicalDialogComponent<T>>,
    @Inject(MAT_DIALOG_DATA)
    public data: {
      title: string;
      template: TemplateRef<any>;
      context: T;
    }
  ) {
  }
}
