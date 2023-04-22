import { MenuItem } from "primeng/api";
import { RouteEnum } from "../enums/route.enum";

export const adminMenu: MenuItem[] = [
  {
    label: 'Dashboard',
    icon: 'pi pi-fw pi-home',
    routerLink: RouteEnum.ADMIN_DASHBOARD_PATH
  }];


export const doctorMenu: MenuItem[] = [{
  label: 'Dashboard',
  icon: 'pi pi-fw pi-home',
  routerLink: RouteEnum.DOCTOR_DASHBOARD_PATH
}];


export const patientMenu: MenuItem[] = [
  {
    label: 'Dashboard',
    icon: 'pi pi-fw pi-home',
    routerLink: RouteEnum.PATIENT_DASHBOARD_PATH
  }];
