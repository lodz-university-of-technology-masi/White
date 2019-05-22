package pl.lodz.p.white.whitetestapp.testmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.exception.EntityNotFoundException;
import pl.lodz.p.white.whitetestapp.exception.FailedSaveException;
import pl.lodz.p.white.whitetestapp.exception.WrongRequestException;
import pl.lodz.p.white.whitetestapp.model.Account;
import pl.lodz.p.white.whitetestapp.model.Answer;
import pl.lodz.p.white.whitetestapp.model.Position;
import pl.lodz.p.white.whitetestapp.model.Question;
import pl.lodz.p.white.whitetestapp.model.TestTemplate;
import pl.lodz.p.white.whitetestapp.model.TestTemplateContent;
import pl.lodz.p.white.whitetestapp.repository.AccountRepository;
import pl.lodz.p.white.whitetestapp.repository.PositionRepository;
import pl.lodz.p.white.whitetestapp.repository.TestTemplateRepository;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.NewTestTemplateRequest;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.TestTemplateResponse;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.mapper.NewTestTemplateMapper;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.mapper.TestTemplateMapper;
import pl.lodz.p.white.whitetestapp.testmanager.service.TestTemplateService;
import pl.lodz.p.white.whitetestapp.translator.service.TranslatorService;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pl.lodz.p.white.whitetestapp.Constants.EN;
import static pl.lodz.p.white.whitetestapp.Constants.PL;
import static pl.lodz.p.white.whitetestapp.exception.WrongRequestException.NOT_ACCEPTABLE_DATA;
import static pl.lodz.p.white.whitetestapp.model.Role.CANDIDATE;
import static pl.lodz.p.white.whitetestapp.model.Role.MODERATOR;
import static pl.lodz.p.white.whitetestapp.model.Role.REDACTOR;

@Service
public class TestTemplateManager implements TestTemplateService {

    TestTemplateRepository repository;
    TranslatorService translatorService;
    AccountRepository accountRepository;
    PositionRepository positionRepository;

    @Autowired
    public TestTemplateManager(TestTemplateRepository repository, TranslatorService translatorService, AccountRepository accountRepository, PositionRepository positionRepository) {
        this.repository = repository;
        this.translatorService = translatorService;
        this.accountRepository = accountRepository;
        this.positionRepository = positionRepository;
    }

    @Override
    public TestTemplate getOne(Long id) throws EntityNotFoundException {
        try {
            return repository.getOne(id);
        } catch (PersistenceException e) {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public TestTemplate findOne(Long id) throws EntityNotFoundException {
        TestTemplate object = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return object;
    }

    @Override
    public List<TestTemplateResponse> getAll(HttpServletRequest request) throws WrongRequestException {
        Stream<TestTemplateResponse> templates = repository
                .findAll()
                .stream()
                .map(TestTemplateMapper::toTestTemplateResponse)
                .flatMap(List::stream);
        if (request.isUserInRole(MODERATOR.getAppRole())) {
            return templates.collect(Collectors.toList());
        } else if (request.isUserInRole(CANDIDATE.getAppRole())) {
            Account candidate = accountRepository
                    .findById(request.getUserPrincipal().getName())
                    .orElseThrow(() -> new WrongRequestException(NOT_ACCEPTABLE_DATA));
            return templates.filter(t -> Objects.equals(t.getLang(), candidate.getLang().name()))
                    .collect(Collectors.toList());
        } else if (request.isUserInRole(REDACTOR.getAppRole())) {
           return templates.filter(t -> t.getAuthor().equals(request.getUserPrincipal().getName()))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public TestTemplate addNewTestTemplate(NewTestTemplateRequest newTestTemplateRequest) throws WrongRequestException {
        TestTemplate testTemplate = NewTestTemplateMapper.toTestTemplate(newTestTemplateRequest, accountRepository, positionRepository);
        return repository.saveAndFlush(testTemplate);
    }

    public void setPositionForTest(TestTemplate test, Position position) throws FailedSaveException {
        try {
            test.setPosition(position);
            repository.saveAndFlush(test);
        } catch (PersistenceException e) {
            throw new FailedSaveException(FailedSaveException.OPERATION_EXECUTION_ERROR_SAVE);
        }
    }

    @Override
    public void translate(Long id, String sourceLang) {
        String targetLang = sourceLang.equals(PL) ? EN : PL;
        repository.findById(id).ifPresent(testTemplate -> {
            TestTemplateContent source;
            TestTemplateContent target = new TestTemplateContent();
            if (sourceLang.equals(PL)) {
                source = testTemplate.getPlVersion();
                testTemplate.setEnVersion(target);
            } else {
                source = testTemplate.getEnVersion();
                testTemplate.setPlVersion(target);
            }
            target.setUsers(source.getUsers());
            source.getQuestions().forEach(question -> {
                Question translateQuestion = translateQuestion(sourceLang, targetLang, question);
                target.getQuestions().add(translateQuestion);
            });
            repository.saveAndFlush(testTemplate);
        });

    }

    private Question translateQuestion(String sourceLang, String targetLang, Question question) {
        Question translateQuestion = new Question();
        translateQuestion.setQuestionType(question.getQuestionType());
        String questionContent = translatorService.translateSentence(question.getContent(), sourceLang, targetLang);
        translateQuestion.setContent(questionContent);

        question.getAnswers().forEach(answer -> {
            Answer translateAnswer = translateAnswer(sourceLang, targetLang, answer);
            translateQuestion.getAnswers().add(translateAnswer);
        });
        return translateQuestion;
    }

    private Answer translateAnswer(String sourceLang, String targetLang, Answer answer) {
        Answer translateAnswer = new Answer();
        String answerContent = translatorService.translateSentence(answer.getContent(), sourceLang, targetLang);
        translateAnswer.setContent(answerContent);
        return translateAnswer;
    }

    @Override
    public void deleteTestById(Long id, String lang) throws EntityNotFoundException {
        try {
            TestTemplate testToDelete = repository.getOne(id);
            if (lang.equals(PL)) {
                testToDelete.getPlVersion().setDeleted(true);
            } else {
                testToDelete.getEnVersion().setDeleted(true);
            }
            repository.saveAndFlush(testToDelete);
        } catch (PersistenceException e) {
            throw new EntityNotFoundException();
        }
    }
}
