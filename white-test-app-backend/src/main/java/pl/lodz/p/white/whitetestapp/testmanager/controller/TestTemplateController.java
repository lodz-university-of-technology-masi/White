package pl.lodz.p.white.whitetestapp.testmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.white.whitetestapp.model.TestTemplate;
import pl.lodz.p.white.whitetestapp.testmanager.service.TestTemplateService;

@Controller
@RestController
@RequestMapping("/api/testtemplate")
public class TestTemplateController {

    TestTemplateService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    TestTemplate get(@PathVariable("id") Long id){
        return service.getOne(id);
    }

    @Autowired
    public TestTemplateController(TestTemplateService service) {
        this.service = service;
    }
}
