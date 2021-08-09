import {TemplateRef} from "@angular/core";

export interface TechnicalDialogData<T = undefined> {
  title: string;
  template: TemplateRef<any>;
  context?: T;
}

export interface TechnicalDialogOptions {
  width: number;
  disableClose: boolean;
}
