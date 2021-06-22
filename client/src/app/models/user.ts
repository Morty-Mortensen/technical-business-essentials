export class User {
  private readonly _firstName: string;
  private readonly _lastName: string;
  private readonly _userName: string;
  private readonly _imageUrl: string = '';

  constructor(firstName: string, lastName: string, userName: string, imageUrl: string = '') {
    this._firstName = firstName;
    this._lastName = lastName;
    this._userName = userName;
    this._imageUrl = imageUrl;
  }

  get firstName(): string {
    return this._firstName;
  }

  get lastName(): string {
    return this._lastName;
  }

  get userName(): string {
    return this._userName;
  }

  get fullName(): string {
    return this._firstName + ' ' + this._lastName;
  }

  get imageUrl(): string {
    return this._imageUrl;
  }
}


