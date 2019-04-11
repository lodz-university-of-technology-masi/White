package pl.lodz.p.white.whitetestapp.testmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.white.whitetestapp.model.Answer;
import pl.lodz.p.white.whitetestapp.testmanager.service.AnswerService;

@Controller
@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    AnswerService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Answer get(@PathVariable("id") Long id){
        return service.getOne(id);
    }

    @Autowired
    public AnswerController(AnswerService service) {
        this.service = service;
    }
}
