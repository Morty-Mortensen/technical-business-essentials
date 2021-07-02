import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

// Subject to change...
export interface DialogData {
  animal: string;
  name: string;
}

@Component({
  selector: 'app-technical-dialog',
  templateUrl: './technical-dialog.component.html',
  styleUrls: ['./technical-dialog.component.scss']
})
export class TechnicalDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<TechnicalDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: DialogData) {
  }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
