import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {TestTemplateDetail} from '../test-templates/model/test-template-detail';
import {TestTemplateContentService} from '../services/test-template-content.service';
import {TestResult} from './model/test-result';
import {AnswerToQuestion} from './model/answer-to-question';
import {TestResultService} from '../services/test-result.service';
import {MessageService} from '../services/message.service';

@Component({
  selector: 'app-test-result',
  templateUrl: './test-result.component.html',
  styleUrls: ['./test-result.component.css']
})
export class TestResultComponent implements OnInit {
  templateId: number;
  template: TestTemplateDetail;
  testResult: TestResult = new TestResult();

  constructor(private templateContentService: TestTemplateContentService,
              private testResultService: TestResultService,
              private messageService: MessageService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.templateId = +params['id'];
      this.loadTemplate();
    });
  }

  loadTemplate() {
    this.templateContentService.get(this.templateId).subscribe(t => {
      console.log(t);
      this.template = t;
      this.testResult.testTemplateId = this.templateId;
    });
  }

  save() {
    this.testResult.answers = [];
    this.template.questions.forEach(q => this.testResult.answers.push(new AnswerToQuestion(q.id, q.answer)));
    this.testResultService.add(this.testResult).subscribe(s => this.messageService.success('Sukces'),
      e => { this.messageService.error('Błąd'); console.log(e); });
  }

}
