import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InvalidFieldDirective } from "./invalid-field.directive";



@NgModule({
  declarations: [InvalidFieldDirective],
  exports: [InvalidFieldDirective],
  imports: [
    CommonModule
  ]
})
export class DirectivesModule { }
