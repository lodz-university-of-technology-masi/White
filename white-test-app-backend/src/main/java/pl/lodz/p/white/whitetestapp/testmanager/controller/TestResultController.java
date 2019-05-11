package pl.lodz.p.white.whitetestapp.testmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.white.whitetestapp.exception.WrongRequestException;
import pl.lodz.p.white.whitetestapp.model.TestResult;
import pl.lodz.p.white.whitetestapp.testmanager.dtos.CandidateTestResultRequest;
import pl.lodz.p.white.whitetestapp.testmanager.service.TestResultService;

@Controller
@RestController
@RequestMapping("/api/testresult")
public class TestResultController {

    TestResultService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity get(@PathVariable("id") Long id) throws WrongRequestException {
        return ResponseEntity.ok(service.getOne(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity getAll() throws WrongRequestException {
        return ResponseEntity.ok(service.getAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity add(@RequestBody CandidateTestResultRequest candidateTestResultRequest) throws WrongRequestException {
        service.add(candidateTestResultRequest);
        return ResponseEntity.ok().build();
    }

    @Autowired
    public TestResultController(TestResultService service) {
        this.service = service;
    }
}
