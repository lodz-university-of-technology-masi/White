package pl.lodz.p.white.whitetestapp.testmanager.service;

import pl.lodz.p.white.whitetestapp.exception.DocumentCreationException;
import pl.lodz.p.white.whitetestapp.exception.EntityNotFoundException;
import pl.lodz.p.white.whitetestapp.exception.FailedSaveException;
import pl.lodz.p.white.whitetestapp.exception.ParseDataException;
import pl.lodz.p.white.whitetestapp.model.Question;
import pl.lodz.p.white.whitetestapp.model.TestTemplateContent;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.TemplateToModifyDto;

public interface TestTemplateContentService {

    void importCsv(String csvContent, Long id) throws ParseDataException, FailedSaveException, EntityNotFoundException;

    TestTemplateContent getOne(Long id);

    TestTemplateContent findOne(Long id) throws EntityNotFoundException;

    void addQuestionToContent(TestTemplateContent content, Question question) throws FailedSaveException;

    byte[] generatePDF(TestTemplateContent requestedTest) throws DocumentCreationException;

    StringBuilder exportCsv(TestTemplateContent requestedTest);

    void editTestContent(TemplateToModifyDto template) throws EntityNotFoundException;
}
