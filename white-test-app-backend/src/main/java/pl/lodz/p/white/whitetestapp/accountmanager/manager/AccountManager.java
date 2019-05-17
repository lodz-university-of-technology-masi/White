package pl.lodz.p.white.whitetestapp.accountmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.accountmanager.dtos.AccountDto;
import pl.lodz.p.white.whitetestapp.accountmanager.service.AccountService;
import pl.lodz.p.white.whitetestapp.exception.EntityNotFoundException;
import pl.lodz.p.white.whitetestapp.exception.WrongRequestException;
import pl.lodz.p.white.whitetestapp.model.Account;
import pl.lodz.p.white.whitetestapp.model.Lang;
import pl.lodz.p.white.whitetestapp.model.Role;
import pl.lodz.p.white.whitetestapp.repository.AccountRepository;

import java.util.List;


@Service
public class AccountManager implements AccountService {

    AccountRepository repository;

    @Autowired
    public AccountManager(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account getOne(String id) {
        return repository.getOne(id);
    }

    @Override
    public Account findOne(String id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteRedactor(Account account) throws WrongRequestException {
        if(account.getRole() == Role.REDACTOR){
            repository.delete(account);
        } else {
            throw new WrongRequestException(WrongRequestException.NOT_ACCEPTABLE_DATA);
        }

    }

    @Override
    public List<Account> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Account> getAllRedactors() {
        return repository.findAllByRole(Role.REDACTOR);
    }

    @Override
    public void updateRedactor(AccountDto account) throws EntityNotFoundException, WrongRequestException {
        Account dao = findOne(account.getUsername());
        if(dao.getRole() != Role.REDACTOR) {
            throw new WrongRequestException(WrongRequestException.NOT_ACCEPTABLE_DATA);
        }
        dao.setEmail(account.getEmail());
        dao.setPasswordHash(account.getPassword()); // todo After authentication done implement generate hash
        dao.setLang(Lang.valueOf(account.getLang()));
        repository.saveAndFlush(dao);
    }
}
