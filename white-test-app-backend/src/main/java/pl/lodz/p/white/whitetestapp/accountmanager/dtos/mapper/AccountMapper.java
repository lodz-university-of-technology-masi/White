package pl.lodz.p.white.whitetestapp.accountmanager.dtos.mapper;


import pl.lodz.p.white.whitetestapp.accountmanager.dtos.AccountDto;
import pl.lodz.p.white.whitetestapp.accountmanager.manager.AccountManager;
import pl.lodz.p.white.whitetestapp.exception.EntityNotFoundException;
import pl.lodz.p.white.whitetestapp.exception.WrongRequestException;
import pl.lodz.p.white.whitetestapp.model.Account;
import pl.lodz.p.white.whitetestapp.model.Lang;
import pl.lodz.p.white.whitetestapp.model.Role;

public class AccountMapper {


    public static Account updateObject(AccountManager accountManager, AccountDto account) throws EntityNotFoundException, WrongRequestException {
        Account dao = accountManager.findOne(account.getUsername());
        if (dao.getRole() != Role.REDACTOR) {
            throw new WrongRequestException(WrongRequestException.NOT_ACCEPTABLE_DATA);
        }
        dao.setEmail(account.getEmail())
                .setPasswordHash(account.getPassword()) // todo After authentication done implement generate hash
                .setLang(Lang.valueOf(account.getLang()));
        return dao;
    }
}
