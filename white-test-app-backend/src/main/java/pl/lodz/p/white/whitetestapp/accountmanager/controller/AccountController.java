package pl.lodz.p.white.whitetestapp.accountmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.white.whitetestapp.accountmanager.dtos.AccountDto;
import pl.lodz.p.white.whitetestapp.accountmanager.service.AccountService;
import pl.lodz.p.white.whitetestapp.model.Account;
import org.springframework.web.bind.annotation.RequestBody;
import pl.lodz.p.white.whitetestapp.exception.EntityAlreadyExistsException;
import pl.lodz.p.white.whitetestapp.exception.EntityNotFoundException;
import pl.lodz.p.white.whitetestapp.exception.WrongRequestException;
import pl.lodz.p.white.whitetestapp.model.Account;
import pl.lodz.p.white.whitetestapp.model.ApiResponse;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api/account")
public class AccountController {

    public static final String REDACTOR_DELETED = "Redactor deleted";
    private static final String REDACTOR_ADDED = "Redactor added";
    private static final String REDACTOR_EDITED = "Redactor edited";
    AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    Account get(@PathVariable("id") String id) {
        return service.getOne(id);
    }

    @RequestMapping(value = "/redactors", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    public ResponseEntity editRedactor(@RequestBody AccountDto account) throws WrongRequestException {
        try {
            ApiResponse response = new ApiResponse();
            service.updateRedactor(account);
            response.setMessage(REDACTOR_EDITED);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (EntityNotFoundException | IllegalArgumentException | ConstraintViolationException e) {
            throw new WrongRequestException(WrongRequestException.NOT_ACCEPTABLE_DATA);
        }
    }

    @RequestMapping(value = "/redactors/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    public ResponseEntity deleteRedactor(@PathVariable String id) throws WrongRequestException {
        try {
            ApiResponse response = new ApiResponse();
            Account account = service.findOne(id);
            service.deleteRedactor(account);
            response.setMessage(REDACTOR_DELETED);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (EntityNotFoundException e) {
            throw new WrongRequestException(WrongRequestException.NOT_ACCEPTABLE_DATA);
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    public List<Account> getAll() {
        return service.getAll();
    }

    @RequestMapping(value = "/redactors", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    public List<Account> getAllRedactors() {
        return service.getAllRedactors();
    }

    @RequestMapping(value = "/redactors", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    public ResponseEntity addRedactor(@RequestBody AccountDto account) throws WrongRequestException {
        try {
            ApiResponse response = new ApiResponse();
            service.addRedactor(account);
            response.setMessage(REDACTOR_ADDED);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (EntityAlreadyExistsException | ConstraintViolationException | IllegalArgumentException e) {
            throw new WrongRequestException(WrongRequestException.NOT_ACCEPTABLE_DATA);
        }
    }
}
