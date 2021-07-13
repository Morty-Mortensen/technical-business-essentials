import {Injectable} from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";

@Injectable({
  providedIn: 'root'
})
export class SnackbarService {

  constructor(private snackbar: MatSnackBar) {
  }

  public show(msg: string) {
    this.snackbar.open(msg, undefined, {
      duration: 3000
    });
  }
}
