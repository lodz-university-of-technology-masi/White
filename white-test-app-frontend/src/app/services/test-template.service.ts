import {Injectable} from '@angular/core';
import {HttpService} from './http-service.service';
import {Configuration} from '../configuration';
import {TestTemplate} from '../test-templates/model/test-template';
import {Observable} from 'rxjs';
import {NewTemplate} from '../test-templates/model/new-template';
import {ApiResponse} from '../test-templates/model/ApiResponse';
import {TestTemplateDetail} from '../test-templates/model/test-template-detail';


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

  add(template: NewTemplate) {
    return this.httpService.post(this.endpoint, template);
  }

  assignPositionToTest(id, positionId) {
    const url = this.endpoint + `/setposition/${id}/${positionId}`;
    return this.httpService.putWithoutBody<ApiResponse>(url);
  }

  translate(id: number, currentLang: string) {
    return this.httpService.put(this.endpoint + '/translate/' + id + '?lang=' + currentLang, {});
  }

  deleteTest(id: number, currentLang: string){
    return this.httpService.delete(this.endpoint + '/' + id + '?lang=' + currentLang);
  }

}
