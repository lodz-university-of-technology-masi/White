import {Injectable} from '@angular/core';
import {HttpService} from './http-service.service';
import {Configuration} from '../configuration';
import {TestTemplate} from '../test-templates/model/test-template';
import {Observable} from 'rxjs';
import {ApiResponse} from '../test-templates/model/ApiResponse';


@Injectable({
  providedIn: 'root'
})
export class TestTemplateService {

  private endpoint = this.config.ApiPath + '/testtemplate';

  constructor(private httpService: HttpService,
              private config: Configuration) {
  }

  getAll(): Observable<TestTemplate[]> {
    return this.httpService.get<TestTemplate[]>(this.endpoint);
  }

  assignPositionToTest(id, positionId) {
    const url = this.endpoint + `/setposition/${id}/${positionId}`;
    return this.httpService.put<ApiResponse>(url);
  }

}
