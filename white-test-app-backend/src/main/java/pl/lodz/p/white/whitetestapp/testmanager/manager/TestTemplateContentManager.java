package pl.lodz.p.white.whitetestapp.testmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.csvgenerator.service.CsvGeneratorService;
import pl.lodz.p.white.whitetestapp.exception.DocumentCreationException;
import pl.lodz.p.white.whitetestapp.exception.EntityNotFoundException;
import pl.lodz.p.white.whitetestapp.exception.FailedSaveException;
import pl.lodz.p.white.whitetestapp.model.Question;
import pl.lodz.p.white.whitetestapp.model.TestTemplateContent;
import pl.lodz.p.white.whitetestapp.pdfgenerator.manager.PdfGeneratorManager;
import pl.lodz.p.white.whitetestapp.pdfgenerator.service.PdfGeneratorService;
import pl.lodz.p.white.whitetestapp.repository.TestTemplateContentRepository;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.TemplateToModifyDto;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.mapper.TestTemplateMapper;
import pl.lodz.p.white.whitetestapp.testmanager.service.TestTemplateContentService;
import pl.lodz.p.white.whitetestapp.csvgenerator.manager.CsvGeneratorManager;

import javax.persistence.PersistenceException;

import static pl.lodz.p.white.whitetestapp.testmanager.dtos.mapper.TestTemplateMapper.toTestTemplateContent;

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

    @Override
    public byte[] generatePDF(TestTemplateContent requestedTest) throws DocumentCreationException {
        PdfGeneratorService service = new PdfGeneratorManager();
        return service.generate(requestedTest);
    }

    @Override
    public StringBuilder exportCsv(TestTemplateContent requestedTest) {
        CsvGeneratorService service = new CsvGeneratorManager();
        return service.exportCsv(requestedTest);
    }

    @Override
    public void editTestContent(TemplateToModifyDto template) throws EntityNotFoundException {
        TestTemplateContent testTemplateContent = repository.findById(template.getId()).orElseThrow(EntityNotFoundException::new);
        testTemplateContent = toTestTemplateContent(template, testTemplateContent);
        repository.saveAndFlush(testTemplateContent);
    }

    @Autowired
    public TestTemplateContentManager(TestTemplateContentRepository repository) {
        this.repository = repository;
    }
}
