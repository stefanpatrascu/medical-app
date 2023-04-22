import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RouteEnum } from "./enums/route.enum";

const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {
    path: RouteEnum.LOGIN_PATH,
    loadChildren: () => import('./features/login/login.module').then(m => m.LoginModule)
  },
  {
    path: RouteEnum.EDIT_ACCOUNT,
    loadChildren: () => import('./features/edit-account/edit-account.module').then(m => m.EditAccountModule)
  },
  {
    path: RouteEnum.ADMIN_DASHBOARD_PATH,
    loadChildren: () => import('./features/admin-dashboard/admin-dashboard.module').then(m => m.AdminDashboardModule)
  },
  {
    path: RouteEnum.PATIENT_DASHBOARD,
    loadChildren: () => import('./features/patient-dashboard/patient-dashboard.module').then(m => m.PatientDashboardModule)
  },
  {
    path: RouteEnum.DOCTOR_DASHBOARD_PATH,
    loadChildren: () => import('./features/doctor-dashboard/doctor-dashboard.module').then(m => m.DoctorDashboardModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
