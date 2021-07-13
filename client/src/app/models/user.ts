export interface User {
  email: string;
  firstName: string;
  lastName: string;
  imageUrl?: string;
}

export interface PostUserRequest {
  user: User;
  password: string;
}

export interface ValidateUserRequest {
  email: string;
  password: string;
}


