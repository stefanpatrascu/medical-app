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
    path: RouteEnum.FIRST_SETUP_PATH,
    loadChildren: () => import('./features/first-setup/first-setup.module').then(m => m.FirstSetupModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
