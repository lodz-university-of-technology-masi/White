import {Component, Input, OnInit} from '@angular/core';
import {Question} from "../test-templates/model/question";

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {

  @Input() questions: Question[];

  constructor() { }

  ngOnInit() {
    if (this.questions === null || this.questions === undefined) {
      this.questions = [];
    }
  }

  add(): void {
    const question = new Question();
    question.answers = [];
    question.content = '';
    question.questionType = 'OPEN';
    this.questions.push(question);
  }

  delete(id: number): void {
    this.questions.splice(id, 1);
  }
}
