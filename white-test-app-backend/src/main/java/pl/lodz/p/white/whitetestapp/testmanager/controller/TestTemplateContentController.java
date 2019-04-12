package pl.lodz.p.white.whitetestapp.testmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.white.whitetestapp.model.TestTemplateContent;
import pl.lodz.p.white.whitetestapp.testmanager.service.TestTemplateContentService;

@Controller
@RestController
@RequestMapping("/api/testtemplatecontent")
public class TestTemplateContentController {

    TestTemplateContentService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    TestTemplateContent get(@PathVariable("id") Long id){
        return service.getOne(id);
    }

    @Autowired
    public TestTemplateContentController(TestTemplateContentService service) {
        this.service = service;
    }
}