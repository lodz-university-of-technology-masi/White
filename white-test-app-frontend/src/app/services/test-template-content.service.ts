import {Injectable} from '@angular/core';
import {HttpService} from './http-service.service';
import {Configuration} from '../configuration';
import {TestTemplate} from '../test-templates/model/test-template';
import {Observable} from 'rxjs';
import {TestTemplateDetail} from '../test-templates/model/test-template-detail';


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

  edit(template: TestTemplateDetail) {
    return this.httpService.put(this.endpoint, template);
  }

  get(id: number): Observable<TestTemplateDetail> {
    return this.httpService.get<TestTemplateDetail>(this.endpoint + '/' + id);
  }

  exportCSV(testId: Number, test: TestTemplate): any {
    return this.httpService.get(this.endpoint + '/downloadcsv/' + testId, {responseType: 'arraybuffer', observe: 'response'});
  }

  importCsv(templateId: number, file: File) {
    return this.httpService.post(this.endpoint + '/importcsv/' + templateId, file);
  }
}
