package pl.lodz.p.white.whitetestapp.testmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.exception.WrongRequestException;
import pl.lodz.p.white.whitetestapp.model.Account;
import pl.lodz.p.white.whitetestapp.model.TestResult;
import pl.lodz.p.white.whitetestapp.repository.AccountRepository;
import pl.lodz.p.white.whitetestapp.repository.QuestionRepository;
import pl.lodz.p.white.whitetestapp.repository.TestResultRepository;
import pl.lodz.p.white.whitetestapp.repository.TestTemplateContentRepository;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.CandidateTestResultRequest;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.TestCheckRequest;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.TestResultDetailResponse;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.TestResultResponse;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.mapper.TestResultMapper;
import pl.lodz.p.white.whitetestapp.testmanager.service.TestResultService;

import java.util.List;
import java.util.stream.Collectors;

import static pl.lodz.p.white.whitetestapp.testmanager.dtos.mapper.TestResultMapper.toTestResult;
import static pl.lodz.p.white.whitetestapp.testmanager.dtos.mapper.TestResultMapper.toTestResultDetailResponse;

@Service
public class TestResultManager implements TestResultService {

    private final TestResultRepository repository;
    private final TestTemplateContentRepository templateContentRepository;
    private final QuestionRepository questionRepository;
    private final AccountRepository accountRepository;


    @Override
    public TestResultDetailResponse getOne(Long id) throws WrongRequestException {
        TestResult testResult = repository.findById(id)
                .orElseThrow(() -> new WrongRequestException(WrongRequestException.NOT_EXISTING_DATA_REQUESTED));
        return toTestResultDetailResponse(testResult);
    }

    @Override
    public void add(CandidateTestResultRequest testResultRequest, String username) throws WrongRequestException {
        TestResult testResult = toTestResult(testResultRequest, templateContentRepository, questionRepository);
        Account participant = accountRepository
                .findById(username)
                .orElseThrow(() -> new WrongRequestException(WrongRequestException.NOT_EXISTING_DATA_REQUESTED));
        testResult.setParticipant(participant);
        repository.saveAndFlush(testResult);
    }

    @Override
    public void addChecked(TestCheckRequest testCheckRequest) throws WrongRequestException {
        TestResult testResult = repository.findById(testCheckRequest.getId())
                .orElseThrow(() -> new WrongRequestException(WrongRequestException.NOT_EXISTING_DATA_REQUESTED));
        TestResult checkedTestResult = toTestResult(testCheckRequest, testResult);
        repository.saveAndFlush(checkedTestResult);
    }

    @Override
    public List<TestResultResponse> getAll(String username) {
        return repository.findAll()
                .stream()
                .filter(t -> t.getTestTemplate().getTestTemplate().getAuthor().getUsername().equals(username))
                .map(TestResultMapper::toTestResultResponse)
                .collect(Collectors.toList());
    }

    @Autowired
    public TestResultManager(TestResultRepository repository, TestTemplateContentRepository testTemplateRepository, QuestionRepository questionRepository, AccountRepository accountRepository) {
        this.repository = repository;
        this.templateContentRepository = testTemplateRepository;
        this.questionRepository = questionRepository;
        this.accountRepository = accountRepository;
    }
}
