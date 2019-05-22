import {Injectable} from '@angular/core';
import {HttpService} from './http-service.service';
import {Configuration} from '../configuration';
import {Observable} from 'rxjs';
import {Redactor} from '../redactors-management/model/redactor';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private endpoint = this.config.ApiPath + '/account';
  private redactors = this.endpoint + '/redactors';

  constructor(private httpService: HttpService,
              private config: Configuration) {
  }

  getAllRedactors(): Observable<Redactor[]> {
    return this.httpService.get<Redactor[]>(this.redactors);
  }

  deleteRedactor(id: string): Observable<Redactor[]> {
    return this.httpService.delete<Redactor[]>(this.redactors + '/' + id);
  }

  addRedactor(redactor: Redactor) {
    return this.httpService.post(this.redactors, redactor);
  }

  updateRedactor(redactor: Redactor) {
    return this.httpService.put(this.redactors, redactor);
  }
}

