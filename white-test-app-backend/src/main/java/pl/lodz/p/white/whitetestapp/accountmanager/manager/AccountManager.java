package pl.lodz.p.white.whitetestapp.accountmanager.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.model.Account;
import pl.lodz.p.white.whitetestapp.repository.AccountRepository;
import pl.lodz.p.white.whitetestapp.accountmanager.service.AccountService;

@Service
public class AccountManager implements AccountService {

    AccountRepository repository;

    @Override
    public Account getOne(String id) {
        return repository.getOne(id);
    }

    @Autowired
    public AccountManager(AccountRepository repository) {
        this.repository = repository;
    }
}
