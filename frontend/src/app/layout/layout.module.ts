import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LayoutComponent } from './layout.component';
import { RouterOutlet } from "@angular/router";
import { LogoModule } from "../shared/modules/logo/logo.module";
import { MenubarModule } from "primeng/menubar";

@NgModule({
  declarations: [
    LayoutComponent
  ],
  imports: [
    CommonModule,
    RouterOutlet,
    LogoModule,
    MenubarModule
  ],
  exports: [LayoutComponent]
})
export class LayoutModule {
}
