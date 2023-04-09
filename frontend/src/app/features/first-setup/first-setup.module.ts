import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FirstSetupComponent } from './first-setup.component';
import { FirstSetupRoutingModule } from "./first-setup-routing.module";
@NgModule({
  declarations: [
    FirstSetupComponent
  ],
  imports: [
    CommonModule,
    FirstSetupRoutingModule
  ]
})
export class FirstSetupModule { }
