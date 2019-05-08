import {Injectable} from '@angular/core';
import {HttpService} from './http-service.service';
import {Configuration} from '../configuration';
import {TestResult} from '../test-result/model/test-result';

@Injectable({
  providedIn: 'root'
})
export class TestResultService {

  private endpoint = this.config.ApiPath + '/testresult';

  constructor(private httpService: HttpService,
              private config: Configuration) {
  }

  add(testResult: TestResult) {
    return this.httpService.post(this.endpoint, testResult);
  }
}
