import {Component, OnInit} from '@angular/core';
import {TestTemplateDetail} from '../test-templates/model/test-template-detail';
import {TestTemplateContentService} from '../services/test-template-content.service';
import {TestResultService} from '../services/test-result.service';
import {MessageService} from '../services/message.service';
import {ActivatedRoute} from '@angular/router';
import {TestCheck} from './model/test-check';
import {QuestionCheck} from './model/question-check';

@Component({
  selector: 'app-test-check',
  templateUrl: './test-check.component.html',
  styleUrls: ['./test-check.component.css']
})
export class TestCheckComponent implements OnInit {
  testResultId: number;
  template: TestTemplateDetail;
  testTemplateId: number;
  testChecked: TestCheck = new TestCheck();

  constructor(private templateContentService: TestTemplateContentService,
              private testResultService: TestResultService,
              private messageService: MessageService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.testResultId = +params['id'];
      this.testResultService.get(this.testResultId).subscribe(tr => {
        this.testChecked = tr;
        this.loadTemplate();
      });
    });
  }

  loadTemplate() {
    this.templateContentService.get(this.testChecked.testTemplateId).subscribe(t => {
      console.log(t);
      this.template = t;
      this.testChecked.questionChecks.forEach(a => this.template.questions.filter(q => q.id === a.questionId)[0].answer = a.answer);
      console.log(this.template);
      console.log(this.testChecked);
    });
  }

  save() {
    this.testChecked.questionChecks = [];
    this.template.questions.forEach(q => this.testChecked.questionChecks.push(new QuestionCheck(q.id, q.isCorrect)));
    console.log(this.testChecked);
    this.testResultService.addChecked(this.testChecked).subscribe(s => this.messageService.success('Sukces'),
      e => {
        this.messageService.error('Błąd');
        console.log(e);
      });
  }

}
