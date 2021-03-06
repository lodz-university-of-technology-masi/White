package pl.lodz.p.white.whitetestapp.testmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.white.whitetestapp.accountmanager.service.PositionService;
import pl.lodz.p.white.whitetestapp.exception.EntityNotFoundException;
import pl.lodz.p.white.whitetestapp.exception.FailedSaveException;
import pl.lodz.p.white.whitetestapp.exception.WrongRequestException;
import pl.lodz.p.white.whitetestapp.model.ApiResponse;
import pl.lodz.p.white.whitetestapp.model.Position;
import pl.lodz.p.white.whitetestapp.model.TestTemplate;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.NewTestTemplateRequest;
import pl.lodz.p.white.whitetestapp.testmanager.service.TestTemplateService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RestController
@RequestMapping("/api/testtemplate")
public class TestTemplateController {

    private static final String TEST_TEMPLATE_WAS_CREATED = "Test template was created";
    private static final String OBJECT_UPDATE = "Object updated";
    private static final String OBJECT_DELETE = "Object deleted";

    private TestTemplateService service;
    private PositionService positionService;

    @Autowired
    public TestTemplateController(TestTemplateService service, PositionService positionService) {
        this.service = service;
        this.positionService = positionService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    TestTemplate get(@PathVariable("id") Long id) throws EntityNotFoundException {
        return service.getOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity getAll(HttpServletRequest request) throws WrongRequestException {
        return ResponseEntity.ok(service.getAll(request));
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_REDACTOR')")
    ResponseEntity addNewTestTemplate(@RequestBody NewTestTemplateRequest newTestTemplateRequest,
                                      HttpServletRequest request)
            throws EntityNotFoundException, WrongRequestException {
        ApiResponse response = new ApiResponse();
        service.addNewTestTemplate(newTestTemplateRequest.setAuthor(request.getUserPrincipal().getName()));
        response.setMessage(TEST_TEMPLATE_WAS_CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @RequestMapping(value = "/addlangversion",method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('ROLE_REDACTOR')")
    ResponseEntity addNewLangContent(@RequestBody NewTestTemplateRequest newTestTemplateRequest,
                                      HttpServletRequest request)
            throws EntityNotFoundException, WrongRequestException {
        ApiResponse response = new ApiResponse();
        service.addNewLangContent(newTestTemplateRequest.setAuthor(request.getUserPrincipal().getName()));
        response.setMessage(TEST_TEMPLATE_WAS_CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RequestMapping(value = "/setposition/{id}/{positionId}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('ROLE_REDACTOR', 'ROLE_MODERATOR')")
    ResponseEntity assignPositionToTest(@PathVariable("id") Long id, @PathVariable("positionId") String positionId) throws WrongRequestException, FailedSaveException {
        ApiResponse response = new ApiResponse();
        try {
            TestTemplate expected = service.findOne(id);
            String fixedPosId = positionId.replace("+", " ");
            Position expectedPosition = positionService.getOne(fixedPosId);
            service.setPositionForTest(expected, expectedPosition);
            response.setMessage(OBJECT_UPDATE);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (EntityNotFoundException e) {
            throw new WrongRequestException(WrongRequestException.NOT_EXISTING_DATA_REQUESTED);
        }
    }

    @RequestMapping(value = "/translate/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('ROLE_REDACTOR')")
    ResponseEntity translateTest(@PathVariable("id") Long id, @RequestParam("lang") String sourceLang) {
        service.translate(id, sourceLang);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value="/{id}")
    @PreAuthorize("hasAnyRole('ROLE_REDACTOR', 'ROLE_MODERATOR')")
    ResponseEntity deleteTest(@PathVariable Long id, @RequestParam("lang") String lang) throws EntityNotFoundException {
        ApiResponse response = new ApiResponse();
        service.deleteTestById(id, lang);
        response.setMessage(OBJECT_DELETE);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
