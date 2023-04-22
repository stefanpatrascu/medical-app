import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PatientDashboardComponent } from './patient-dashboard.component';
import { PatientDashboardRoutingModule } from "./patient-dashboard-routing.module";

@NgModule({
  declarations: [
    PatientDashboardComponent
  ],
  imports: [
    CommonModule,
    PatientDashboardRoutingModule
  ]
})
export class PatientDashboardModule { }
