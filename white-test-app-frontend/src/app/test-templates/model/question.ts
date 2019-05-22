import {Answer} from './answer';

export class Question {
  id: number;
  content: string;
  questionType: string;
  answers: Answer[] = [];
  answer: string;
  isCorrect: boolean;
}
