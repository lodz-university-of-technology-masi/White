package pl.lodz.p.white.whitetestapp.accountmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.accountmanager.dtos.AccountDto;
import pl.lodz.p.white.whitetestapp.accountmanager.dtos.mapper.AccountMapper;
import pl.lodz.p.white.whitetestapp.accountmanager.service.AccountService;
import pl.lodz.p.white.whitetestapp.exception.EntityAlreadyExistsException;
import pl.lodz.p.white.whitetestapp.exception.EntityNotFoundException;
import pl.lodz.p.white.whitetestapp.exception.WrongRequestException;
import pl.lodz.p.white.whitetestapp.model.Account;
import pl.lodz.p.white.whitetestapp.model.Role;
import pl.lodz.p.white.whitetestapp.repository.AccountRepository;

import javax.validation.ConstraintViolationException;
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
        if (account.getRole() == Role.REDACTOR) {
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
    public void addRedactor(AccountDto account) throws EntityAlreadyExistsException, ConstraintViolationException, IllegalArgumentException {
        try {
            Account daoCheck = findOne(account.getUsername());
            throw new EntityAlreadyExistsException();
        } catch (EntityNotFoundException e) {
            createRedactorEntity(account);
        }
    }

    private void createRedactorEntity(AccountDto account) throws ConstraintViolationException, IllegalArgumentException  {
       repository.saveAndFlush(AccountMapper.fromDtoConverter(account));
    }
}
