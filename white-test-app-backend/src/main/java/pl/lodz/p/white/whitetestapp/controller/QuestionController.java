package pl.lodz.p.white.whitetestapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.white.whitetestapp.model.Question;
import pl.lodz.p.white.whitetestapp.service.QuestionService;

@Controller
@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    QuestionService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Question get(@PathVariable("id") Long id){
        return service.getOne(id);
    }
}

