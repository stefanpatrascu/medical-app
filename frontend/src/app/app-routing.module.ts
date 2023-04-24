import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RouteEnum } from "./enums/route.enum";
import { LoginGuard } from "./guards/login.guard";
import { AuthorityGuard } from "./guards/authority.guard";
import { RoleEnum } from "./enums/role.enum";
import { LayoutComponent } from "./layout/layout.component";

const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {
    path: RouteEnum.LOGIN_PATH,
    canActivate: [LoginGuard],
    loadChildren: () => import('./features/login/login.module').then(m => m.LoginModule)
  },
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: RouteEnum.EDIT_ACCOUNT_PATH,
        data: {authorities: [RoleEnum.ADMIN, RoleEnum.DOCTOR, RoleEnum.PATIENT]},
        canActivate: [AuthorityGuard],
        loadChildren: () => import('./features/edit-account/edit-account.module').then(m => m.EditAccountModule)
      },
      {
        path: RouteEnum.ADMIN_DASHBOARD_PATH,
        data: {authorities: [RoleEnum.ADMIN]},
        canActivate: [AuthorityGuard],
        loadChildren: () => import('./features/admin-dashboard/admin-dashboard.module').then(m => m.AdminDashboardModule)
      },
      {
        path: RouteEnum.PATIENT_DASHBOARD_PATH,
        data: {authorities: [RoleEnum.PATIENT]},
        canActivate: [AuthorityGuard],
        loadChildren: () => import('./features/patient-dashboard/patient-dashboard.module').then(m => m.PatientDashboardModule)
      },
      {
        path: RouteEnum.DOCTOR_DASHBOARD_PATH,
        data: {authorities: [RoleEnum.DOCTOR]},
        canActivate: [AuthorityGuard],
        loadChildren: () => import('./features/doctor-dashboard/doctor-dashboard.module').then(m => m.DoctorDashboardModule)
      }
    ]
  },
  {path: '**', redirectTo: 'login', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
