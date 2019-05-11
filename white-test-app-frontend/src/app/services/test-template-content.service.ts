import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpService} from './http-service.service';
import {Configuration} from '../configuration';
import {TestTemplateDetail} from '../test-templates/model/test-template-detail';

@Injectable({
  providedIn: 'root'
})
export class TestTemplateContentService {

  private endpoint = this.config.ApiPath + '/testtemplatecontent';

  constructor(private httpService: HttpService,
              private config: Configuration) {
  }

  get(id: number): Observable<TestTemplateDetail> {
    return this.httpService.get<TestTemplateDetail>(this.endpoint + '/' + id);
  }
}
