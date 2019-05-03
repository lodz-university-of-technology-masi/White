package pl.lodz.p.white.whitetestapp.testmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.model.Answer;
import pl.lodz.p.white.whitetestapp.model.Position;
import pl.lodz.p.white.whitetestapp.model.Question;
import pl.lodz.p.white.whitetestapp.model.TestTemplate;
import pl.lodz.p.white.whitetestapp.model.TestTemplateContent;
import pl.lodz.p.white.whitetestapp.repository.TestTemplateRepository;
import pl.lodz.p.white.whitetestapp.testmanager.response.TestTemplateResponse;
import pl.lodz.p.white.whitetestapp.testmanager.response.mapper.TestTemplateMapper;
import pl.lodz.p.white.whitetestapp.testmanager.service.TestTemplateService;
import pl.lodz.p.white.whitetestapp.translator.service.TranslatorService;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.stream.Collectors;

import static pl.lodz.p.white.whitetestapp.Constants.EN;
import static pl.lodz.p.white.whitetestapp.Constants.PL;

@Service
public class TestTemplateManager implements TestTemplateService {

    TestTemplateRepository repository;
    TranslatorService translatorService;

    @Autowired
    public TestTemplateManager(TestTemplateRepository repository, TranslatorService translatorService) {
        this.repository = repository;
        this.translatorService = translatorService;
    }

    @Override
    public TestTemplate getOne(Long id) {
        try {
            return repository.getOne(id);
        } catch (PersistenceException e) {
            return null;
        }

    }

    @Override
    public TestTemplate findOne(Long id){
        TestTemplate object = repository.findById(id).orElse(null);
        return object;
    }

    @Override
    public List<TestTemplateResponse> getAll() {
        return repository
                .findAll()
                .stream()
                .map(TestTemplateMapper::toTestTemplateResponse)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    public int setPositionForTest(TestTemplate test, Position position) {
        try{
            test.setPosition(position);
            repository.saveAndFlush(test);
            return 1;
        } catch (PersistenceException e){
            return 0;
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


}
