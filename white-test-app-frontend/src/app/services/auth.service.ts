import {Injectable} from '@angular/core';
import {Configuration} from '../configuration';
import {HttpService} from './http-service.service';
import {TokenInfo} from '../login/model/token-info';
import {Observable} from 'rxjs';
import {AuthData} from '../login/model/auth-data';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private endpoint = this.config.ApiPath + '/auth';

  constructor(private httpService: HttpService,
              private config: Configuration) {
  }

  login(user: AuthData): Observable<TokenInfo> {
    return this.httpService.post(this.endpoint + '/signin', user);
  }
}
