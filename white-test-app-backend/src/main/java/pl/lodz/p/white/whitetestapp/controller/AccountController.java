package pl.lodz.p.white.whitetestapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.white.whitetestapp.model.Account;
import pl.lodz.p.white.whitetestapp.service.AccountService;

@Controller
@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    AccountService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Account get(@PathVariable("id") String id) {
        return service.getOne(id);
    }

}
