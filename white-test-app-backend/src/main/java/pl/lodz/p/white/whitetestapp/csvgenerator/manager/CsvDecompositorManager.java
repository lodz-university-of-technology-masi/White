package pl.lodz.p.white.whitetestapp.csvgenerator.manager;

import org.apache.commons.lang3.StringUtils;
import pl.lodz.p.white.whitetestapp.csvgenerator.service.CsvDecompositorService;
import pl.lodz.p.white.whitetestapp.exception.ParseDataException;
import pl.lodz.p.white.whitetestapp.model.Question;
import pl.lodz.p.white.whitetestapp.model.QuestionType;
import pl.lodz.p.white.whitetestapp.model.TestTemplate;
import pl.lodz.p.white.whitetestapp.model.TestTemplateContent;
import pl.lodz.p.white.whitetestapp.model.Answer;

import java.util.ArrayList;
import java.util.List;

public class CsvDecompositorManager implements CsvDecompositorService {

    private static final String PIPE = "|";
    private static final String QUESTION_TYPE_OPEN = "O";
    private static final String QUESTION_TYPE_CHOICE = "W";
    private static final String QUESTION_TYPE_SCALE = "S";
    private static final String QUESTION_TYPE_NUMBER = "L";
    private static final String LANGUAGE_TYPE_PL = "PL";
    private static final String LANGUAGE_TYPE_EN = "EN";
    private static final String NEW_LINE_SIGN = "\n";
    private static final String SEPARATOR = ";";

    @Override
    public TestTemplateContent importCsv(String value, TestTemplate template) throws ParseDataException {
        TestTemplateContent content = new TestTemplateContent();
        content.setTestTemplate(template);
        List<Question> questions = new ArrayList<>();
        String[] arrOfStr = value.split(NEW_LINE_SIGN);
        for (String line : arrOfStr) {
            validateLine(line);
        }
        for (String line : arrOfStr) {
            questions.add(createObject(line));
        }
        content.setQuestions(questions);
        return content;
    }

    private Question createObject(String line) {
        String[] questionData = line.split(SEPARATOR);
        Question question = new Question();
        question.setContent(questionData[3]);
        QuestionType type = getQuestionType(questionData[1]);
        question.setQuestionType(type);
        if (type == QuestionType.OPEN) {
            List<Answer> answers = new ArrayList<>();
            question.setAnswers(answers);
        } else {
            question.setAnswers(setAnswers(questionData));
        }
        return question;
    }

    private List<Answer> setAnswers(String[] questionData) {
        List<Answer> answers = new ArrayList<>();
        for (int i = 5; i < questionData.length; i++) {
            answers.add(new Answer().setContent(questionData[i]));
        }
        return answers;
    }

    private QuestionType getQuestionType(String columnValue) {
        switch (columnValue) {
            case QUESTION_TYPE_OPEN:
                return QuestionType.OPEN;
            case QUESTION_TYPE_CHOICE:
                return QuestionType.CHOICE;
            case QUESTION_TYPE_SCALE:
                return QuestionType.SCALE;
            case QUESTION_TYPE_NUMBER:
                return QuestionType.NUMBER;
            default:
                return null;
        }
    }

    private void validateLine(String data) throws ParseDataException {
        String[] questionData = data.split(SEPARATOR);
        int numberOfPossibleAnswers = questionData.length - 5;
        int i = 1;
        for (String value : questionData) {
            if (value.isEmpty()) {
                throw new ParseDataException(String.format(ParseDataException.COLUMN_ERROR_EMPTY, 1));
            }
            i++;
        }
        if (!StringUtils.isNumeric(questionData[0])) {
            throw new ParseDataException(String.format(ParseDataException.COLUMN_ERROR_FORMAT_NUMERIC, 1));
        } else if (!questionData[1].equals(QUESTION_TYPE_OPEN) &&
                !questionData[1].equals(QUESTION_TYPE_CHOICE) &&
                !questionData[1].equals(QUESTION_TYPE_SCALE) &&
                !questionData[1].equals(QUESTION_TYPE_NUMBER)) {
            throw new ParseDataException(ParseDataException.COLUMN_ERROR_QUESTION_TYPE);
        } else if (!questionData[2].equals(LANGUAGE_TYPE_PL) &&
                !questionData[2].equals(LANGUAGE_TYPE_EN)) {
            throw new ParseDataException(ParseDataException.COLUMN_ERROR_LANGUAGE_TYPE);
        } else if (!StringUtils.isNumeric(questionData[4]) && !questionData[4].equals(PIPE)) {
            throw new ParseDataException(ParseDataException.COLUMN_ERROR_WRONG_VALUE);
        } else if (!questionData[4].equals(PIPE)) {
            if (Double.parseDouble(questionData[4]) != numberOfPossibleAnswers) {
                throw new ParseDataException(ParseDataException.COLUMN_ERROR_ANSWERS_COUNT);
            }
        }
    }
}
