package pl.lodz.p.white.whitetestapp.accountmanager.dtos.mapper;


import org.springframework.security.crypto.password.PasswordEncoder;
import pl.lodz.p.white.whitetestapp.accountmanager.dtos.AccountDto;
import pl.lodz.p.white.whitetestapp.accountmanager.manager.AccountManager;
import pl.lodz.p.white.whitetestapp.exception.EntityNotFoundException;
import pl.lodz.p.white.whitetestapp.exception.WrongRequestException;
import pl.lodz.p.white.whitetestapp.model.Account;
import pl.lodz.p.white.whitetestapp.model.Lang;
import pl.lodz.p.white.whitetestapp.model.Role;

import java.util.ArrayList;

public class AccountMapper {

    public static Account fromDtoConverter(AccountDto account, PasswordEncoder encoder) {
        return new Account()
                .setUsername(account.getUsername())
                .setEmail(account.getEmail())
                .setPasswordHash(encoder.encode(account.getPassword()))
                .setLang(Lang.valueOf(account.getLang()))
                .setRole(Role.REDACTOR)
                .setTestResults(new ArrayList<>());
    }

    public static Account updateObject(AccountManager accountManager, AccountDto account, PasswordEncoder encoder)
            throws EntityNotFoundException, WrongRequestException {
        Account dao = accountManager.findOne(account.getUsername());
        if (dao.getRole() != Role.REDACTOR) {
            throw new WrongRequestException(WrongRequestException.NOT_ACCEPTABLE_DATA);
        }
        if(account.getPassword().isEmpty()){
            account.setPassword(dao.getPasswordHash());
        }
        dao.setEmail(account.getEmail())
                .setPasswordHash(encoder.encode(account.getPassword()))
                .setLang(Lang.valueOf(account.getLang()));
        return dao;
    }
}
