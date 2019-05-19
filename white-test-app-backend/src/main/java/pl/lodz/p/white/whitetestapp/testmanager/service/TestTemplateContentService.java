package pl.lodz.p.white.whitetestapp.testmanager.service;

import pl.lodz.p.white.whitetestapp.exception.DocumentCreationException;
import pl.lodz.p.white.whitetestapp.exception.EntityNotFoundException;
import pl.lodz.p.white.whitetestapp.exception.FailedSaveException;
import pl.lodz.p.white.whitetestapp.model.Question;
import pl.lodz.p.white.whitetestapp.model.TestTemplateContent;

public interface TestTemplateContentService {

    TestTemplateContent getOne(Long id);

    TestTemplateContent findOne(Long id) throws EntityNotFoundException;

    void addQuestionToContent(TestTemplateContent content, Question question) throws FailedSaveException;

    byte[] generatePDF(TestTemplateContent requestedTest) throws DocumentCreationException;

    StringBuilder exportCsv(TestTemplateContent requestedTest);
}
