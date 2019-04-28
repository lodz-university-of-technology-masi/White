package pl.lodz.p.white.whitetestapp.testmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.white.whitetestapp.accountmanager.service.PositionService;
import pl.lodz.p.white.whitetestapp.model.ApiResponse;
import pl.lodz.p.white.whitetestapp.model.Position;
import pl.lodz.p.white.whitetestapp.model.TestTemplate;
import pl.lodz.p.white.whitetestapp.testmanager.service.TestTemplateService;

@Controller
@RestController
@RequestMapping("/api/testtemplate")
public class TestTemplateController {

    private static final String GIVEN_DATA_WAS_NULL = "Given data was incorrect";
    private static final String OBJECT_UPDATE = "Object updated";
    private static final String UNABLE_TO_EXECUTE_QUERY = "Query unabled to succeed";
    private TestTemplateService service;
    private PositionService positionService;

    @Autowired
    public TestTemplateController(TestTemplateService service, PositionService positionService) {
        this.service = service;
        this.positionService = positionService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    TestTemplate get(@PathVariable("id") Long id) {
        return service.getOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @RequestMapping(value = "/setposition/{id}/{positionId}", method = RequestMethod.PUT)
    ResponseEntity assignPositionToTest(@PathVariable("id") Long id, @PathVariable("positionId") String positionId) {
        ApiResponse response = new ApiResponse();
        TestTemplate expected = service.findOne(id);
        Position expectedPosition = positionService.getOne(positionId);
        if(expectedPosition == null || expected == null){
            response.setMessage(GIVEN_DATA_WAS_NULL);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
        } else if(service.setPositionForTest(expected,expectedPosition) == 1){
            response.setMessage(OBJECT_UPDATE);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.setMessage(UNABLE_TO_EXECUTE_QUERY);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
