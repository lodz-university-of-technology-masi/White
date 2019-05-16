import {Component, Input, OnInit} from '@angular/core';
import {Question} from "../../test-templates/model/question";

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent implements OnInit {

  constructor() { }

  @Input() question: Question;
  questionTypes =['OPEN', 'CHOICE', 'NUMBER', 'SCALE'];
  questionType: string;

  ngOnInit() {
  }

  setQuestionType(type: string): void {
    this.questionType = type;
  }
}
