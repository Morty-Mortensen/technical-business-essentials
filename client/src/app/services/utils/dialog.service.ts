import {TemplateRef} from '@angular/core';
import {first} from "rxjs/operators";
import {MatDialogRef} from "@angular/material/dialog";
import {TechnicalDialogComponent} from "../../components/technical-dialog/technical-dialog.component";

type DialogRef<T> = MatDialogRef<TechnicalDialogComponent<T>>

export class DialogService<T = undefined> {

  opened$ = this.dialogRef.afterOpened().pipe(first())

  constructor(private dialogRef: DialogRef<T>) {
  }

  get context() {
    return this.dialogRef.componentInstance.data.context
  }

  close() {
    this.dialogRef.close()
  }

  setTitle(title: string): void {
    this.dialogRef.componentInstance.data.title = title
  }

  setTemplate(template: TemplateRef<any>): void {
    this.dialogRef.componentInstance.data.template = template
  }
}
