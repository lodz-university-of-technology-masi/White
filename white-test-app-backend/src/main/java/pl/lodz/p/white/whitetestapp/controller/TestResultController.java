package pl.lodz.p.white.whitetestapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.white.whitetestapp.model.TestResult;
import pl.lodz.p.white.whitetestapp.service.TestResultService;

@Controller
@RestController
@RequestMapping("/api/testresult")
public class TestResultController {

    @Autowired
    TestResultService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    TestResult get(@PathVariable("id") Long id){
        return service.getOne(id);
    }
}
