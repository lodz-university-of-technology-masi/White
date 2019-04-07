package pl.lodz.p.white.whitetestapp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.white.whitetestapp.model.Account;
import pl.lodz.p.white.whitetestapp.repository.AccountRepository;
import pl.lodz.p.white.whitetestapp.service.AccountService;

@Service
public class AccountManager implements AccountService {

    @Autowired
    AccountRepository repository;

    @Override
    public Account getOne(String id) {
        return repository.getOne(id);
    }
}
