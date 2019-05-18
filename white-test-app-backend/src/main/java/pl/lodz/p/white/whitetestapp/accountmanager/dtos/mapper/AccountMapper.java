package pl.lodz.p.white.whitetestapp.accountmanager.dtos.mapper;

import pl.lodz.p.white.whitetestapp.accountmanager.dtos.AccountDto;
import pl.lodz.p.white.whitetestapp.model.Account;
import pl.lodz.p.white.whitetestapp.model.Lang;
import pl.lodz.p.white.whitetestapp.model.Role;

import java.util.ArrayList;

public class AccountMapper {

    public static Account fromDtoConverter(AccountDto account) {
        return new Account()
                .setUsername(account.getUsername())
                .setEmail(account.getEmail())
                .setPasswordHash(account.getPassword()) //todo change to hash method after authentication done
                .setLang(Lang.valueOf(account.getLang()))
                .setRole(Role.REDACTOR)
                .setTestResults(new ArrayList<>());
    }
}
