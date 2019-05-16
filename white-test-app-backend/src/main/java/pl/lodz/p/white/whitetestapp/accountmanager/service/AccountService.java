package pl.lodz.p.white.whitetestapp.accountmanager.service;

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
}
