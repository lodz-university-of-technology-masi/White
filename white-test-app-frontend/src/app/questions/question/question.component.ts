import {Component, Input, OnInit} from '@angular/core';
import {Question} from "../../test-templates/model/question";

export interface Type {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent implements OnInit {

  constructor() { }

  @Input() question: Question;
  questionType: string;

  questionTypes: Type[] = [
    {value: 'OPEN', viewValue: 'Otwarte'},
    {value: 'CHOICE', viewValue: 'Wyboru'},
    {value: 'NUMBER', viewValue: 'Numeryczne'},
    {value: 'SCALE', viewValue: 'Skali'},
  ];
  ngOnInit() {
  }

  setQuestionType(type: string): void {
    this.questionType = type;
  }
}
