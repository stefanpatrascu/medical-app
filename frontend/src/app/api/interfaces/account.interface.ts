

export interface IAccountResponse {
  email: string;
  firstName: string;
  lastName: string;
  role: string; // TODO: need to be replaced with an Enum
  userInfo: IUserInfo;
}


interface IUserInfo {
  cnp: string;
  birthDate: string;
}
