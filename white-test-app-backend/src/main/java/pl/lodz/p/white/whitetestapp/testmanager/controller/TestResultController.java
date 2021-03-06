package pl.lodz.p.white.whitetestapp.testmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.white.whitetestapp.exception.WrongRequestException;
import pl.lodz.p.white.whitetestapp.model.TestResult;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.CandidateTestResultRequest;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.TestCheckRequest;
import pl.lodz.p.white.whitetestapp.testmanager.service.TestResultService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RestController
@RequestMapping("/api/testresult")
public class TestResultController {

    TestResultService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_REDACTOR')")
    ResponseEntity get(@PathVariable("id") Long id) throws WrongRequestException {
        return ResponseEntity.ok(service.getOne(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_REDACTOR')")
    ResponseEntity getAll(HttpServletRequest request) {
        return ResponseEntity.ok(service.getAll(request.getUserPrincipal().getName()));
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_CANDIDATE')")
    ResponseEntity add(@RequestBody CandidateTestResultRequest candidateTestResultRequest, HttpServletRequest request)
                       throws WrongRequestException {
        service.add(candidateTestResultRequest, request.getUserPrincipal().getName());
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('ROLE_REDACTOR')")
    ResponseEntity addChecked(@RequestBody TestCheckRequest testCheckRequest) throws WrongRequestException {
        service.addChecked(testCheckRequest);
        return ResponseEntity.ok().build();
    }

    @Autowired
    public TestResultController(TestResultService service) {
        this.service = service;
    }
}
