import {Component, Input, OnInit} from '@angular/core';
import {Answer} from "../../test-templates/model/answer";

@Component({
  selector: 'app-choice-scale-question',
  templateUrl: './choice-scale-question.component.html',
  styleUrls: ['./choice-scale-question.component.css']
})
export class ChoiceScaleQuestionComponent implements OnInit {

  constructor() { }

  @Input() answers: Answer[];

  ngOnInit() {
  }

  add(): void {
    const possibleAnswer = new Answer();
    possibleAnswer.content = '';
    this.answers.push(possibleAnswer);
  }

  delete(id: number): void  {
    this.answers.splice(id, 1);
  }
}
