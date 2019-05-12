import {Injectable} from '@angular/core';
import {HttpService} from './http-service.service';
import {Configuration} from '../configuration';
import {TestResult} from '../test-result/model/test-result';
import {TestCheck} from '../test-check/model/test-check';
import {Observable} from 'rxjs';
import {TestResult} from '../all-test-results/model/test-result';

@Injectable({
  providedIn: 'root'
})
export class TestResultService {

  private endpoint = this.config.ApiPath + '/testresult';

  constructor(private httpService: HttpService,
              private config: Configuration) {
  }

  get(id: number): Observable<TestCheck> {
    return this.httpService.get(this.endpoint + '/' + id);
  }

  add(testResult: TestResult) {
    return this.httpService.post(this.endpoint, testResult);
  }

  addChecked(testChecked: TestCheck) {
    return this.httpService.put(this.endpoint, testChecked);
  }

  getAll(): Observable<TestResult[]> {
    return this.httpService.get(this.endpoint);
  }
}
