import { Injectable } from '@angular/core';
import {HttpService} from "./http-service.service";
import {Configuration} from "../configuration";
import {Question, Questions} from "../test-templates/model/question";
import {Observable} from "rxjs/Observable";

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  private endpoint = this.config.ApiPath + '/testtemplate';

  constructor(private httpService: HttpService,
              private config: Configuration) {
  }

  add(testId: number, questions: Questions) {
    return this.httpService.post( `/${this.endpoint}/${testId}/'questions'`, questions);
  }

  get(testId: number): Observable<Questions> {
    return this.httpService.get<Questions>(`/${this.endpoint}/${testId}/'questions'`);
  }
}
