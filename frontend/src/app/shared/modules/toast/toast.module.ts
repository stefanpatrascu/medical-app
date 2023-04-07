import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomToastService } from "./toast.service";
import { ToastModule } from "primeng/toast";
import { MessageService } from "primeng/api";

@NgModule({
  declarations: [],
  providers: [MessageService, CustomToastService],
  imports: [
    CommonModule,
    ToastModule
  ],
  exports: [ToastModule]
})
export class CustomToastModule {
}
