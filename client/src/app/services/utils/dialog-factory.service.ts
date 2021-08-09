import {Injectable} from '@angular/core';
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {DialogService} from "./dialog.service";
import {first} from "rxjs/operators";
import {
  TechnicalDialogComponent,
} from "../../components/technical-dialog/technical-dialog.component";
import {TechnicalDialogData, TechnicalDialogOptions} from "../../models/dialog";

@Injectable({
  providedIn: 'root'
})
export class DialogFactoryService<T = undefined> {

  constructor(private dialog: MatDialog) {
  }

  open(
    dialogData: TechnicalDialogData<T>,
    options: TechnicalDialogOptions = {width: 500, disableClose: true}
  ): DialogService<T> {
    const dialogRef = this.dialog.open<TechnicalDialogComponent<T>, TechnicalDialogData<T>>(
      TechnicalDialogComponent,
      {
        ...this.fetchOptions(options),
        data: dialogData
      }
    )

    dialogRef.afterClosed().pipe(first())

    return new DialogService(dialogRef)
  }

  private fetchOptions({
                         width,
                         disableClose
                       }: TechnicalDialogOptions): Pick<MatDialogConfig<TechnicalDialogData<T>>,
    'width' | 'disableClose'> {
    return {
      width: `${width}px`,
      disableClose
    }
  }
}
