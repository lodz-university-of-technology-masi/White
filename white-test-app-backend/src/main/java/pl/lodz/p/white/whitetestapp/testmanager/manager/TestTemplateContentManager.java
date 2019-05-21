package pl.lodz.p.white.whitetestapp.testmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.csvgenerator.manager.CsvDecompositorManager;
import pl.lodz.p.white.whitetestapp.csvgenerator.manager.CsvGeneratorManager;
import pl.lodz.p.white.whitetestapp.csvgenerator.service.CsvDecompositorService;
import pl.lodz.p.white.whitetestapp.csvgenerator.service.CsvGeneratorService;
import pl.lodz.p.white.whitetestapp.exception.DocumentCreationException;
import pl.lodz.p.white.whitetestapp.exception.EntityNotFoundException;
import pl.lodz.p.white.whitetestapp.exception.FailedSaveException;
import pl.lodz.p.white.whitetestapp.exception.ParseDataException;
import pl.lodz.p.white.whitetestapp.model.Question;
import pl.lodz.p.white.whitetestapp.model.TestTemplate;
import pl.lodz.p.white.whitetestapp.model.TestTemplateContent;
import pl.lodz.p.white.whitetestapp.pdfgenerator.manager.PdfGeneratorManager;
import pl.lodz.p.white.whitetestapp.pdfgenerator.service.PdfGeneratorService;
import pl.lodz.p.white.whitetestapp.repository.TestTemplateContentRepository;
import pl.lodz.p.white.whitetestapp.testmanager.service.TestTemplateContentService;
import pl.lodz.p.white.whitetestapp.testmanager.service.TestTemplateService;

import javax.persistence.PersistenceException;

@Service
public class TestTemplateContentManager implements TestTemplateContentService {

    TestTemplateContentRepository repository;
    TestTemplateService testTemplateService;

    @Autowired
    public TestTemplateContentManager(TestTemplateContentRepository repository, TestTemplateService testTemplateService) {
        this.repository = repository;
        this.testTemplateService = testTemplateService;
    }

    @Override
    public void importCsv(String csvContent, Long id) throws ParseDataException, FailedSaveException, EntityNotFoundException {
        CsvDecompositorService service = new CsvDecompositorManager();
        try {
            TestTemplate template = testTemplateService.findOne(id);
            repository.saveAndFlush(service.importCsv(csvContent, template));
        } catch (PersistenceException e) {
            throw new FailedSaveException(FailedSaveException.OPERATION_EXECUTION_ERROR_SAVE);
        }
    }

    @Override
    public TestTemplateContent getOne(Long id) {
        return repository.getOne(id);
    }

    @Override
    public TestTemplateContent findOne(Long id) throws EntityNotFoundException {
        TestTemplateContent object = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return object;
    }

    @Override
    public void addQuestionToContent(TestTemplateContent content, Question question) throws FailedSaveException {
        try {
            content.getQuestions().add(question);
            repository.saveAndFlush(content);
        } catch (PersistenceException e) {
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
}
