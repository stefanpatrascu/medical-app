import { RoleEnum } from "../../enums/role.enum";

export interface IAccountResponse {
  email: string;
  role: RoleEnum;
  userInfo: IUserInfo;
}

interface IUserInfo {
  firstName: string;
  lastName: string;
  cnp: string;
  birthDate: string;
}


export interface IAccountUpdateRequest {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
}
