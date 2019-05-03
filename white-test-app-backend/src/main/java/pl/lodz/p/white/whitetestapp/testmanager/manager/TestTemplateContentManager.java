package pl.lodz.p.white.whitetestapp.testmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.model.Question;
import pl.lodz.p.white.whitetestapp.model.TestTemplateContent;
import pl.lodz.p.white.whitetestapp.repository.TestTemplateContentRepository;
import pl.lodz.p.white.whitetestapp.testmanager.service.TestTemplateContentService;

import javax.persistence.PersistenceException;

@Service
public class TestTemplateContentManager implements TestTemplateContentService {

    TestTemplateContentRepository repository;

    @Override
    public TestTemplateContent getOne(Long id) {
        return repository.getOne(id);
    }

    @Override
    public TestTemplateContent findOne(Long id) {
        TestTemplateContent object =  repository.findById(id).orElse(null);
        return object;
    }

    @Override
    public int addQuestionToContent(TestTemplateContent content, Question question) {
        try {
            content.getQuestions().add(question);
            repository.saveAndFlush(content);
            return 1;
        } catch (PersistenceException e){
            return 0;
        }
    }

    @Autowired
    public TestTemplateContentManager(TestTemplateContentRepository repository) {
        this.repository = repository;
    }
}
