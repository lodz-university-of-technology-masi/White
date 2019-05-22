export class QuestionCheck {
  id: number;
  questionId;
  answer: string;
  correct: boolean;

  constructor(id: number, isCorrect: boolean) {
    this.id = id;
    this.correct = isCorrect;
  }
}
