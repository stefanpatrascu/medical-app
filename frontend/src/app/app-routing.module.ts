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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
