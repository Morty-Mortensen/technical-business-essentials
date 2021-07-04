import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NavbarComponent} from './views/navbar/navbar.component';
import {MatMenuModule} from "@angular/material/menu";
import {MatButtonModule} from "@angular/material/button";
import {MatCardModule} from "@angular/material/card";
import {LoginComponent} from './views/login/login.component';
import {RegisterComponent} from './views/register/register.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {CommonModule, CurrencyPipe, DecimalPipe, PercentPipe, TitleCasePipe} from "@angular/common";
import {HomeComponent} from './views/home/home.component';
import {MatInputModule} from "@angular/material/input";
import {InputComponent} from './components/input/input.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {FooterComponent} from './views/footer/footer.component';
import {MatIconModule} from "@angular/material/icon";
import {FortuneFivehundredComponent} from './views/fortune-fivehundred/fortune-fivehundred.component';
import {FortuneFivehundredCompanyInfoComponent} from './views/fortune-fivehundred/components/fortune-fivehundred-company-info/fortune-fivehundred-company-info.component';
import {SubHeaderComponent} from './components/sub-header/sub-header.component';
import {DatepickerRangeComponent} from './components/datepicker-range/datepicker-range.component';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {HttpClientModule} from "@angular/common/http";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatTableModule} from "@angular/material/table";
import {TechnicalDialogComponent} from './components/technical-dialog/technical-dialog.component';
import {MatDialogModule} from "@angular/material/dialog";
import {HoverClassDirective} from './directive/hover-class.directive';
import {LoadingComponent} from './components/loading/loading.component';
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    InputComponent,
    FooterComponent,
    FortuneFivehundredComponent,
    FortuneFivehundredCompanyInfoComponent,
    SubHeaderComponent,
    DatepickerRangeComponent,
    TechnicalDialogComponent,
    HoverClassDirective,
    LoadingComponent,
  ],
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatMenuModule,
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatPaginatorModule,
    MatTableModule,
    MatDialogModule,
    MatDatepickerModule,
    MatNativeDateModule,
    HttpClientModule,
    MatProgressSpinnerModule,
  ],
  providers: [CurrencyPipe, DecimalPipe, TitleCasePipe, PercentPipe],
  bootstrap: [AppComponent]
})
export class AppModule {
}
