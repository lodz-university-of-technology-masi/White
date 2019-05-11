import {Injectable} from '@angular/core';
import {HttpService} from './http-service.service';
import {Configuration} from '../configuration';
import {TestTemplate} from '../test-templates/model/test-template';

@Injectable({
  providedIn: 'root'
})
export class TestTemplateContentService {

  private endpoint = this.config.ApiPath + '/testtemplatecontent';

  constructor(private httpService: HttpService,
              private config: Configuration) {
  }

  generatePDF(testId: Number, test: TestTemplate): any {
    return this.httpService.post(this.endpoint + '/downloadpdf/' + testId, test, {responseType: 'arraybuffer', observe: 'response'});
  }
}
