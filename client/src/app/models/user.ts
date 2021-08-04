export interface User {
  email: string;
  firstName: string;
  lastName: string;
  token?: Token;
  imageUrl?: string;
}

export interface Token {
  token: string;
  time: number;
}

export interface PostUserRequest {
  user: User;
  password: string;
}

export interface ValidateUserRequest {
  email: string;
  password: string;
}

export interface LogoutUserRequest {
  token: string;
}


