export class QuestionCheck {
  id: number;
  isCorrect: boolean;

  constructor(id: number, isCorrect: boolean) {
    this.id = id;
    this.isCorrect = isCorrect;
  }
}
