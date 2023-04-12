import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ValidationComponent } from './validation.component';



@NgModule({
  declarations: [
    ValidationComponent,
    ValidationComponent
  ],
  imports: [
    CommonModule,
  ],
  exports: [ValidationComponent]
})
export class ValidationModule { }
