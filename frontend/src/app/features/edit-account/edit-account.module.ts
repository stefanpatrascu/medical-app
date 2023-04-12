import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { EditAccountComponent } from './edit-account.component';
import { ButtonModule } from "primeng/button";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { InputTextModule } from "primeng/inputtext";
import { CheckboxModule } from "primeng/checkbox";
import { MessagesModule } from "primeng/messages";
import { CalendarModule } from "primeng/calendar";
import { MyAvatarComponent } from './components/my-avatar/my-avatar.component';
import { EditAccountRoutingModule } from "./edit-account-routing.module";
import { ValidationModule } from "../../shared/modules/validation/validation.module";

@NgModule({
  declarations: [
    EditAccountComponent,
    MyAvatarComponent
  ],
  imports: [
    CommonModule,
    ButtonModule,
    FormsModule,
    InputTextModule,
    ReactiveFormsModule,
    CheckboxModule,
    MessagesModule,
    CalendarModule,
    EditAccountRoutingModule,
    ValidationModule
  ],
  providers: [DatePipe],
  exports: [EditAccountComponent]
})
export class EditAccountModule {
}
