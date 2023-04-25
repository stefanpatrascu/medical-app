import { Component } from '@angular/core';
import { MenuItem } from "primeng/api";
import { AccountApiService } from "../api/account/account-api.service";
import { RouteEnum } from "../enums/route.enum";
import { IAccountResponse } from "../api/account/account.interface";
import { RoleEnum } from "../enums/role.enum";
import { adminMenu, doctorMenu, patientMenu } from "./layout.constants";

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent {

  menuItems!: MenuItem[];

  constructor(private accountApiService: AccountApiService) {
  }

  public logout(): void {
    this.accountApiService.logout().subscribe(() => {
      window.location.reload();
    });
  }


  ngOnInit() {
    this.populateMenuItems();
  }

  private populateMenuItems(): void {
    this.accountApiService.getMyAccount()
      .subscribe((account: IAccountResponse | null) => {
      let menu: MenuItem[] = [];

      if (account?.role === RoleEnum.ADMIN) {
        menu = [...adminMenu];
      } else if (account?.role === RoleEnum.DOCTOR) {
        menu = [...doctorMenu];
      } else if (account?.role === RoleEnum.PATIENT) {
        menu = [...patientMenu];
      }

      menu.push({
        label: 'Edit Account',
        icon: 'pi pi-fw pi-pencil',
        routerLink: RouteEnum.EDIT_ACCOUNT_PATH
      });

      menu.push({
        label: 'Logout',
        icon: 'pi pi-fw pi-power-off',
        command: () => this.logout()
      })

      this.menuItems = menu;
    });
  }

}
