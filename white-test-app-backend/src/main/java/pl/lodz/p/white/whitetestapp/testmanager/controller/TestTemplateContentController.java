package pl.lodz.p.white.whitetestapp.testmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.white.whitetestapp.model.ApiResponse;
import pl.lodz.p.white.whitetestapp.model.Question;
import pl.lodz.p.white.whitetestapp.model.TestTemplateContent;
import pl.lodz.p.white.whitetestapp.testmanager.service.QuestionService;
import pl.lodz.p.white.whitetestapp.testmanager.service.TestTemplateContentService;

@Controller
@RestController
@RequestMapping("/api/testtemplatecontent")
public class TestTemplateContentController {

    public static final String REQUEST_BODY_IS_NULL = "Given data is inappropriate";
    public static final String OBJECT_UPDATED = "Object updated";
    public static final String UNABLE_TO_EXECUTE_QUERY = "Unable to execute query";
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
    ResponseEntity addQuestion(@PathVariable("id") Long id, @RequestBody Question question) {
        ApiResponse response = new ApiResponse();
        TestTemplateContent content = service.findOne(id);
        Long createdQuestionId = questionService.addNew(question);
        if (question == null || content == null || createdQuestionId == 0) {
            response.setMessage(REQUEST_BODY_IS_NULL);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
        } else {
            Question createdQuestion = questionService.findOne(createdQuestionId);
            if (service.addQuestionToContent(content, createdQuestion) == 1) {
                response.setMessage(OBJECT_UPDATED);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response.setMessage(UNABLE_TO_EXECUTE_QUERY);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }
    }

}
