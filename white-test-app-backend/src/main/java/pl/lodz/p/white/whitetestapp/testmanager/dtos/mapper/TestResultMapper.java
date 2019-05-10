package pl.lodz.p.white.whitetestapp.testmanager.dtos.mapper;

import pl.lodz.p.white.whitetestapp.exception.WrongRequestException;
import pl.lodz.p.white.whitetestapp.model.AnswerToQuestion;
import pl.lodz.p.white.whitetestapp.model.TestResult;
import pl.lodz.p.white.whitetestapp.repository.QuestionRepository;
import pl.lodz.p.white.whitetestapp.repository.TestTemplateContentRepository;
import pl.lodz.p.white.whitetestapp.repository.TestTemplateRepository;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.CandidateAnswerToQuestionDto;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.CandidateTestResultRequest;

import java.util.ArrayList;
import java.util.List;

public class TestResultMapper {

    public static TestResult toTestResult(CandidateTestResultRequest testResultRequest,
                                   TestTemplateContentRepository templateContentRepository,
                                   QuestionRepository questionRepository) throws WrongRequestException {
        List<AnswerToQuestion> answers = new ArrayList<>();
        for (CandidateAnswerToQuestionDto a : testResultRequest.getAnswers()) {
            AnswerToQuestion answerToQuestion = toAnswerToQuestion(a, questionRepository);
            answers.add(answerToQuestion);
        }
        return new TestResult()
                .setTestTemplate(templateContentRepository
                        .findById(testResultRequest.getTestTemplateId())
                        .orElseThrow(() -> new WrongRequestException(WrongRequestException.NOT_EXISTING_DATA_REQUESTED)))
                .setAnswers(answers);
    }

    private static AnswerToQuestion toAnswerToQuestion(CandidateAnswerToQuestionDto answerToQuestionDto,
                                                QuestionRepository questionRepository) throws WrongRequestException {
        return new AnswerToQuestion()
                .setQuestion(questionRepository
                        .findById(answerToQuestionDto.getQuestionId())
                        .orElseThrow(() -> new WrongRequestException(WrongRequestException.NOT_EXISTING_DATA_REQUESTED)))
                .setAnswer(answerToQuestionDto.getAnswer());

    }
}
