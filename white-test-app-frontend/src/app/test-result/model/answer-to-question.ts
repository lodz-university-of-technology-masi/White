export class AnswerToQuestion {
  questionId: number;
  answer: string;

  constructor(questionId: number, answer: string) {
    this.questionId = questionId;
    this.answer = answer;
  }
}
