package pl.lodz.p.white.whitetestapp.testmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import pl.lodz.p.white.whitetestapp.exception.AppException;
import pl.lodz.p.white.whitetestapp.exception.WrongRequestException;
import pl.lodz.p.white.whitetestapp.exception.FailedSaveException;
import pl.lodz.p.white.whitetestapp.exception.DocumentCreationException;
import pl.lodz.p.white.whitetestapp.exception.EntityNotFoundException;
import pl.lodz.p.white.whitetestapp.model.ApiResponse;
import pl.lodz.p.white.whitetestapp.model.Question;
import pl.lodz.p.white.whitetestapp.model.TestInformationRequest;
import pl.lodz.p.white.whitetestapp.model.TestTemplateContent;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.TemplateToModifyDto;
import pl.lodz.p.white.whitetestapp.testmanager.service.QuestionService;
import pl.lodz.p.white.whitetestapp.testmanager.service.TestTemplateContentService;

import java.io.IOException;

import javax.validation.ConstraintViolationException;


@Controller
@RestController
@RequestMapping("/api/testtemplatecontent")
public class TestTemplateContentController {

    private static final String OBJECT_UPDATED = "Object updated";
    private static final String DATA_IMPORTED = "CSV was imported";
    TestTemplateContentService service;
    QuestionService questionService;

    @Autowired
    public TestTemplateContentController(TestTemplateContentService service, QuestionService questionService) {
        this.service = service;
        this.questionService = questionService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    TestTemplateContent get(@PathVariable("id") Long id) {
        return service.getOne(id);
    }

    @RequestMapping(value = "/addquestion/{id}", method = RequestMethod.PUT)
    ResponseEntity addQuestion(@PathVariable("id") Long id, @RequestBody Question question) throws WrongRequestException, FailedSaveException {
        try {
            ApiResponse response = new ApiResponse();
            TestTemplateContent content = service.findOne(id);
            Long createdQuestionId = questionService.addNew(question);
            Question createdQuestion = questionService.findOne(createdQuestionId);
            service.addQuestionToContent(content, createdQuestion);
            response.setMessage(OBJECT_UPDATED);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (EntityNotFoundException e) {
            throw new WrongRequestException(WrongRequestException.NOT_EXISTING_DATA_REQUESTED);
        }
    }

    @RequestMapping(value = "/downloadpdf/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE, consumes = MediaType.ALL_VALUE)
    ResponseEntity<byte[]> generatePdf(@PathVariable("id") Long id, @RequestBody TestInformationRequest testInformationRequest) throws WrongRequestException, DocumentCreationException {
        try {
            TestTemplateContent requestedTest = service.findOne(id);
            byte[] contents = service.generatePDF(requestedTest);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            String filename = testInformationRequest.getName() + "_" +
                    testInformationRequest.getPosition() + "_" +
                    testInformationRequest.getLang() + ".pdf";
            filename = filename.replaceAll("\\s+", "_");
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            headers.add("filename", filename);
            return new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new WrongRequestException(WrongRequestException.NOT_EXISTING_DATA_REQUESTED);
        } catch (DocumentCreationException e) {
            throw new DocumentCreationException(DocumentCreationException.ERROR_CREATING_DOCUMENT);
        }
    }

    @RequestMapping(value = "/downloadcsv/{id}", method = RequestMethod.GET)
    public ResponseEntity<StringBuilder> exportCsv(@PathVariable("id") Long id) throws WrongRequestException {
        try {
            TestTemplateContent requestedTestContent = service.findOne(id);
            StringBuilder file = service.exportCsv(requestedTestContent);
            String filename = requestedTestContent.getTestTemplate().getId() + "_" +
                    requestedTestContent.getTestTemplate().getName() + "_" +
                    requestedTestContent.getTestTemplate().getPosition().getName() + ".csv";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData(filename, filename);
            headers.add("filename", filename);
            return new ResponseEntity<>(file, headers, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new WrongRequestException(WrongRequestException.NOT_EXISTING_DATA_REQUESTED);
        }
    }
  
    @RequestMapping(value = "/importcsv/{templateId}", method = RequestMethod.POST)
    public ResponseEntity importCsv(@RequestBody String file, @PathVariable("templateId") Long id) throws AppException {
        ApiResponse response = new ApiResponse();
        service.importCsv(file, id);
        response.setMessage(DATA_IMPORTED);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity edit(@RequestBody TemplateToModifyDto template) throws WrongRequestException {
        try {
            ApiResponse response = new ApiResponse();
            service.editTestContent(template);
            response.setMessage(OBJECT_UPDATED);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (EntityNotFoundException | IllegalArgumentException | ConstraintViolationException e) {
            throw new WrongRequestException(WrongRequestException.NOT_ACCEPTABLE_DATA);
        }
    }
}
