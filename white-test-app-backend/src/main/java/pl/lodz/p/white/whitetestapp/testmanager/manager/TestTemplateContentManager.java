package pl.lodz.p.white.whitetestapp.testmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.exception.EntityNotFoundException;
import pl.lodz.p.white.whitetestapp.exception.FailedSaveException;
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
    public TestTemplateContent findOne(Long id) throws EntityNotFoundException {
        TestTemplateContent object =  repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return object;
    }

    @Override
    public void addQuestionToContent(TestTemplateContent content, Question question) throws FailedSaveException {
        try {
            content.getQuestions().add(question);
            repository.saveAndFlush(content);
        } catch (PersistenceException e){
            throw new FailedSaveException(FailedSaveException.OPERATION_EXECUTION_ERROR_SAVE);
        }
    }

    @Autowired
    public TestTemplateContentManager(TestTemplateContentRepository repository) {
        this.repository = repository;
    }
}
