package pl.lodz.p.white.whitetestapp.testmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.white.whitetestapp.model.AnswerToQuestion;
import pl.lodz.p.white.whitetestapp.testmanager.service.AnswerToQuestionService;

@Controller
@RestController
@RequestMapping("/api/answertoquestion")
public class AnswerToQuestionController {

    AnswerToQuestionService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    AnswerToQuestion get(@PathVariable("id") Long id){
        return service.getOne(id);
    }

    @Autowired
    public AnswerToQuestionController(AnswerToQuestionService service) {
        this.service = service;
    }
}
