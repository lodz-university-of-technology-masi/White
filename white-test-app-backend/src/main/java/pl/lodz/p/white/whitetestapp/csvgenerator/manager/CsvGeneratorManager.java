package pl.lodz.p.white.whitetestapp.csvgenerator.manager;

import pl.lodz.p.white.whitetestapp.csvgenerator.service.CsvGeneratorService;
import pl.lodz.p.white.whitetestapp.model.*;

import java.util.List;

public class CsvGeneratorManager implements CsvGeneratorService {

    private static final String SEPARATOR =";";
    private static String questionType ="";
    private static String questionLang ="";

    @Override
    public StringBuilder exportCsv(TestTemplateContent testTemplateContent)  {
        TestTemplate test = testTemplateContent.getTestTemplate();
        List<Question> questions = testTemplateContent.getQuestions();
        StringBuilder file = new StringBuilder();
        questions.forEach(question -> {
           getQuestionLang(test, testTemplateContent);
           StringBuilder answersToCsv = getAnswersToQuestion(question);
            StringBuilder line = new StringBuilder();
            line.append(questions.indexOf(question)+1).append(SEPARATOR).append(questionType).append(SEPARATOR).append(questionLang).append(SEPARATOR).append(question.getContent());
            if (question.getQuestionType()==QuestionType.CHOICE || question.getQuestionType()==QuestionType.SCALE){
                line.append(SEPARATOR).append(answersToCsv).append("\n");
            }else{
                line.append(SEPARATOR).append(SEPARATOR).append("|").append(SEPARATOR).append("\n");
            }
            file.append(line);
        });
        return file;
    }
    
   private StringBuilder getAnswersToQuestion(Question question){
        StringBuilder answersToCsv = new StringBuilder();
       if (question.getQuestionType()==QuestionType.OPEN) {
           setQuestionType("O");
        }else if(question.getQuestionType()==QuestionType.CHOICE) {
           setQuestionType("W");
            List<Answer> answers= question.getAnswers();
            answersToCsv.append(answers.size()).append(SEPARATOR);
            for (int i = 0; i < answers.size(); i++) {
                if(i != answers.size()-1)
                    answersToCsv.append(answers.get(i).getContent()).append(SEPARATOR);
                else answersToCsv.append(answers.get(i).getContent());
            }
        }else if(question.getQuestionType()==QuestionType.SCALE){
           setQuestionType("S");
            List<Answer> answers= question.getAnswers();
            answersToCsv.append(answers.size()).append(SEPARATOR);
            for (int i = 0; i < answers.size(); i++) {
                if(i != answers.size()-1)
                    answersToCsv.append(answers.get(i).getContent()).append(SEPARATOR);
                else answersToCsv.append(answers.get(i).getContent());
            }
        }else if(question.getQuestionType()==QuestionType.NUMBER){
           setQuestionType("N");
        }
        return answersToCsv;
    }
    
    private void getQuestionLang(TestTemplate test, TestTemplateContent testTemplateContent){
        if(test.getPlVersion() != null) {
            if (test.getPlVersion().getId().equals(testTemplateContent.getId())) {
                setQuestionLang("PL");
            }
        }else if(test.getEnVersion() != null) {
            if (test.getEnVersion().getId().equals(testTemplateContent.getId())) {
                setQuestionLang("EN");
            }
        }
    }

    private void setQuestionType(String type){
         questionType=type;
    }

    private void setQuestionLang(String type){
        questionLang=type;
    }
}
