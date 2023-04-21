export interface IAccountResponse {
  email: string;
  role: string; // TODO: need to be replaced with an Enum
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
