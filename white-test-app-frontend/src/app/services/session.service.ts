import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {TokenInfo} from '../login/model/token-info';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private userLoggedIn = new Subject();
  private loggedUser = new TokenInfo();

  constructor() {
  }

  watchSession(): Observable<any> {
    return this.userLoggedIn;
  }

  getUser(): TokenInfo {
    const user: TokenInfo = JSON.parse(localStorage.getItem('token'));
    if (user == null) {
      this.resetSession();
      return new TokenInfo();
    }
    return user;
  }

  setUser(user: TokenInfo): void {
    console.log(JSON.parse(window.atob(user.accessToken.split('.')[1])));
    this.loggedUser = JSON.parse(window.atob(user.accessToken.split('.')[1]));
    this.loggedUser.accessToken = user.accessToken;
    localStorage.setItem('token', JSON.stringify(this.loggedUser));
    this.userLoggedIn.next(true);
  }

  resetSession() {
    localStorage.removeItem('token');
    this.userLoggedIn.next(false);
  }

  hasUserRole(role: string) {
    const user: TokenInfo = this.getUser();
    if (user == null) {
      return false;
    }
    return user.authorities[0].authority === role;
  }
}
