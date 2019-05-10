package pl.lodz.p.white.whitetestapp.testmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.exception.WrongRequestException;
import pl.lodz.p.white.whitetestapp.model.TestResult;
import pl.lodz.p.white.whitetestapp.repository.QuestionRepository;
import pl.lodz.p.white.whitetestapp.repository.TestResultRepository;
import pl.lodz.p.white.whitetestapp.repository.TestTemplateContentRepository;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.CandidateTestResultRequest;
import pl.lodz.p.white.whitetestapp.testmanager.service.TestResultService;

import static pl.lodz.p.white.whitetestapp.testmanager.dtos.mapper.TestResultMapper.toTestResult;

@Service
public class TestResultManager implements TestResultService {

    private final TestResultRepository repository;
    private final TestTemplateContentRepository templateContentRepository;
    private final QuestionRepository questionRepository;


    @Override
    public TestResult getOne(Long id) {
        return repository.getOne(id);
    }

    @Override
    public void add(CandidateTestResultRequest testResultRequest) throws WrongRequestException {
        TestResult testResult = toTestResult(testResultRequest, templateContentRepository, questionRepository);
        //testResult.setParticipant(...); //TODO: ustawiac użytkownika gdy będzie już logowanie
        repository.saveAndFlush(testResult);
    }

    @Autowired
    public TestResultManager(TestResultRepository repository, TestTemplateContentRepository testTemplateRepository, QuestionRepository questionRepository) {
        this.repository = repository;
        this.templateContentRepository = testTemplateRepository;
        this.questionRepository = questionRepository;
    }
}
