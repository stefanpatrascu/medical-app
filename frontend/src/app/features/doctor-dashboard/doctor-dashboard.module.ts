import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DoctorDashboardComponent } from "./doctor-dashboard.component";
import { DoctorDashboardRoutingModule } from "./doctor-dashboard-routing.module";

@NgModule({
  declarations: [DoctorDashboardComponent],
  imports: [
    CommonModule,
    DoctorDashboardRoutingModule
  ]
})
export class DoctorDashboardModule { }
