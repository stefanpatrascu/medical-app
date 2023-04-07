import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { CredentialsInterceptor } from "./interceptors/credentials.interceptor";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { CustomToastModule } from "./shared/modules/toast/toast.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
    CustomToastModule
  ],
  providers: [
    {
    provide: HTTP_INTERCEPTORS,
    useClass: CredentialsInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
