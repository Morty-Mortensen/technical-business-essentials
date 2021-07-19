import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./views/login/login.component";
import {RegisterComponent} from "./views/register/register.component";
import {HomeComponent} from "./views/home/home.component";
import {FortuneFivehundredComponent} from "./views/fortune-fivehundred/fortune-fivehundred.component";
import {FortuneFivehundredCompanyInfoComponent} from "./views/fortune-fivehundred/components/fortune-fivehundred-company-info/fortune-fivehundred-company-info.component";
import {ForbiddenComponent} from "./views/forbidden/forbidden.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'forbidden', component: ForbiddenComponent},
  {
    path: 'fortune-fivehundred', component: FortuneFivehundredComponent, children: [
      {path: 'companies', component: FortuneFivehundredCompanyInfoComponent}
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
