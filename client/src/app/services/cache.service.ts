import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CacheService {

  constructor() {
  }

  public setData(key: string, value: any): void {
    localStorage.setItem(key, JSON.stringify(value));
  }

  public getData(key: string): any {

    const value = localStorage.getItem(key);

    if (value === null) {
      throw new Error("No value for " + key);
    }

    return JSON.parse(value);
  }

  public deleteData(key: string): void {
    localStorage.removeItem(key);
  }

  public clearData(): void {
    localStorage.clear();
  }
}
