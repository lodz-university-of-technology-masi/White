package pl.lodz.p.white.whitetestapp.testmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import pl.lodz.p.white.whitetestapp.model.ApiResponse;
import pl.lodz.p.white.whitetestapp.model.TestTemplate;
import pl.lodz.p.white.whitetestapp.testmanager.service.TestTemplateService;

@Controller
@RestController
@RequestMapping("/api/testtemplate")
public class TestTemplateController {

    public static final String TEST_TEMPLATE_COULDN_T_BE_CREATED = "Test template couldn't be created";
    public static final String TEST_TEMPLATE_WAS_CREATED = "Test template was created";
    private TestTemplateService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    TestTemplate get(@PathVariable("id") Long id){
        return service.getOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity addNewTestTemplate(@RequestBody TestTemplate testTemplate) {
        ApiResponse response = new ApiResponse();
        if (service.getOne(testTemplate.getId()) != null) {
            response.setMessage(TEST_TEMPLATE_COULDN_T_BE_CREATED);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            service.addNewTestTemplate(testTemplate);
            response.setMessage(TEST_TEMPLATE_WAS_CREATED);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
    }

    @Autowired
    public TestTemplateController(TestTemplateService service) {
        this.service = service;
    }
}
