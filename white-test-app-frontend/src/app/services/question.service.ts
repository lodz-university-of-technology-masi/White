import { Injectable } from '@angular/core';
import {HttpService} from "./http-service.service";
import {Configuration} from "../configuration";
import {Question} from "../test-templates/model/question";
import {Observable} from "rxjs/Observable";

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  private endpoint = this.config.ApiPath + '/testtemplatecontent';

  constructor(private httpService: HttpService,
              private config: Configuration) {
  }

  add(testId: number, questions: Question[]) {
    return this.httpService.post( `/${this.endpoint}/'addquestion'/${testId}`, questions);
  }

  get(testId: number): Observable<Question[]> {
    return this.httpService.get<Question[]>(`/${this.endpoint}/'addquestion'/${testId}`);
  }
}
