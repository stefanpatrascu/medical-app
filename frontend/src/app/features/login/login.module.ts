import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LoginComponent} from './login.component';
import {LoginRoutingModule} from "./login-routing.module";
import { InputTextModule } from "primeng/inputtext";
import { ButtonModule } from "primeng/button";
import { LogoModule } from "../../shared/modules/logo/logo.module";
import { ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import {ValidationModule} from "../../shared/modules/validation/validation.module";


@NgModule({
  declarations: [
    LoginComponent
  ],
    imports: [
        ReactiveFormsModule,
        LoginRoutingModule,
        CommonModule,
        HttpClientModule,
        InputTextModule,
        ButtonModule,
        LogoModule,
        ValidationModule
    ]
})
export class LoginModule {
}
