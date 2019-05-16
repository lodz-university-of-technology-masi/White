import {Component, Input, OnInit} from '@angular/core';
import {Question, Questions} from "../test-templates/model/question";

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {

  questions: Questions;

  constructor() { }

  ngOnInit() {
    if (this.questions === null || this.questions === undefined) {
      this.questions = new Questions();
      this.questions.questions = [];
    }
  }

  add(): void {
    const question = new Question();
    question.answers = [];
    question.content = '';
    question.questionType = 'OPEN';
    this.questions.questions.push(question);
  }

  delete(id: number): void {
    this.questions.questions.splice(id, 1);
  }
}
