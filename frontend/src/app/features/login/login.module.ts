import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LoginComponent} from './login.component';
import {LoginRoutingModule} from "./login-routing.module";
import { InputTextModule } from "primeng/inputtext";
import { ButtonModule } from "primeng/button";
import { LogoModule } from "../../shared/logo/logo.module";


@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    LoginRoutingModule,
    CommonModule,
    InputTextModule,
    ButtonModule,
    LogoModule
  ]
})
export class LoginModule {
}
