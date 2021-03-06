package pl.lodz.p.white.whitetestapp.accountmanager.service;

import pl.lodz.p.white.whitetestapp.accountmanager.dtos.AccountDto;
import pl.lodz.p.white.whitetestapp.exception.EntityAlreadyExistsException;
import pl.lodz.p.white.whitetestapp.exception.EntityNotFoundException;
import pl.lodz.p.white.whitetestapp.exception.WrongRequestException;
import pl.lodz.p.white.whitetestapp.model.Account;

import java.util.List;

public interface AccountService {

    Account getOne(String id);

    Account findOne(String id) throws EntityNotFoundException;

    void deleteRedactor(Account account) throws WrongRequestException;

    List<Account> getAll();

    List<Account> getAllRedactors();

    void updateRedactor(AccountDto account) throws EntityNotFoundException, WrongRequestException;

    void addRedactor(AccountDto account) throws EntityAlreadyExistsException;
}
