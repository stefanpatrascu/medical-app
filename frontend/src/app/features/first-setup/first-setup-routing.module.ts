import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { FirstSetupComponent } from "./first-setup.component";

const routes: Routes = [
  {
    path: '',
    component: FirstSetupComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FirstSetupRoutingModule {
}
